package ru.stqa.pft.mantis.model;

import java.util.Date;

public class MailMessage {
    public String to;
    public String text;
    public Date sendDate;

    public MailMessage(String to, String text, Date sendDate) {
        this.to = to;
        this.text = text;
        this.sendDate = sendDate;
    }
}
