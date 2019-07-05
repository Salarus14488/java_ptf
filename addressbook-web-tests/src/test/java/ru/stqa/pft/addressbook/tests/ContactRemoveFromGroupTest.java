package ru.stqa.pft.addressbook.tests;

import com.sun.javafx.scene.control.ControlAcceleratorSupport;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.stream.Collectors;

public class ContactRemoveFromGroupTest extends TestBase {

    @BeforeMethod
    public void checkContactsInGroup() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
            app.goTo().goToHomePage();
        }
        app.goTo().goToHomePage();

        Contacts contacts = app.db().contacts();
        ContactData contact = contacts.stream()
                .filter(contactData -> !contactData.getGroups().isEmpty())
                .findFirst().orElse(null);
        if (contact == null) {
            app.goTo().goToNewContactPage();
            String firstNAme = "Beta";
            String lastNAme = "Serk";
            String middleNAme = "Dad";
            app.contact().create(new ContactData()
                            .withFirstName(firstNAme)
                            .withLastName(lastNAme)
                            .withMiddleName(middleNAme)
                    .withPhoto(new File("src\\test\\resources\\stru.png")),
                    true);
            app.goTo().goToHomePage();
            int id = app.db().contacts().stream()
                    .filter(contactData -> contactData.getFirstName().equals(firstNAme)
                            && contactData.getLastName().equals(lastNAme)
                            && contactData.getMiddleName().equals(middleNAme)
                            && contactData.getGroups().isEmpty())
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Контакт не создался"))
                    .getId();
            app.getContactHelper().selectContactById(id);
            Groups groups = app.db().groups();
            String groupName = groups.stream()
                    .filter(groupData -> !groupData.getName().contains("["))
                    .map(GroupData::getName)
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("Список групп пуст"));
            app.getContactHelper().addContactToGroup(groupName);
            app.goTo().goToHomePage();
            app.getContactHelper().selectGroupByName(groupName);
            Assert.assertTrue(app.db().contacts(groupName).stream().anyMatch(contactData -> contactData.getId() == id));
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        Groups groups = app.db().groups();
        String groupName = groups.stream()
                .filter(groupData -> !groupData.getName().contains("["))
                .map(GroupData::getName)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Список групп пуст"));
        app.getContactHelper().selectGroupByName(groupName);
        Contacts contacts = app.db().contacts(groupName);
        app.getContactHelper().selectContactById(contacts.iterator().next().getId());
        app.getContactHelper().removeContactFromGroup();
        app.goTo().goToHomePage();
        app.getContactHelper().selectGroupByName(groupName);
        Assert.assertEquals(app.db().contacts(groupName).size(), contacts.size() - 1);
    }
}
