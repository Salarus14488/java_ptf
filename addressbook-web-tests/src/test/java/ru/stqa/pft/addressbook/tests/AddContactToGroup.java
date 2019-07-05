package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddContactToGroup extends TestBase {


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
            app.goTo().goToHomePage();
        }
        app.goTo().goToHomePage();
    }

    @Test
    public void testAddContactToGroup() {
       ContactData contact = app.db().contacts().iterator().next();
       app.getContactHelper().selectContactById(contact.getId());
        Groups groups = app.db().groups();
        String groupName = groups.stream()
                .filter(groupData -> !groupData.getName().contains("["))
                .map(GroupData::getName)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Список групп пуст"));
        app.getContactHelper().addContactToGroup(groupName);
        app.goTo().goToHomePage();
        app.getContactHelper().selectGroupByName(groupName);
        Contacts contacts = app.db().contacts(groupName);
        Assert.assertTrue(contacts.stream().anyMatch(contactData -> contactData.getId() == contact.getId()));
    }
}
