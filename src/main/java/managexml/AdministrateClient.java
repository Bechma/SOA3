package managexml;

import exceptions.InternalDBError;
import seven.group.Client;

import java.util.List;

public final class AdministrateClient 
{
	public static boolean AddClient(Client client)
	{
		Root root = ManageXML.ReadXML();
		if(root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		
		List<Client> clients = root.getClients();

		long id;
		try {
			id = clients.get(clients.size() - 1).getId();
			id = id + 1;
		} catch (NullPointerException | IndexOutOfBoundsException npe){
			id = 1;
		}
		client.setId(id);
		clients.add(client);
		ManageXML.CreateXML(clients);
		return true;
	}
	
	public static boolean ModifyClient(Client client)
	{
		Root root = ManageXML.ReadXML();
		if(root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		
		List<Client> clients = root.getClients();
		for(int i = 0; i < clients.size(); ++i)
			if(clients.get(i).getId() == client.getId()) {
				clients.set(i, client);
				ManageXML.CreateXML(clients);
				return true;
			}
		return false;
	}
	
	public static boolean DeleteClient(long id)
	{
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> clients = root.getClients();
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getId() == id) {
				clients.remove(i);
				ManageXML.CreateXML(clients);
				return true;
			}
		}
		return false;
	}
}
