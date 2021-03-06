package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        if (app.db().contacts().size() == 0) {
            app.goTo().goToNewContactPage();
            app.contact().create(new ContactData()
                                .withFirstName("Beta")
                                .withLastName("Serk")
                                .withMiddleName("Dad"),
                        true);
        }
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName(UUID.randomUUID().toString().substring(0, 4))
                .withLastName(UUID.randomUUID().toString().substring(0, 4))
                .withLastName(UUID.randomUUID().toString().substring(0, 4));
        app.contact().modify(contact);
        app.goTo().goToHomePage();
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());
        assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
