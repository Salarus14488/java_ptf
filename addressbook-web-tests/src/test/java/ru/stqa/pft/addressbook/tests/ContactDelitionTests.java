package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDelitionTests extends TestBase {
    @Test
    public void testContactDelition() {
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().DeleteSelectedContact();
        app.getContactHelper().AcceptDelition();
    }
}
