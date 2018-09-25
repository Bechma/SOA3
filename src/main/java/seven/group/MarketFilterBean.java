package seven.group;

import javax.ws.rs.QueryParam;

public class MarketFilterBean {
	private @QueryParam("start") int start;
	private @QueryParam("end") int end;
	private @QueryParam("location") String location;
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int size) {
		this.end = size;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
