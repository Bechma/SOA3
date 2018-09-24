package seven.group;

import exceptions.DataNotFound;
import exceptions.InternalDBError;
import managexml.AdministrateMarket;
import managexml.ManageXML;
import managexml.Root;

import java.util.ArrayList;
import java.util.List;

class MarketService {
	
	List<Market> getAllMarkets(){
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		return root.getMarkets();
	}
	
	Market getMarket(long id) {
		Root root = ManageXML.ReadXML();
		if (root != null)
			for (Market market : root.getMarkets())
				if (market.getId() == id)
					return market;
		throw new DataNotFound("Market not found");
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
		if (AdministrateMarket.ModifyMarket(market))
			return market;
		throw new DataNotFound("Market not found");
	}
	
	void deleteMarket(long id) {
		if (AdministrateMarket.DeleteMarket(id))
			return ;
		throw new DataNotFound("Market not found");
	}
}
