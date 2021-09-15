package nekrasova.svetlana.applicants.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import nekrasova.svetlana.applicants.model.Applicant;
import nekrasova.svetlana.applicants.model.ApplicantDto;

@Component
public class EntityConverter {

	public Applicant fromApplicantDtoToApplicant(ApplicantDto applDto) {
		return new Applicant(applDto.getId(), applDto.getFirstName(), applDto.getLastName(), applDto.getCity());
	}
	
	public ApplicantDto fromApplicantToApplicantDto(Applicant appl) {
		ApplicantDto applDto = new ApplicantDto();
		applDto.setId(appl.getId());
		applDto.setFirstName(appl.getFirstName());
		applDto.setLastName(appl.getLastName());
		applDto.setCity(appl.getCity());
		
		return applDto;
	}
	
	public Optional<ApplicantDto> fromApplicantToApplicantDto(Optional<Applicant> appl) {
		
		ApplicantDto applDto = new ApplicantDto();
		applDto.setId(appl.get().getId());
		applDto.setFirstName(appl.get().getFirstName());
		applDto.setLastName(appl.get().getLastName());
		applDto.setCity(appl.get().getCity());
		
		return Optional.of(applDto);
	}

}
