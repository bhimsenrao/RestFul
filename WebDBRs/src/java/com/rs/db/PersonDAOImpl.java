/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rs.db;

import com.rs.db.bean.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Bhimsen
 */
public class PersonDAOImpl implements IPersonDAO {

    private com.rs.db.DBFactory factory = null;
    private java.sql.Connection connection = null;

    public PersonDAOImpl() {
        factory = com.rs.db.DBFactory.getFactory();
        connection = factory.getConnection();
        System.out.println("Connection ready");
    }

    @Override
    public void addPerson(Person person) {
        try {
            String command = "insert into person values(?,?)";
            PreparedStatement statement = connection.prepareStatement(command);
            statement.setInt(1, person.getId());
            statement.setString(2, person.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<>();
        Person person = null;
        try {
            String command = "select * from person";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(command);
            while (result.next()) {
                person = new Person();
                person.setId(result.getInt(1));
                person.setName(result.getString(2));
                persons.add(person);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return persons;
    }

    @Override
    public Person getPerson(int id) {
        Person person = new Person();
        try {
            String command = "select * from person where id=" + id;
            Statement statement = connection.createStatement(
                  //  ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_SENSITIVE
            );
            ResultSet result = statement.executeQuery(command);
            result.next();
            person = new Person();
            person.setId(result.getInt(1));
            person.setName(result.getString(2));
            System.out.println(person.getId()+" --- "+person.getName());
            return person;
        } catch (Exception e) {
            e.printStackTrace();
            return person;
        }

    }

    @Override
    public Person deletePerson(int id) {
        Person person = getPerson(id);
        try {
            if (person != null) {
                String command = "delete from person where id=" + id;
                Statement statement = connection.createStatement();
                statement.executeUpdate(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void updatePerson(Person person) {
        Person oldPerson = person;
        try {
            if (oldPerson != null) {
                String command = "update person set name='"
                        + person.getName() + "' where id=" + person.getId();
                Statement statement = connection.createStatement();
                statement.executeUpdate(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
