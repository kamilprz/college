//package Club;

import java.util.Calendar;
import java.util.Date;

public class ClubMember {

	private String name;
	private String address;
	private int yearOfRegistration;
	private String email;

	ClubMember(String name, String address, int yearOfRegistration, String email) {
		super();
		this.name = name;
		this.address = address;
		this.yearOfRegistration = yearOfRegistration;
		this.email = email;
	}

	public ClubMember(String name, String address, String email) {
		this(name, address, Calendar.getInstance().get(Calendar.YEAR), email);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(int yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return String.format("%s : from %s, joined in %d, email : %s", getName(), getAddress(), getYearOfRegistration(),
				getEmail());
	}

}
