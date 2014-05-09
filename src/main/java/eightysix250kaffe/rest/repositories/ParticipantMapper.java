package eightysix250kaffe.rest.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import eightysix250kaffe.rest.api.Participant;
import eightysix250kaffe.rest.api.ParticipantId;

public class ParticipantMapper implements ResultSetMapper<Participant> {

	@Override
	public Participant map(int index, ResultSet rs, StatementContext ctx) throws SQLException {
		return Participant.builder().id(rs.getInt("id"))
				.participantId(new ParticipantId(rs.getString("participant_id"))).name(rs.getString("name"))
				.email(rs.getString("email")).phone(rs.getString("phone")).defaultOrder(rs.getString("default_order"))
				.build();
	}
}
