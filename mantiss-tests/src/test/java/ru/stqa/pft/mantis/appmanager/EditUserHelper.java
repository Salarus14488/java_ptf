package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;
import java.util.stream.Collectors;

public class EditUserHelper extends HelperBase {

    public EditUserHelper(ApplicationManager app) {
        super(app);
    }

    public void resetPassword() {
        wd.findElement(By.xpath("//input[@value = 'Reset Password']")).click();
    }
}
