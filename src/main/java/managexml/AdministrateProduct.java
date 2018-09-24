package managexml;

import seven.group.Market;
import seven.group.Product;

import java.util.ArrayList;
import java.util.List;

public final class AdministrateProduct {
	public static boolean AddProduct(long market, Product product) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (int i = 0; i < markets.size(); i++) {
			if (markets.get(i).getId() == market) {
				long id;
				try {
					ArrayList<Product> products = markets.get(i).getProduct();
					id = products.get(products.size() - 1).getId();
				} catch (NullPointerException | IndexOutOfBoundsException npe) {
					id = 1;
				}
				product.setId(id+1);
				markets.get(i).addProduct(product);
				ManageXML.CreateXML(markets);
				return true;
			}
		}
		return false;
	}

	public static boolean ModifyProduct(long market, Product product) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (Market market1 : markets)
			if (market1.getId() == market) {
				ArrayList<Product> products = market1.getProduct();
				for (int i = 0; i < products.size(); i++) {
					if (products.get(i).getId() == product.getId()) {
						products.set(i, product);
						ManageXML.CreateXML(markets);
						return true;
					}
				}
			}
		return false;
	}

	public static boolean DeleteProduct(long marketId, long productId) {
		Root root = ManageXML.ReadXML();
		// TODO: Exception handling
		if (root == null)
			return false;
		List<Market> markets = root.getMarkets();
		for (Market market : markets) {
			if (market.getId() == marketId) {
				ArrayList<Product> products = market.getProduct();
				for (int i = 0; i < products.size(); i++)
					if (products.get(i).getId() == productId){
						products.remove(i);
						ManageXML.CreateXML(markets);
						return true;
					}
			}
		}
		return false;
	}
}
