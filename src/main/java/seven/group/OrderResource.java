package seven.group;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("clients/{clientId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource 
{
	private OrderService orderService = new OrderService();
	
	@GET
	public Response getOrders(@PathParam("clientId")long clientId) {
		List<Order> orders = orderService.getAllOrders(clientId);
		return Response.status(Status.OK).entity(orders).build();
	}
	
	@GET
	@Path("/{orderId}")
	public Response getOrder(@PathParam("clientId") long clientId, @PathParam("orderId") long orderId) {
		Order order = orderService.getOrder(clientId, orderId);
		return Response.status(Status.OK).entity(order).build();
	}
	
	@POST
	public Response addOrder(Order order, @PathParam("clientId")long clientId, @Context UriInfo uriInfo) {
		Order newOrder = orderService.addOrder(order, clientId);
		String newId = String.valueOf(newOrder.getId());
		URI uri = uriInfo.getBaseUriBuilder().path(newId).build();
		return Response.created(uri).entity(newOrder).build();
	}
	
	@PUT
	@Path("/{orderId}")
	public Response modifyOrder(Order order, @PathParam("clientId")long clientId, @PathParam("orderId")long orderId) {
		order.setId(orderId);
		Order newOrder = orderService.modifyOrder(order, clientId);
		return Response.status(Status.OK).entity(newOrder).build();
	}
	
	@DELETE
	@Path("/{orderId}")
	public Response deleteOrder(@PathParam("clientId")long clientId, @PathParam("orderId")long orderId) {
		orderService.deleteOrder(clientId, orderId);
		return Response.status(Status.OK).build();
	}
}
