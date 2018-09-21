package seven.group;

import javax.ws.rs.QueryParam;

public class MarketFilterBean {
	private @QueryParam("start") int start;
	private @QueryParam("size") int size;
	private @QueryParam("location") String location;
	
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
