package seven.group;

import exceptions.DataNotFound;
import exceptions.InternalDBError;
import managexml.AdministrateClient;
import managexml.ManageXML;
import managexml.Root;

import java.util.ArrayList;
import java.util.List;

class ClientService {
	List<Client> getAllClients() {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		return root.getClients();
	}
	
	Client getClient(long id) {
		Root root = ManageXML.ReadXML();
		if(root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		for(Client client : root.getClients())
			if(client.getId() == id)
				return client;
		throw new DataNotFound("Client not found");
	}
	
	Client getClientForEmail(String email) {
		Root root = ManageXML.ReadXML();
		if(root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		for (Client client : root.getClients())
			if(client.getEmail().equals(email))
				return client;
		throw new DataNotFound("This email is not from one of our clients");
	}
	
	List<Client> getAllClientsPaginated(int start, int size) {
		List<Client> clients = new ArrayList<>();
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		List<Client> temp = root.getClients();
		for(int i = start; i < size && i < temp.size(); ++i) {
			clients.add(temp.get(i));
		}
		return clients;
	}
	
	Client addClient(Client client) {
		if (AdministrateClient.AddClient(client))
			return client;
		return new Client();
	}
	
	Client modifyClient(Client client) {
		if (AdministrateClient.ModifyClient(client))
			return client;
		throw new DataNotFound("Client not found");
	}
	
	void deleteClient(long id) {
		if (AdministrateClient.DeleteClient(id))
			return ;
		throw new DataNotFound("Client not found");
	}
}
