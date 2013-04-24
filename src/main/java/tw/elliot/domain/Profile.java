package tw.elliot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import tw.elliot.domain.core.AbstractStrOidAuditable;

@Entity
@Table(name = "T_PROFILE_DAO")
public class Profile extends AbstractStrOidAuditable {
	private static final long serialVersionUID = -8019838372522130596L;

	@Column(name = "ADDRESS", length = 50)
	private String address;

	@Type(type = "yes_no")
	@Column(name = "ADMIN", length = 1)
	private Boolean admin;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
