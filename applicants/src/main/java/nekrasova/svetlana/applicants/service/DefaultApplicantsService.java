package nekrasova.svetlana.applicants.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nekrasova.svetlana.applicants.exception.ApplValidationException;
import nekrasova.svetlana.applicants.model.Applicant;
import nekrasova.svetlana.applicants.model.ApplicantDto;
import nekrasova.svetlana.applicants.repository.ApplicantRepository;

@Service
public class DefaultApplicantsService implements ApplicantsService {
	@Autowired
	private ApplicantRepository applicantRepository;
	@Autowired
	private EntityConverter entityConverter;

	@Override
	public ApplicantDto saveApplicant(ApplicantDto applicantDto) throws ApplValidationException {
		validateDto(applicantDto);
		Applicant savedAppl = applicantRepository.save(entityConverter.fromApplicantDtoToApplicant(applicantDto));

		return entityConverter.fromApplicantToApplicantDto(savedAppl);
	}

	private void validateDto(ApplicantDto applDto) throws ApplValidationException {
		if (null == applDto) {
			throw new ApplValidationException("Object applicant does not exists");
		}
		if (null == applDto.getFirstName() || applDto.getFirstName().isEmpty()) {
			throw new ApplValidationException("Applicant name is empty");
		}
	}

	@Override
	public void removeApplicant(long id) {
		applicantRepository.deleteById(id);

	}

	@Override
	public List<ApplicantDto> findAll() {
		Iterable<Applicant> appls = applicantRepository.findAll();
		List<ApplicantDto> applDtos = new ArrayList<>();
		for (Applicant appl : appls) {
			applDtos.add(entityConverter.fromApplicantToApplicantDto(appl));
		}
		return applDtos;
	}

	@Override
	public Optional<ApplicantDto> findById(long id) {
		Optional<Applicant> applicant = applicantRepository.findById(id);
		return entityConverter.fromApplicantToApplicantDto(applicant);
	}

	
}
