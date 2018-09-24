package seven.group;

import exceptions.DataNotFound;
import exceptions.InternalDBError;
import managexml.AdministrateProduct;
import managexml.ManageXML;
import managexml.Root;

import java.util.ArrayList;
import java.util.List;

class ProductService {
	 
	List<Product> getAllProducts(long marketId){
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		for (Market market : root.getMarkets()) {
			if (market.getId() == marketId)
				return market.getProduct();
		}
		return new ArrayList<>();
	}
	
	Product getProduct(long marketId, long productId) {
		Root root = ManageXML.ReadXML();
		if (root == null)
			throw new InternalDBError("There is a problem with our database, please try again in a moment");
		for (Market market : root.getMarkets()) {
			if (market.getId() == marketId)
				for (Product product : market.getProduct())
					if (product.getId() == productId)
						return product;
		}
		throw new DataNotFound("Product not found");
	}
	
	Product addProduct(long marketId, Product product) {
		return AdministrateProduct.AddProduct(marketId, product) ? product : new Product();
	}
	
	Product modifyProduct(long marketId, Product product) {
		if (AdministrateProduct.ModifyProduct(marketId, product))
			return product;
		throw new DataNotFound("Product not found");
	}
	
	void deleteProduct(long marketId, long productId) {
		if (AdministrateProduct.DeleteProduct(marketId, productId))
			return ;
		throw new DataNotFound("Product not found");
	}
}
