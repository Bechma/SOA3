package managexml;

import seven.group.Market;

import java.util.List;

public final class AdministrateMarket {
	public static boolean AddMarket(Market market) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;

		List<Market> markets = root.getMarkets();
		long id = markets.get(markets.size()-1).getId();
		market.setId(id+1);

		markets.add(market);
		ManageXML.CreateXML(markets);
		return true;
	}

	public static boolean ModifyMarket(Market market) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (int i = 0; i < markets.size(); i++)
			if (markets.get(i).getId() == market.getId()) {
				markets.set(i, market);
				ManageXML.CreateXML(markets);
				return true;
			}
		return false;
	}


	public static boolean DeleteMarket(long id) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (int i = 0; i < markets.size(); i++) {
			if (markets.get(i).getId() == id) {
				markets.remove(i);
				ManageXML.CreateXML(markets);
				return true;
			}
		}
		return false;
	}
}
