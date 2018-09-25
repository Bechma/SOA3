package seven.group;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path("/markets")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  MarketsResource{
	@Inject ProductResource pr;
	private MarketService marketService = new MarketService();

	@Path("/{marketId}/products")
	public ProductResource getProductResource(){
		return pr;
	}
    
    @GET
    @Path("/{marketId}")
    public Response getMarket(@PathParam("marketId") long id) {
		Market market;
		market = marketService.getMarket(id);
        return Response.status(Status.OK).entity(market).build();
    }
    
    @GET
    public Response getMarkets(@BeanParam MarketFilterBean fBean){
    	List<Market> markets;

    	if(fBean.getLocation() != null)
    		markets =  marketService.getAllMarketsForLocation(fBean.getLocation());
    	else if(fBean.getStart() > 0)
    		markets =  marketService.getAllMarketsPaginated(fBean.getStart(), fBean.getSize());
    	else 
    		markets = marketService.getAllMarkets();
    	
    	return Response.status(Status.OK).entity(markets).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMarket (Market market, @Context UriInfo uriInfo) {
    	Market newMarket = marketService.addMarket(market);
    	String newId = String.valueOf(newMarket.getId());
    	URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
    	return Response.created(uri).entity(newMarket).build();
    }
    
    @PUT
    @Path("/{marketId}")
    public Response modifyMarket(@PathParam("marketId") long id, Market market) {
    	market.setId(id);
		Market newMarket = marketService.modifyMarket(market);
    	return Response.status(Status.OK).entity(newMarket).build();
    }
    
    @DELETE
    @Path("/{marketId}")
    public Response deleteMarket(@PathParam("marketId") long id) {
    	marketService.deleteMarket(id);
    	return Response.status(Status.OK).build();
    }
    
    
}
