package seven.group;

import managexml.AdministrateMarket;
import managexml.ManageXML;
import managexml.Root;

import java.util.ArrayList;
import java.util.List;

class MarketService {
	
	List<Market> getAllMarkets(){
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		return root != null ? root.getMarkets() : null;
	}
	
	Market getMarket(long id) {
		Root root = ManageXML.ReadXML();
		if (root != null)
			for (Market market : root.getMarkets())
				if (market.getId() == id)
					return market;
		//TODO
		return new Market();
	}
	
	List<Market> getAllMarketsForLocation(String location){
		List<Market> markets = new ArrayList<>();
		Root root = ManageXML.ReadXML();
		if (root != null)
			for (Market market : root.getMarkets())
				if (market.getLocation().equals(location))
					markets.add(market);
		return markets;
	}
	
	List<Market> getAllMarketsPaginated(int start, int size){
		List<Market> markets = new ArrayList<>();
		Root root = ManageXML.ReadXML();
		if (root != null) {
			List<Market> temp = root.getMarkets();
			for (int i = start; i < size && i < temp.size(); i++) {
				markets.add(temp.get(i));
			}
		}
		return markets;
	}
	
	Market addMarket(Market market) {
		// TODO: Exception handling
		AdministrateMarket.AddMarket(market);
		return market;
	}
	
	Market modifyMarket(Market market) {
		// TODO: Exception handling
		AdministrateMarket.ModifyMarket(market);
		return market;
	}
	
	void deleteMarket(long id) {
		// TODO: Exception handling
		AdministrateMarket.DeleteMarket(id);
	}
}
