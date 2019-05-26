package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testAddNewContact() {

        Contacts before = app.contact().all();
        app.goTo().goToNewContactPage();
        ContactData contact = new ContactData().withFirstName("Demir").withLastName("Dallas").withMiddleName("Test").withGroup("test1");
        app.getContactHelper().create((contact), true);
        Contacts after = app.getContactHelper().all();
        assertThat(after.size(),equalTo(before.size() + 1));
        assertThat(after, equalTo( before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        app.goTo().goToHomePage();
    }
}
