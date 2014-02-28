package eightysix250kaffe.rest.resources.open;

import eightysix250kaffe.rest.api.Participant;
import eightysix250kaffe.rest.api.ParticipantId;
import eightysix250kaffe.rest.repositories.ParticipantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
@Path("/open/kaffe/participants")
@Service
@Produces(MediaType.APPLICATION_JSON)
public class ParticipantsResource {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantsResource(@Qualifier("staticParticipantRepository") ParticipantRepository participantRepository) {
        this.participantRepository = checkNotNull(participantRepository);
    }

    @GET
    public Set<Participant> getAll() {
        return participantRepository.list();
    }

    @GET
    @Path("{id}")
    public Participant getById(@PathParam("id") ParticipantId id) {
        return participantRepository.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Participant create(@Valid Participant participant) {
        return participantRepository.save(participant);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Participant update(@Valid Participant participant) {
        return participantRepository.save(participant);
    }

}
