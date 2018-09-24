package managexml;

import exceptions.InternalDBError;
import seven.group.Client;
import seven.group.Order;

import java.util.ArrayList;
import java.util.List;

public final class AdministrateOrder {
	public static boolean AddOrder(long client, Order order) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> clients = root.getClients();
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getId() == client) {
				long id;
				try {
					id = clients.get(i).getOrder().get(clients.size() - 1).getId();
				} catch (NullPointerException npe) {
					id = 1;
				}
				order.setId(id+1);
				clients.get(i).addOrder(order);
				ManageXML.CreateXML(clients);
				return true;
			}
		}
		return false;
	}

	public static boolean ModifyOrder(long client, Order order) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> clients = root.getClients();
		for (Client client1 : clients)
			if (client1.getId() == client) {
				ArrayList<Order> orders = client1.getOrder();
				for (int i = 0; i < orders.size(); i++) {
					if (orders.get(i).getId() == order.getId()) {
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
