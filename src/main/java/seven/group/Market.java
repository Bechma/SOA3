package seven.group;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Market {
	private long id;
	private String name;
	private String location;
	private ArrayList<Product> product = new ArrayList<>();
	
	public Market() {
		id = -1;
		name = "";
		location = "";
	}
	
	public Market(long id, String name, String location){
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@XmlElementWrapper(name="products")
	@XmlElement
	public ArrayList<Product> getProduct() {
		return product;
	}
	public void setProduct(ArrayList<Product> product) {
		this.product = product;
	}

	public void addProduct(Product product) {
		this.product.add(product);
	}
}
