package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class EditAccountHelper extends HelperBase {
    public EditAccountHelper(ApplicationManager app) {
        super(app);
    }

    public void updatePassword(String newPassword) {
        wd.findElement(By.name("password")).sendKeys(newPassword);
        wd.findElement(By.name("password_confirm")).sendKeys(newPassword);
        wd.findElement(By.xpath("//input[@value = 'Update User']")).click();
    }
}
