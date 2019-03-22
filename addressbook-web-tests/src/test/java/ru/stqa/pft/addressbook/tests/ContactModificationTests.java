package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
       if (!app.getContactHelper().isThereAModificationButton()) {
           app.getNavigationHelper().goToNewContactPage();
           app.getContactHelper().createContact(new ContactData("Peper", "Maratovich", "Yusupov", "test1", "Salarus",
                   "Gaz", "Bank", "Cowwall 5", "4992055756", "9670693352",
                   "gazbank@mail.ru"), true);
       }
        app.getNavigationHelper().goToContactModification();
        app.getContactHelper().fillContactForm3(new ContactData("Murat", "Ignatovich", "Benzin", null),false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToGroupPage();

    }
}
