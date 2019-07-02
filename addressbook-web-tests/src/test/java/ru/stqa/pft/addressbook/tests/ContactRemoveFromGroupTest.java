package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

public class ContactRemoveFromGroupTest extends TestBase {

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
