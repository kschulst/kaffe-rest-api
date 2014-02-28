package eightysix250kaffe.rest.resources.open;

import eightysix250kaffe.rest.repositories.ParticipantRepository;
import eightysix250kaffe.rest.resources.open.ParticipantsResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class ParticipantsResourceTest {

    private static final ParticipantRepository participantRepository = mock(ParticipantRepository.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ParticipantsResource(participantRepository))
            .build();

    @Test
    public void getById() throws Exception {


//        when(participantRepository.save(any(Participant.class))).thenReturn(new );

//        WebResource resource = resources.client().resource("/open/kaffe").queryParam("echo", "Hello");

//        String actual = ;
//        String expected = "Echo Hello";

//        assertEquals(resource.get(String.class), "expected...");
    }
}
