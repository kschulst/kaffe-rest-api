package eightysix250kaffe.rest.repositories;

import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import eightysix250kaffe.rest.api.Participant;
import eightysix250kaffe.rest.api.ParticipantId;

@Slf4j
@Repository
public class StaticParticipantRepository implements ParticipantRepository {

    public static final Participant KENNETH = Participant.builder().participantId(new ParticipantId("p74")).name("Kenneth").phone("93481208").email("ks@udp.no").defaultOrder("Black as the sky on a moonless night").build();
    public static final Participant MATS = Participant.builder().participantId(new ParticipantId("q7e")).name("Mats").phone("91776249").defaultOrder("Mats Latte").build();

    private static final ImmutableMap<ParticipantId, Participant> PARTICIPANTS =
         new ImmutableMap.Builder<ParticipantId, Participant>()
             .put(KENNETH.getParticipantId(), KENNETH)
             .put(MATS.getParticipantId(), MATS)
    .build();

    @Override
    public Participant findById(ParticipantId id) {
        return PARTICIPANTS.get(id);
    }

    @Override
    public Set<Participant> list() {
        return Sets.newLinkedHashSet(PARTICIPANTS.values());
    }

    @Override
    public Participant save(Participant participant) {
        StaticParticipantRepository.log.info("Saving participant: {}", participant);
        // TODO: implement this
        return participant;
    }

    @Override
    public boolean delete(Participant participant) {
        StaticParticipantRepository.log.info("Deleting participant: {}", participant);
        // TODO: implement this
        return PARTICIPANTS.containsValue(participant);
    }
    
}
