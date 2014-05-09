package eightysix250kaffe.rest.resources.open;

import static org.mockito.Mockito.mock;
import io.dropwizard.testing.junit.ResourceTestRule;

import org.junit.Test;

import eightysix250kaffe.rest.repositories.ParticipantRepository;

public class ParticipantsResourceTest {

    private static final ParticipantRepository participantRepository = mock(ParticipantRepository.class);

    //@ClassRule
//    public static final ResourceTestRule resources = ResourceTestRule.builder()
//            .addResource(new ParticipantsResource(participantRepository))
//            .build();

    @Test
    public void getById() throws Exception {


//        when(participantRepository.save(any(Participant.class))).thenReturn(new );

//        WebResource resource = resources.client().resource("/open/kaffe").queryParam("echo", "Hello");

//        String actual = ;
//        String expected = "Echo Hello";

//        assertEquals(resource.get(String.class), "expected...");
    }
}
