package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;



import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDelitionTests extends TestBase {
    @Test(enabled = false)
    public void testContactDelition() {
        if (app.contact().all().size() == 0) {
            app.goTo().goToNewContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Beta")
                    .withLastName("Serk")
                    .withMiddleName("Dad")
                    .withGroup("test1"),
                    true);
        }
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(),before.size() - 1);

        assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
        app.goTo().goToHomePage();
    }
}
