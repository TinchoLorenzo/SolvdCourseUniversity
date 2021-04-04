package com.solvd.UniversityMvn.members;

import java.util.Date;
import java.util.Objects;

public abstract class UniversityMember {

	private String name;
	private long id;
	private Date birthDate;
	private String email;

	public UniversityMember() {

	}

	public UniversityMember(String name, long id, Date birthDate, String email) {
		this.name = name;
		this.id = id;
		this.birthDate = birthDate;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UniversityMember))
			return false;
		if (obj.hashCode() != this.hashCode())
			return false;
		UniversityMember other = (UniversityMember) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && id == other.id;
	}

}
