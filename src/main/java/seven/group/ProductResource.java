package seven.group;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/markets/{marketId}/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	private ProductService productService = new ProductService();

	@GET
	public Response getProducts(@PathParam("marketId") long marketId) {
		List<Product> products = productService.getAllProducts(marketId);
		return Response.status(Status.OK).entity(products).build();
	}
	
	@GET
	@Path("/{productId}")
	public Response getProduct(@PathParam("marketId") long marketId, @PathParam("productId") long productId) {
		Product product = productService.getProduct(marketId, productId);
		return Response.status(Status.OK).entity(product).build();
	}
	
	@POST
	public Response addProduct(Product product, @PathParam("marketId") long marketId, @Context UriInfo uriInfo) {
		Product newProduct = productService.addProduct(marketId, product, uriInfo);
		String newId = String.valueOf(newProduct.getId());
		URI uri = uriInfo.getBaseUriBuilder().path(newId).build();
		return Response.created(uri).entity(newProduct).build();
	}
	
	@PUT
	@Path("/{productId}")
	public Response modifyProduct(Product product, @PathParam("marketId") long marketId, @PathParam("productId") long productId, @Context UriInfo uriInfo) {
		product.setId(productId);
		Product newProduct = productService.modifyProduct(marketId, product, uriInfo);
		return Response.status(Status.OK).entity(newProduct).build();
	}
	
	@DELETE
	@Path("/{productId}")
	public Response deleteProduct(@PathParam("marketId") long marketId, @PathParam("productId") long productId) {
		productService.deleteProduct(marketId, productId);
		return Response.status(Status.OK).entity("Product removed").build();
	}
}
