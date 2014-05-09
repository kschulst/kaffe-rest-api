package eightysix250kaffe.rest.resources.open;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.spring.DBIFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eightysix250kaffe.rest.api.Participant;
import eightysix250kaffe.rest.api.ParticipantId;
import eightysix250kaffe.rest.repositories.ParticipantDao;

@Path("/open/kaffe/participants")
@Service
@Produces(MediaType.APPLICATION_JSON)
public class ParticipantsResource {

	@Autowired
	private DBIFactoryBean dbiFactoryBean;

    @GET
    public List<Participant> getAll() {
    	return getDBI().open(ParticipantDao.class).list();
    }

	private DBI getDBI() {
		DBI dbi;
		try {
			dbi = (DBI) dbiFactoryBean.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return dbi;
	}

    @GET
    @Path("{id}")
    public Participant getById(@PathParam("id") ParticipantId id) {
        return getDBI().open(ParticipantDao.class).findByParticipantId(id.toString());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Participant create(@Valid Participant participant) {
        return getDBI().open(ParticipantDao.class).insert(participant);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Participant update(@Valid Participant participant) {
        return getDBI().open(ParticipantDao.class).insert(participant);
    }
    
}
