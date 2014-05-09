package eightysix250kaffe.rest.repositories;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import eightysix250kaffe.rest.api.Participant;

@RegisterMapper(ParticipantMapper.class)
public interface ParticipantDao {

	@SqlUpdate("create table if not exists participants (id int auto_increment primary key, participant_id varchar(255), name varchar(255), email varchar(255), phone varchar(255), default_order varchar(255))")
	void createTable();

	@SqlUpdate("insert into participants (participant_id, name, email, phone, default_order) values (:participant_id, :name, :email, :phone, :default_order)")
	void insert(@Bind("participant_id") String participantId, @Bind("name") String name, @Bind("email") String email,
			@Bind("phone") String phone, @Bind("default_order") String default_order);

	@SqlQuery("select * from participants where participant_id = :participant_id")
	Participant findByParticipantId(@Bind("participant_id") String participantId);

	void close();

	@SqlQuery("select id from participants where participant_id = :participant_id")
	int findName(@Bind("participant_id") String participantId);

	@SqlQuery("select * from participants")
	List<Participant> list();

	@SqlUpdate("insert into participants (participant_id, name, email, phone, default_order) values (:participant_id, :name, :email, :phone, :default_order)")
	Participant insert(@BindBean Participant participant);

}
