package eightysix250kaffe.rest.repositories;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import eightysix250kaffe.rest.api.Event;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Slf4j
@Repository
public class StaticEventRepository implements EventRepository {

    private static final ImmutableMap<String, Event> COFFEE_EVENTS =
         new ImmutableMap.Builder<String, Event>()
             .put("1", Event.builder().id("1").dateTime(new DateTime("2014-02-14T08:45:00Z")).organizerId(StaticParticipantRepository.MATS.getId()).build().addParticipants(StaticParticipantRepository.KENNETH, StaticParticipantRepository.MATS))
             .put("2", Event.builder().id("2").dateTime(new DateTime("2014-02-21T08:45:00Z")).organizerId(StaticParticipantRepository.KENNETH.getId()).build().addParticipants(StaticParticipantRepository.MATS, StaticParticipantRepository.KENNETH))
             .build();

    @Override
    public Event findById(String id) {
        return COFFEE_EVENTS.get(id);
    }

    @Override
    public Set<Event> list() {
        return Sets.newLinkedHashSet(COFFEE_EVENTS.values());
    }

    @Override
    public Event save(Event event) {
        StaticEventRepository.log.info("Saving event: {}", event);
        // TODO: implement this
        return event;
    }

    @Override
    public boolean delete(Event event) {
        StaticEventRepository.log.info("Deleting event: {}", event);
        // TODO: implement this
        return COFFEE_EVENTS.containsValue(event);
    }
}
