package de.hska.eb.smarttravel.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import de.hska.eb.smarttravel.model.Image;

/** Was geschieht hier?
 * @author Lars
 *
 */
@Path("/images")
@Consumes("application/json")
@Produces("text/plain")
public class ImageResource {
	
	@POST
	@Path("/upload")
	public Image uploadImage() {
		return null;
	}
	
	@GET
	@Path("/test")
	public String test() {
		String res = "123 Test Test";
		return res;
	}
}