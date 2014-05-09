package eightysix250kaffe.rest.resources.open;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.sql.DataSource;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;

import eightysix250kaffe.rest.api.Event;
import eightysix250kaffe.rest.api.EventParticipant;
import eightysix250kaffe.rest.api.ParticipantId;
import eightysix250kaffe.rest.repositories.EventRepository;

@Path("/open/kaffe/events")
@Service
@Produces(MediaType.APPLICATION_JSON)
public class EventsResource {

	private final EventRepository eventRepository;

	@Autowired
	private DataSource dataSource;

	@Autowired
	public EventsResource(@Qualifier("staticEventRepository") EventRepository eventRepository) {
		this.eventRepository = checkNotNull(eventRepository);
	}

	@GET
	public Set<Event> getAll() {
		return eventRepository.list();
	}

	@GET
	@Path("{id}")
	public Event getById(@PathParam("id") String id) {
		return eventRepository.findById(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Event create(@Valid Event event) {
		return eventRepository.save(event);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Event update(@Valid Event event) {
		return eventRepository.save(event);
	}

	@GET
	@Path("{eventId}/participants")
	public Set<EventParticipant> getEventParticipants(@PathParam("eventId") String eventId) {
		Event event = eventRepository.findById(eventId);
		return event.getParticipants();
	}

	@GET
	@Path("{eventId}/participants/{participantId}")
	public EventParticipant getEventParticipant(@PathParam("eventId") String eventId,
			@PathParam("participantId") ParticipantId participantId) {
		Event event = eventRepository.findById(eventId);
		return event.getParticipant(participantId).orNull();
	}

	@PUT
	@Path("{eventId}/participants/{participantId}/decline")
	public Event decline(@PathParam("eventId") String eventId, @PathParam("participantId") ParticipantId participantId,
			@QueryParam("excuse") Optional<String> excuse) {
		Event event = eventRepository.findById(eventId);
		event.decline(participantId, excuse);
		return eventRepository.save(event);
	}

	@PUT
	@Path("{eventId}/participants/{participantId}/accept")
	public Event accept(@PathParam("eventId") String eventId, @PathParam("participantId") ParticipantId participantId,
			@QueryParam("coffee") Optional<String> orderOverride) {
		Event event = eventRepository.findById(eventId);
		event.accept(participantId, orderOverride);
		return eventRepository.save(event);
	}

}
