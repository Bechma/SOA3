package managexml;

import seven.group.Client;
import seven.group.Market;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Root {
	private List<Market> markets;
	private List<Client> clients;

	public Root() {markets = new ArrayList<>();}

	public Root(List<Market> markets) {
		this.markets = markets;
	}

	@XmlElementWrapper(name = "clients")
	@XmlElement(name = "client")
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@XmlElementWrapper(name="markets")
	@XmlElement(name = "market")
	public List<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}
}
