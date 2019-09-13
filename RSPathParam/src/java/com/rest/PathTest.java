package com.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/view")
public class PathTest {
 @GET
 @Path("/{first}/{second}")
 @Produces(MediaType.TEXT_PLAIN)
 public String sum(@PathParam("first") int a,
         @PathParam("second") int b){
 return "Sum of Numbers="+(a+b);
 }
 @GET
 @Path("validate/{username}")
 @Produces(MediaType.TEXT_HTML)
 public String validate(@PathParam("username") String user){
   if(user.length()>=5){
     return "Valid username "; 
   }
   else
       return "Invalid username";
 }
}