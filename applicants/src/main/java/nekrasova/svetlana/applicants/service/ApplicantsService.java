package nekrasova.svetlana.applicants.service;

import nekrasova.svetlana.applicants.exception.ApplValidationException;
import nekrasova.svetlana.applicants.model.ApplicantDto;

import java.util.List;
import java.util.Optional;

public interface ApplicantsService {
	ApplicantDto saveApplicant(ApplicantDto applicantDto) throws ApplValidationException;
	
	void removeApplicant(long id);
	
	Optional<ApplicantDto> findById(long id);
	
	List<ApplicantDto> findAll();	

}
