package seven.group;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;

public class Client 
{
	private long id;
	private String fullName;
	private String email;
	private ArrayList<Order> order = new ArrayList<>();
	
	public Client()
	{
		id = -1;
		fullName = "";
		email = "";
	}
	
	public Client(long id, String fullName, String email)
	{
		this.id = id;
		this.fullName = fullName;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElementWrapper(name = "orders")
	@XmlElement
	public ArrayList<Order> getOrder() {
		return order;
	}

	public void setOrder(ArrayList<Order> order) {
		this.order = order;
	}
	
	public void addOrder(Order order)
	{
		this.order.add(order);
	}
	
}
