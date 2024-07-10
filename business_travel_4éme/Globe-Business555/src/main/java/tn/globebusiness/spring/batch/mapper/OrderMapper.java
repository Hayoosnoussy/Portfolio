package tn.globebusiness.spring.batch.mapper;

import tn.globebusiness.spring.Entities.Invitation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Invitation> {
	@Override
	public Invitation mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Invitation.builder().id(rs.getLong("id")).date(rs.getDate("date"))
				.destinataireEmail(rs.getString("destinataire_email")).sentStatus(rs.getBoolean("sent_status"))

				.build();
	}
}
