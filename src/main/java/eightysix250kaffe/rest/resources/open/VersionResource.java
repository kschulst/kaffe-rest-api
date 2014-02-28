package eightysix250kaffe.rest.resources.open;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

@Slf4j
@Path("/open/kaffe/version")
@Service
@Produces(MediaType.APPLICATION_JSON)
public class VersionResource {

    @GET
    public String get() {
        URL url = Resources.getResource("version.txt");
        try {
            return Resources.toString(url, Charsets.UTF_8);
        }
        catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
