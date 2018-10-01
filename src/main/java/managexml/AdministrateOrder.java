package managexml;

import exceptions.InternalDBError;
import seven.group.Client;
import seven.group.MarketsResource;
import seven.group.Order;
import seven.group.OrderResource;

import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

public final class AdministrateOrder {
	private static void links(long client, Order order, UriInfo uriInfo) {
		order.setSelfLink(uriInfo
				.getBaseUriBuilder()
				.path(OrderResource.class)
				.path(OrderResource.class, "getOrder")
				.resolveTemplate("clientId", client)
				.resolveTemplate("orderId", order.getId())
				.build()
				.toString());

		order.setMarketLink(uriInfo
				.getBaseUriBuilder()
				.path(MarketsResource.class)
				.path(MarketsResource.class, "getMarket")
				.resolveTemplate("marketId", order.getMarketId())
				.build()
				.toString());
	}

	public static boolean AddOrder(long client, Order order, UriInfo uriInfo) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> clients = root.getClients();
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getId() == client) {
				long id;
				try {
					id = clients.get(i).getOrder().get(clients.size() - 1).getId();
					id = id + 1;
				} catch (NullPointerException | IndexOutOfBoundsException npe) {
					id = 1;
				}
				order.setId(id+1);
				links(client, order, uriInfo);

				for (int j = 0; j < order.getProduct().size(); j++){
					AdministrateProduct.links(order.getMarketId(), order.getProduct().get(j),uriInfo);
				}

				clients.get(i).addOrder(order);
				ManageXML.CreateXML(clients);
				return true;
			}
		}
		return false;
	}

	public static boolean ModifyOrder(long client, Order order, UriInfo uriInfo) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> clients = root.getClients();
		for (Client client1 : clients)
			if (client1.getId() == client) {
				ArrayList<Order> orders = client1.getOrder();
				for (int i = 0; i < orders.size(); i++) {
					if (orders.get(i).getId() == order.getId()) {
						links(client, order, uriInfo);
						orders.set(i, order);
						ManageXML.CreateXML(clients);
						return true;
					}
				}
			}
		return false;
	}

	public static boolean DeleteOrder(long clientId, long orderId) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> clients = root.getClients();
		for (Client client : clients) {
			if (client.getId() == clientId) {
				ArrayList<Order> orders = client.getOrder();
				for (int i = 0; i < orders.size(); i++)
					if (orders.get(i).getId() == orderId){
						orders.remove(i);
						ManageXML.CreateXML(clients);
						return true;
					}
			}
		}
		return false;
	}
}
