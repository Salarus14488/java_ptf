package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.Date;

public class ChangePasswordTests extends TestBase {
    private Date currentDate = new Date();

    @Test
    public void testLogin() throws MessagingException, InterruptedException {
        app.registration().login("administrator", "root");
        app.registration().goToManage();
        app.registration().goToManageUsers();
        UserData user = app.manageUsersHelper().getUsers()
                .stream()
                .filter(userData -> !userData.username().equals("administrator"))
                .findFirst().orElseThrow(() -> new AssertionError("User not found"));
        app.manageUsersHelper().openUser(user.id());
        app.editUserHelper().resetPassword();
        app.james().initTelnetSession();
        Thread.sleep(5000);
        MailMessage message = getChangePasswordMessage(user);
        app.james().closeTelnetSession();
        String link = Arrays.stream(message.text.split("\n"))
                .filter(row -> row.contains("http"))
                .findFirst().orElseThrow(() -> new AssertionError("URL not found"));
        app.editAccountHelper().openUrl(link);
        app.editAccountHelper().updatePassword("password");
        app.registration().login(user.username(), "password");
    }

    private MailMessage getChangePasswordMessage(UserData userData) throws MessagingException {
        while (true) {
            MailMessage message = app.james()
                    .waitForMail(userData.username(), "password", 60000)
                    .stream()
                    .filter(m -> m.text.contains("requested a password change") && m.sendDate.after(currentDate))
                    .findFirst().orElse(null);
            if(message != null) {
                return message;
            }
        }
    }

}
