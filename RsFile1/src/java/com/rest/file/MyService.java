/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/file")
public class MyService {
    @Path("/get")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String getInfo(
            @FormDataParam("txt1") 
                    InputStream uploadedInputStream,
            @FormDataParam("txt1") 
                    FormDataContentDisposition fileDetail
    ) {
        String filename = "e:/tmp/" + fileDetail.getFileName();
        try {
            FileOutputStream out
                    = new FileOutputStream(new File(filename));
            int read = 0;
            byte[] bytes = new byte[1024];
            System.out.println("Buffered");
            out = new FileOutputStream(new File(filename));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

        return "Hello!" + fileDetail.getFileName();
    }

    @GET
    @Path("/download")
    @Produces(MediaType.TEXT_HTML)
    public Response download() {
        Response.ResponseBuilder builder = 
                Response.ok((Object) (
                        new java.io.File("e:/file.txt")));
        builder.header("Content-Disposition", "attachment;"
                + " filename=\"file.txt\"");
        return builder.build();
    }
}
