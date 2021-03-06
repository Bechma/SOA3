package seven.group;

import exceptions.InternalDBError;
import managexml.ManageXML;
import managexml.Root;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import java.security.Key;
import java.util.List;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private String email = "";

	private boolean isAdmin(ContainerRequestContext requestContext) {
		//Get token
		String jwt = requestContext.getHeaderString("token");
		if (jwt == null)
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());

		Key key;

		key = LoginResource.getKey();
		if (key == null)
			requestContext.abortWith(Response.status(Status.INTERNAL_SERVER_ERROR).build());

		//Generate consumer
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setVerificationKey(key).build();

		//Check the token and check the groups
		try {
			JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
			List<String> groups = jwtClaims.getStringListClaimValue("groups");
			email = jwtClaims.getSubject();
			return groups.contains("admin");

		} catch(InvalidJwtException | MalformedClaimException e){
			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
		}
		return false;
	}

	@Override
	public void filter(ContainerRequestContext requestContext) {

		//If the user tries to use a restricted method
		String method = requestContext.getMethod();

		if (method.equals("DELETE") || method.equals("PUT") || method.equals("POST")) {
			if (!isAdmin(requestContext))
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());

		} else if (method.equals("GET") && requestContext.getUriInfo().getPath().contains("clients") && requestContext.getHeaderString("token") != null && !isAdmin(requestContext)) {
			Root root = ManageXML.ReadXML();
			long idClient = -1;
			if (root == null)
				throw new InternalDBError("DB error");
			for (Client c : root.getClients()) {
				if (c.getEmail().equals(email)) {
					idClient = c.getId();
					break;
				}
			}
			if (!requestContext.getUriInfo().getPath().contains("clients/" + idClient)) {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			}
		}
	}
}
