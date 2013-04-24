package tw.elliot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import tw.elliot.domain.core.AbstractStrOidAuditable;

@Entity
@Table(name = "T_ROLE_DAO")
public class Role extends AbstractStrOidAuditable {
	private static final long serialVersionUID = 6636999324441873024L;
	@Column(name="NAME", length=50)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
