package seven.group;

import exceptions.DataNotFound;
import exceptions.InternalDBError;
import managexml.AdministrateOrder;
import managexml.ManageXML;
import managexml.Root;

import javax.ws.rs.core.UriInfo;
import java.util.List;

class OrderService {
	List<Order> getAllOrders(long clientId) {
		Root root = ManageXML.ReadXML();
		if(root != null) {
			List<Client> clients = root.getClients();
			for (Client client : clients)
				if (client.getId() == clientId) {
					return client.getOrder();
				}
		}
		throw new InternalDBError("There is a problem with our database, please try again in a moment");
	}

	
	Order getOrder(long clientId, long orderId) {
		Root root = ManageXML.ReadXML();
		if(root != null) {
			Client client = new Client();
			List<Client> clients = root.getClients();
			for (Client client1 : clients) {
				if (client1.getId() == clientId) {
					List<Order> orders = client1.getOrder();
					for (Order order : orders)
						if (order.getId() == orderId)
							return order;
				}
			}
			throw new DataNotFound("Order not found");
		}
		throw new InternalDBError("There is a problem with our database, please try again in a moment");
	}
	
	Order addOrder(Order order, long clientId, UriInfo uriInfo) {
		return AdministrateOrder.AddOrder(clientId, order, uriInfo) ? order : new Order();
	}
	
	Order modifyOrder(Order order, long clientId, UriInfo uriInfo) {
		if(AdministrateOrder.ModifyOrder(clientId, order, uriInfo))
			return order;
		throw new DataNotFound("Order not found");
	}
	
	void deleteOrder(long clientId, long orderId) {
		if (AdministrateOrder.DeleteOrder(clientId, orderId))
			return ;
		throw new DataNotFound("Order not found");
	}
}
