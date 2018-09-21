package seven.group;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private long id;
	private String name;
	private double price;
	
	public Product() {
		id = -1;
		name = "";
		price = -1.0;
	}
	
	public Product(long id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
