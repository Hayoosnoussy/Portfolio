package tn.globebusiness.spring.Entities;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class InvitationFieldSetMapper implements FieldSetMapper<Invitation> {
	@Override
	public Invitation mapFieldSet(FieldSet fieldSet) {
		return Invitation.builder().id(fieldSet.readLong(0)).date(fieldSet.readDate(1))

				.destinataireEmail(fieldSet.readString(2)).sentStatus(fieldSet.readBoolean(3)).build();
	}

}
