package com.rs.db;

public interface IPersonDAO {

    public void addPerson(com.rs.db.bean.Person person);
    public java.util.ArrayList<com.rs.db.bean.Person> getPersons();
    public com.rs.db.bean.Person getPerson(int id);
    public com.rs.db.bean.Person deletePerson(int id);
    public void updatePerson(com.rs.db.bean.Person person);
}
