package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.GroupCreationTests;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DbHelper {


    private final SessionFactory sessionFactory;

    public DbHelper() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public Groups groups() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for (GroupData group : result) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);

    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        for (ContactData contact : result) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
        result.sort(Comparator.comparingInt(ContactData::getId));
        return new Contacts(result);
    }

    public Contacts contacts(String groupName) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List <ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        List<ContactData> resultByGroup = result.stream().filter(contactData -> {
            Groups groups = contactData.getGroups();
            return groups.stream().anyMatch(groupData -> groupData.getName().equals(groupName));
        }).collect(Collectors.toList());
        for (ContactData contact : result) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
        resultByGroup.sort(Comparator.comparingInt(ContactData::getId));
        return new Contacts(resultByGroup);
    }
}
