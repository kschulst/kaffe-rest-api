package eightysix250kaffe.rest.api;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.experimental.Builder;
import org.joda.time.DateTime;

import java.util.Set;

import static com.google.common.base.Strings.isNullOrEmpty;

@Data
@Builder
public class Event {
    private final int id;
    private ParticipantId organizerId;
    private DateTime dateTime;
    private final Set<EventParticipant> participants = Sets.newHashSet();

    public Optional<EventParticipant> getParticipant(final ParticipantId id) {
        return Iterables.tryFind(participants, new Predicate<EventParticipant>() {
            @Override
			public boolean apply(EventParticipant eventParticipant) {
                return eventParticipant.getParticipantId().equals(id);
            }
        });
    }

    public Event addParticipant(Participant participant) {
        return this.addParticipant(participant, null);
    }

    public Event addParticipant(Participant participant, String orderOverride) {
        EventParticipant eventParticipant = new EventParticipant(participant);
        eventParticipant.setOrder(isNullOrEmpty(orderOverride) ? participant.getDefaultOrder() : orderOverride);
        participants.add(eventParticipant);
        return this;
    }

    public Event addParticipants(Participant... participantArray) {
        for (Participant p : participantArray) {
            this.addParticipant(p);
        }
        return this;
    }

    public EventParticipant confirm(final ParticipantId id, EventParticipant.Confirmation confirmation) {
        for (EventParticipant p : participants) {
            if (p.getParticipant().getParticipantId().equals(id)) {
                p.setConfirmed(confirmation);
                return p;
            }
        }

        return null;
    }

    public EventParticipant decline(final ParticipantId id, Optional<String> excuse) {
        EventParticipant eventParticipant = confirm(id, EventParticipant.Confirmation.NO);
        if (excuse.isPresent()) {
            eventParticipant.setExcuse(excuse.get());
        }

        return eventParticipant;
    }

    public EventParticipant accept(final ParticipantId id, Optional<String> orderOverride) {
        EventParticipant eventParticipant = confirm(id, EventParticipant.Confirmation.YES);
        if (orderOverride.isPresent()) {
            eventParticipant.setOrder(orderOverride.get());
        }

        return eventParticipant;
    }

}
