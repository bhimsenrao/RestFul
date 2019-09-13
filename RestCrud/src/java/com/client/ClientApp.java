
//import com.sun.jersey.api.client.config.ClientConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
 public class ClientApp {
   static public void main( String[] args )
 	   throws Exception {

       
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target("http://localhost:30385/RestCrud/rest/employees");
        // Get XML
        String xmlResponse = target.path("rest").path("employee").request()
                .accept(MediaType.TEXT_XML).get(String.class); 
       System.out.println(xmlResponse);
   }
 }
