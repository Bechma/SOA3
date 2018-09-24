package seven.group;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientsResource 
{
	@Inject ProductResource pr;
	private ClientService clientService = new ClientService();

	@Path("/{clientId}/products")
	public ProductResource getProductResource(){
		return pr;
	}
    
    @GET
    @Path("/{clientId}")
    public Response getClient(@PathParam("clientId") long id) {
        Client client = clientService.getClient(id);
        return Response.status(Status.OK).entity(client).build();
    }
    
    @GET
    public Response getClients(@BeanParam ClientFilterBean fBean){
    	List<Client> clients;

    	if(fBean.getEmail() != null)
    	{
    		clients = new ArrayList<>();
    		clients.add(clientService.getClientForEmail(fBean.getEmail()));
    	}
    	else if(fBean.getStart() > 0)
    		clients =  clientService.getAllClientsPaginated(fBean.getStart(), fBean.getSize());
    	else 
    		clients = clientService.getAllClients();
    	
    	return Response.status(Status.OK).entity(clients).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient (Client client, @Context UriInfo uriInfo) {
    	Client newClient = clientService.addClient(client);
    	String newId = String.valueOf(newClient.getId());
    	URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri).entity(newClient).build();
    }
    
    @PUT
    @Path("/{clientId}")
    public Response modifyClient(@PathParam("clientId") long id, Client client) {
    	client.setId(id);
		Client newClient = clientService.modifyClient(client);
    	return Response.status(Status.CREATED).entity(newClient).build();
    }
    
    @DELETE
    @Path("/{clientId}")
    public Response deleteClient(@PathParam("clientId") long id) {
    	clientService.deleteClient(id);
    	return Response.status(Status.CREATED).build();
    }
}
