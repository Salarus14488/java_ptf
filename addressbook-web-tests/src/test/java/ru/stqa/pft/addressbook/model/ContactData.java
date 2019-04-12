package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String group;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String homeNumber;
    private final String mobileNumber;
    private final String email;


    public ContactData(int id,  String firstName, String middleName, String lastName, String group, String nickname, String title, String company, String address, String homeNumber, String mobileNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
    }

    public ContactData( String firstName, String middleName, String lastName, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
        this.nickname = "";
        this.title = "";
        this.company = "";
        this.address = "";
        this.homeNumber = "";
        this.mobileNumber = "";
        this.email = "";

    }
    public ContactData(int id, String firstName, String middleName, String lastName, String group) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
        this.nickname = "";
        this.title = "";
        this.company = "";
        this.address = "";
        this.homeNumber = "";
        this.mobileNumber = "";
        this.email = "";

    }
    public ContactData(int id, String firstName, String middleName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = "";
        this.nickname = "";
        this.title = "";
        this.company = "";
        this.address = "";
        this.homeNumber = "";
        this.mobileNumber = "";
        this.email = "";
    }
    public int getId() {
        return  id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }
    public String getGroup() {
        return group; }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
