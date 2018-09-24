package seven.group;

import javax.ws.rs.QueryParam;

public class ClientFilterBean 
{
	private @QueryParam("start") int start;
	private @QueryParam("size") int size;
	private @QueryParam("name") String name;
	private @QueryParam("email") String email;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
