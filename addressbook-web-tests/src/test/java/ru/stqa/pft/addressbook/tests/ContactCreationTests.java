package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testAddNewContact() {

        List<ContactData> before = app.getContactHelper().getContactList();
        System.out.println(before);
        app.getNavigationHelper().goToNewContactPage();
        ContactData contact = new ContactData("Peper", "Black", "Mort", "test1");
        app.getContactHelper().createContact((contact), true);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byLastName = (g1, g2) -> g1.getLastName().compareTo(g2.getLastName());
        before.sort(byLastName);
        after.sort(byLastName);
        System.out.println(after);
        System.out.println(before);
        Assert.assertEquals(before, after);

        app.getNavigationHelper().goToHomePage();
    }
}
