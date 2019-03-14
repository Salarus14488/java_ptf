package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().goToContactModification();
        app.getContactHelper().fillContactForm3(new ContactData("Murat", "Ignatovich", "Benzin"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToGroupPage();

    }
}
