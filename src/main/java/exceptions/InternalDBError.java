package exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class InternalDBError extends WebApplicationException {
	public InternalDBError(String message) {
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(new ErrorMessage(message,500))
				.build());
	}
}
