package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
public class ContactCreationTests extends TestBase {


    @Test
    public void testAddNewContact() {

        app.getNavigationHelper().goToNewContactPage();
        app.getContactHelper().fillContactFormAll(
                new ContactData("Damir", "Maratovich", "Yusupov", "Salarus",
                        "Gaz", "Bank", "Cowwall 5", "4992055756", "9670693352",
                        "gazbank@mail.ru"));
        app.getContactHelper().saveContact();
    }
}
