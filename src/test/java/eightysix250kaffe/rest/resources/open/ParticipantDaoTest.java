package eightysix250kaffe.rest.resources.open;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.spring.DBIFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eightysix250kaffe.rest.api.Participant;
import eightysix250kaffe.rest.repositories.ParticipantDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring/kaffe-rest-api-spring-resources.xml" })
public class ParticipantDaoTest {

	@Autowired
	private DBIFactoryBean dbiFactory;

	@Autowired
	private ParticipantsResource participantsResource;

	private ParticipantDao dao;

	@Before
	public void setup() throws Exception {
		dao = ((DBI) dbiFactory.getObject()).open(ParticipantDao.class);
		dao.createTable();
	}

	@Test
	@Ignore
	public void should_create_and_read_a_participant() throws Exception {
		dao.insert("Q7E", "Mats", "q7e@storebrand.no", "92837465", "Mats latte");
		Participant participant = dao.findByParticipantId("Q7E");
		dao.close();

		assertThat(participant.getName()).isEqualTo("Mats");
		assertThat(participant.getParticipantId().toString()).isEqualTo("Q7E");
		assertThat(participant.getEmail()).isEqualTo("q7e@storebrand.no");
		assertThat(participant.getPhone()).isEqualTo("92837465");
		assertThat(participant.getDefaultOrder()).isEqualTo("Mats latte");
	}

}
