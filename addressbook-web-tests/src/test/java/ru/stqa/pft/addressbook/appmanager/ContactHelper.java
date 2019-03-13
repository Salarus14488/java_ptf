package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper (FirefoxDriver wd) {
        super(wd);
    }
    public void fillContactForm(ContactData groupContact) {
        type(By.name("firstname"), groupContact.getFirstname());
        type(By.name("middlename"), groupContact.getMiddlename());
        type(By.name("lastname"), groupContact.getLastname());
        type(By.name("nickname"), groupContact.getNickname());
        type(By.name("title"), groupContact.getTitle());
        type(By.name("company"), groupContact.getCompany());
        type(By.name("address"), groupContact.getAddress());
        type(By.name("home"), groupContact.getHomeNumber());
        type(By.name("mobile"), groupContact.getMobileNumber());
        type(By.name("email"), groupContact.getEmail());
    }
    public void saveContact() {
        click(By.xpath("//input[@name='submit']"));
    }
}
