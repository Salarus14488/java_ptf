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


        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void fillContactForm3(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
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
//            String[] cells = element.getText().split(" ");
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.xpath(".//td[input[@type = 'checkbox']]"))
                    .findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }

        return contacts;

    }

//    public void goToContactModification(int editId) {
//        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name = 'entry']"));
//        for (WebElement element : elements) {
//            int id = Integer.parseInt(element.findElement(By.xpath(".//td[input[@type = 'checkbox']]"))
//                    .findElement(By.tagName("input")).getAttribute("value"));
//            if (id == editId) {
//                element.findElement(By.xpath(".//img[@alt='Edit']")).click();
//                break;
//            }
//        }
//    }
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
    }

    public void modify(ContactData contact) {
//        selectContactById(contact.getId());
        goToContactModificationById(contact.getId());
        fillContactForm3(contact, false);
        updateContact();
    }
}
