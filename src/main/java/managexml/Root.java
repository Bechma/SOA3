package managexml;

import seven.group.Market;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Root {
	private List<Market> markets;
	public Root() {markets = new ArrayList<>();}

	public Root(List<Market> markets) {
		this.markets = markets;
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
