package seven.group;

import managexml.ManageXML;
import managexml.Root;

import java.util.ArrayList;
import java.util.List;

public class MarketService {
	
	public List<Market> getAllMarkets(){
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		return root != null ? root.getMarkets() : null;
	}
	
	public Market getMarket(long id) {
		Root root = ManageXML.ReadXML();
		if (root != null)
			for (Market market : root.getMarkets())
				if (market.getId() == id)
					return market;
		//TODO
		return new Market();
	}
	
	public List<Market> getAllMarketsForLocation(String location){
		ArrayList<Market> markets = new ArrayList<Market>();
		//TODO
		return markets;
	}
	
	public List<Market> getAllMarketsPaginated(int start, int size){
		ArrayList<Market> markets = new ArrayList<>();
		//TODO
		return markets;
	}
	
	public Market addMarket(Market market) {
		// TODO: Exception handling
		ManageXML.AddMarket(market);
		return market;
	}
	
	public Market modifyMarket(long id, Market market) {
		// TODO: Exception handling
		ManageXML.ModifyMarket(market);
		return market;
	}
	
	public void deleteMarket(long id) {
		//TODO
		ManageXML.DeleteMarket(id);
	}
}
