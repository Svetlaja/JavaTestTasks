package nekrasova.svetlana.applicants.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import nekrasova.svetlana.applicants.model.Applicant;

public interface ApplicantRepository extends CrudRepository<Applicant, Long> {
	
	List<Applicant> findByFirstName(String firstName);
	

}
