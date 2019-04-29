package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
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

        before.remove(0);
        Comparator<? super ContactData> byLastName = (g1, g2) -> (g1.getLastName() + g1.getFirstName()).compareTo(g2.getLastName() + g2.getFirstName());
        before.sort(byLastName);
        after.sort(byLastName);
        System.out.println(after);
        System.out.println(before);
        Assert.assertEquals(before, after);

        app.getNavigationHelper().goToHomePage();
    }
}
