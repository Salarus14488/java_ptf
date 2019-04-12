package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAModificationButton()) {
            app.getNavigationHelper().goToNewContactPage();
            app.getContactHelper().createContact(new ContactData("Peper", "Maratovich", "Yusupov", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Murat", "Ignatovich", "Yusupov");
        app.getContactHelper().goToContactModification(before.get(before.size() - 1).getId());
        app.getContactHelper().fillContactForm3(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byLastName = (g1, g2) -> (g1.getLastName() + g1.getFirstName()).compareTo(g2.getLastName() + g2.getFirstName());
        before.sort(byLastName);
        after.sort(byLastName);
        System.out.println(after);
        System.out.println(before);
        Assert.assertEquals(after, before);
    }
}
