package nekrasova.svetlana.applicants.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String city;
	
	protected Applicant() {
		}

	public Applicant(String firstName, String lastName, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}
	
	public Applicant(long id, String firstName, String lastName, String city) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}
	

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + "]";
	}
	
	
	
	
}
