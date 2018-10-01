package seven.group;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.security.Key;
import java.util.Arrays;
import java.util.List;

@Path("/login")
@Singleton
public class LoginResource {
	
	private static RsaJsonWebKey webKey;
	
	public LoginResource(){
		//Generate webKey

	}

	static Key getKey() {
		if (webKey == null) {
			try {
				webKey = RsaJwkGenerator.generateJwk(2048);
			} catch (JoseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return webKey.getKey();
	}
	
	@GET
	public Response getToken(@Context HttpHeaders headers) {
		
		/*
		 * GET USER AND PASSWORD 
		 * GET LIST OF GROUPS OF THE USER
		 * GENERATE TOKEN WITH THIS GROUPS AND USER INFO
		 * 
		 */
		
		//Check credentials
		String email = headers.getHeaderString("email");
		String pass = headers.getHeaderString("pass");

		if (email == null || pass == null)
			return Response.status(Status.UNAUTHORIZED).build();

		if (!email.equals("raulsf66@gmail.com") || !pass.equals("112233"))
			return Response.status(Status.UNAUTHORIZED).build();
		
		//Create payload
		JwtClaims claims = new JwtClaims();
		claims.setSubject(email);
		List<String> groups = Arrays.asList("user", "admin");
		claims.setStringListClaim("groups", groups);
		
		//Create web signature
		JsonWebSignature jws = new JsonWebSignature();
		
		jws.setPayload(claims.toJson());
		jws.setKey(webKey.getPrivateKey());
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		
		//Generate Token
		String jwt = null;
		try {
			jwt = jws.getCompactSerialization();
		} catch (JoseException e) {
			e.printStackTrace();
		}
		
		return Response.status(Status.OK).header("token", jwt).build();
	}
}
