package seven.group;

import managexml.AdministrateProduct;
import managexml.ManageXML;
import managexml.Root;

import java.util.ArrayList;
import java.util.List;

class ProductService {
	 
	List<Product> getAllProducts(long marketId){
		Root root = ManageXML.ReadXML();
		if (root == null)
			return new ArrayList<>();
		// TODO: Exception handling
		for (Market market : root.getMarkets()) {
			if (market.getId() == marketId)
				return market.getProduct();
		}
		return new ArrayList<>();
	}
	
	Product getProduct(long marketId, long productId) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			return new Product();
		// TODO: Exception handling
		for (Market market : root.getMarkets()) {
			if (market.getId() == marketId)
				for (Product product : market.getProduct())
					if (product.getId() == productId)
						return product;
		}
		return new Product();
	}
	
	Product addProduct(long marketId, Product product) {
		return AdministrateProduct.AddProduct(marketId, product) ? product : new Product();
	}
	
	Product modifyProduct(long marketId, Product product) {
		return AdministrateProduct.ModifyProduct(marketId, product) ? product : new Product();
	}
	
	boolean deleteProduct(long marketId, long productId) {
		return AdministrateProduct.DeleteProduct(marketId, productId);
	}
}
