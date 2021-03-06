package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(
                Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validContactsFromJson")
    public void testAddNewContact(ContactData contact) {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.goTo().goToNewContactPage();
        File photo = new File("src/test/resources/stru.png");
        ContactData newContact = new ContactData().withFirstName("Demir").withLastName("Dallas").withMiddleName("Test").withPhoto(photo)
                .inGroup(groups.iterator().next());
        app.getContactHelper().create((newContact), true);
        Contacts after = app.db().contacts();
        assertThat(after.size(),equalTo(before.size() + 1));
        assertThat(after, equalTo( before.withAdded(newContact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));

        app.goTo().goToHomePage();
    }

}
