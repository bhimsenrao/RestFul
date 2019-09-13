package com.rest;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/employees")
public class EmployeeService {

    @GET
    @Produces({MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML})
    public List<Employee> getEmployees_JSON() {
        List<Employee> listOfCountries = EmployeeDAO.getAllEmployees();
        return listOfCountries;
    }

    @RolesAllowed({"admin", "manager"})
    @GET
    @Path("/jsonList")
    public String getEmployees(@Context SecurityContext sc) {
        String data = "no";
        if (!sc.isUserInRole("client")) {
            List<Employee> listOfEmps = EmployeeDAO.getAllEmployees();
            data = "emps[";
            for (Employee emp : listOfEmps) {
                data += "{empid:" + emp.getEmpNo() + ",empname:" + emp.getEmpName()
                        + ",empjob:" + emp.getPosition() + "},";
            }
            data += "]";

            return data;
        } else {
            return sc.getAuthenticationScheme();
        }
    }

    @GET
    @Path("/{empNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Employee getEmployee(@PathParam("empNo") String empNo) {
        return EmployeeDAO.getEmployee(empNo);
    }

    @POST
    @Path("addEmps")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Employee addEmployee(@FormParam("id") String id,
            @FormParam("name") String name, @FormParam("job") String job) {
        Employee emp = new Employee(id, name, job);
        return EmployeeDAO.addEmployee(emp);
    }

    @GET
    @Path("delete/{empNo}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteEmployee(@PathParam("empNo") String empNo,
            @Context HttpServletResponse resp) {
        EmployeeDAO.deleteEmployee(empNo);
        try {
            resp.sendRedirect("../");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
