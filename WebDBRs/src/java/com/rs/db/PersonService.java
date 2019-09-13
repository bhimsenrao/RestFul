/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rs.db;

import com.rs.db.bean.Person;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/persons")
public class PersonService {

    IPersonDAO personDao;

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    private int id;

    public PersonService() {
        personDao = new PersonDAOImpl();
    }

    public PersonService(UriInfo uriInfo, Request request, int id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
        personDao = new PersonDAOImpl();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Person> getPersons() {
        return personDao.getPersons();
    }

    @GET
    @Path("/{id}")
    public Person getPerson(@PathParam("id") int id) {
        return personDao.getPerson(id);
    }
    @GET
    @Path("delete/{id}")
    public Person deletePerson(@PathParam("id") int id) {
        return personDao.deletePerson(id);
    }
    @GET
    @Path("update/{id}/{name}")
    //@Consumes(MediaType.TEXT_XML)
    public Person updatePerson(@PathParam("id") int id,
           @PathParam("name") String name) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        personDao.updatePerson(person);
        return person;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addPerson(@FormParam("id") int id,
            @FormParam("pname") String name,
            @Context HttpServletResponse servletResponse)
            throws IOException {
        Person person = new Person(id, name);
        personDao.addPerson(person);
        servletResponse.sendRedirect("./persons/");
    }
}