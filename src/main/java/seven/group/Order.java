package seven.group;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Order 
{
	private long id;
	private long marketId;
	private String marketLink;
	private String selfLink;
	private ArrayList<Product> product = new ArrayList<>();

	public String getMarketLink() {
		return marketLink;
	}

	public void setMarketLink(String marketLink) {
		this.marketLink = marketLink;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public void setSelfLink(String selfLink) {
		this.selfLink = selfLink;
	}

	public Order()
	{
		id = -1;
		marketId = -1;
	}
	
	public Order(long id, long marketId)
	{
		this.id = id;
		this.marketId = marketId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMarketId() {
		return marketId;
	}

	public void setMarketId(long marketId) {
		this.marketId = marketId;
	}

	@XmlElementWrapper(name="products")
	@XmlElement
	public ArrayList<Product> getProduct() {
		return product;
	}

	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}
	
	public void addProduct(Product product)
	{
		this.product.add(product);
	}
}
