package eightysix250kaffe.rest.api;

import lombok.Data;
import lombok.experimental.Builder;

import static com.google.common.base.Objects.firstNonNull;


@Data
@Builder
public class Participant {
    private final ParticipantId id;
    private final String name;
    private final String email;
    private final String phone;
    private final String defaultOrder;

    public String getEmail() {
        return firstNonNull(email, id + "@storebrand.no");
    }
}
