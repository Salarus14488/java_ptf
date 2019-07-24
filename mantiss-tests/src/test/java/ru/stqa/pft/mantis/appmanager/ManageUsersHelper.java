package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.tests.TestBase;

import java.util.List;
import java.util.stream.Collectors;

public class ManageUsersHelper extends HelperBase {

    public ManageUsersHelper(ApplicationManager app) {
        super(app);
    }

    public List<UserData> getUsers() {
        return wd.findElements(By.xpath("//tr[contains(@class, 'row') and not(contains(@class, 'row-category'))]"))
                .stream().map(row -> {
                    String username = row.findElement(By.xpath(".//a")).getText();
                    String email = row.findElement(By.xpath("(.//td)[3]")).getText();
                    String id = row.findElement(By.xpath(".//a")).getAttribute("href").split("=")[1];
                    return new UserData().username(username).email(email).id(id);
                }).collect(Collectors.toList());
    }

    public void openUser(String id) {
        wd.findElement(By.xpath("//a[contains(@href, 'id=" + id + "')]")).click();
    }
}
