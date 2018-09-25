package managexml;

import exceptions.InternalDBError;
import seven.group.Market;

import java.util.List;

public final class AdministrateMarket {
	public static boolean AddMarket(Market market) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");

		List<Market> markets = root.getMarkets();
		long id;
		try{
			id = markets.get(markets.size()-1).getId();
			id = id + 1;
		} catch (NullPointerException | IndexOutOfBoundsException npe)  {
			id = 1;
		}
		market.setId(id);

		markets.add(market);
		ManageXML.CreateXML(markets);
		return true;
	}

	public static boolean ModifyMarket(Market market) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");

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
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");

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
