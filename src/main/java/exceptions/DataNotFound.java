package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class DataNotFound extends WebApplicationException {
	public DataNotFound(String message) {
		super(Response.status(Response.Status.NOT_FOUND)
				.entity(new ErrorMessage(message,404))
				.build());
	}
}
