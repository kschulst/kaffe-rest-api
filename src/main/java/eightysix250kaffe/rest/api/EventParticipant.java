package eightysix250kaffe.rest.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

import static com.google.common.base.Strings.isNullOrEmpty;

@Data
public class EventParticipant {

    public enum Confirmation {
        YES, NO, NOT_ANSWERED;
    }

    @NonNull
    private final Participant participant;

    private String order;

    private Confirmation confirmed = Confirmation.NOT_ANSWERED;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String excuse;

    @JsonIgnore
    public ParticipantId getParticipantId() {
        return participant.getId();
    }

    public boolean isOrderOverride() {
        return ! isNullOrEmpty(order) && ! order.equals(participant.getDefaultOrder());
    }
}
