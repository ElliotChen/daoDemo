package tw.elliot.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import tw.elliot.domain.core.AbstractStrOidAuditable;

@Entity
@Table(name = "T_USER_DAO")
public class User extends AbstractStrOidAuditable {
	private static final long serialVersionUID = 6973853293711914826L;

	@Column(name = "NAME", length = 50)
	private String name;
	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER", length = 10)
	private Gender gender;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PROFILE_ID", nullable = true)
	private Profile profile;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "T_USER_ROLE_MAPPING", joinColumns = @JoinColumn(name = "USER_OID"), inverseJoinColumns = @JoinColumn(name = "ROLE_OID"))
	private List<Role> roles;

	@Column(name="BIRTHDAY")
	@Type(type="org.joda.time.contrib.hibernate.PersistentYearMonthDay")
	private DateTime birthday;
	
	public User() {
		super();
	}

	public User(String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public DateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(DateTime birthday) {
		this.birthday = birthday;
	}
	
}

