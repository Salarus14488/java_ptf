package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactFormAll(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        attach(By.name("photo"), contactData.getPhoto());


        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);

                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next()
                        .getName());
            } else {
                Assert.assertTrue(isElementPresent(By.name("new_group")));
            }
        }
    }

    public void fillContactForm3(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void saveContact() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void SelectFirstContact() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td/input"));
    }
    public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void DeleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void AcceptDelition() {
        acceptAllert();
    }

    public void create(ContactData contact, boolean creation) {
        fillContactFormAll(contact, creation);
        saveContact();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public boolean isThereAModificationButton() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        for (WebElement element : rows) {
            List<WebElement> cells = element.findElements(By.xpath("./td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String postAddress = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td[input[@type = 'checkbox']]"))
                    .findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withPostAddress(postAddress));
        }

        return contacts;

    }

    public void goToContactModification(int index) {
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
        elements.get(index).findElement(By.xpath(".//img[@alt='Edit']")).click();
    }

    public void goToContactModificationById(int id) {
        WebElement raw = wd.findElement(By.xpath("//tr[td[input[@value='" + id + "']]]"));
        raw.findElement(By.xpath(".//img[@alt='Edit']")).click();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        DeleteSelectedContact();
        AcceptDelition();
        click(By.linkText("home"));
    }

    public void modify(ContactData contact) {
//        selectContactById(contact.getId());
        goToContactModificationById(contact.getId());
        fillContactForm3(contact, false);
        updateContact();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withPostAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();
    }

    public void selectGroupByName(String groupName) {
       new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    }

    public void removeContactFromGroup() {
        wd.findElement(By.name("remove")).click();
    }

    public void addContactToGroup(String groupName) {
        new Select (wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
        wd.findElement(By.name("add")).click();

    }
}
