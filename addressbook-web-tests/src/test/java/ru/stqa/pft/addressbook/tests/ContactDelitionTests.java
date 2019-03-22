package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDelitionTests extends TestBase {
    @Test
    public void testContactDelition() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToNewContactPage();
            app.getContactHelper().createContact(new ContactData("Peper", "Maratovich", "Yusupov", "test1", "Salarus",
                    "Gaz", "Bank", "Cowwall 5", "4992055756", "9670693352",
                    "gazbank@mail.ru"), true);
        }
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().DeleteSelectedContact();
        app.getContactHelper().AcceptDelition();
    }
}
