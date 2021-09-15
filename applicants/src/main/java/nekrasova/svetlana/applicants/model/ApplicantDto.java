package nekrasova.svetlana.applicants.model;

import com.opencsv.bean.CsvBindByName;

public class ApplicantDto {
	@CsvBindByName
	private long id;
	
	@CsvBindByName
	private String firstName;
	
	@CsvBindByName
	private String lastName;
	
	@CsvBindByName
	private String city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public ApplicantDto() {
	}

	public ApplicantDto(long id, String firstName, String lastName, String city) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}

}
