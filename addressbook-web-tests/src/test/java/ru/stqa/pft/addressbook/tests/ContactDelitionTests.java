package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDelitionTests extends TestBase {
    @Test
    public void testContactDelition() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContactPage();
            app.getContactHelper().createContact(new ContactData("Pepper", "Black", "Man", "Test1"),
                    true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().DeleteSelectedContact();
        app.getContactHelper().AcceptDelition();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(after, before);

    }
}
