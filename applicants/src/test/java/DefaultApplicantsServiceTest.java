

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import nekrasova.svetlana.applicants.exception.ApplValidationException;
import nekrasova.svetlana.applicants.model.Applicant;
import nekrasova.svetlana.applicants.model.ApplicantDto;
import nekrasova.svetlana.applicants.repository.ApplicantRepository;
import nekrasova.svetlana.applicants.service.DefaultApplicantsService;
import nekrasova.svetlana.applicants.service.EntityConverter;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicantsServiceTest {
	@InjectMocks
	DefaultApplicantsService applService;

	@Mock
	ApplicantRepository applRepository;

	@Mock
	EntityConverter applConverter;
	
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
		
	}
	

	@Test
	public void findAllTest() {
		List<Applicant> applList = new ArrayList<Applicant>();
		Applicant appl1 = new Applicant(1, "Aleksei", "Novikov", "Saratov");
		Applicant appl2 = new Applicant(2, "Oleg", "Samarin", "Kazan");

		applList.add(appl1);
		applList.add(appl2);

		when(applRepository.findAll()).thenReturn(applList);

		List<ApplicantDto> applDtoList = applService.findAll();

		assertEquals(2, applDtoList.size());
		verify(applRepository, times(1)).findAll();
	}

	@Test
	public void saveApplicantTest() throws ApplValidationException {
		ApplicantDto applDto = new ApplicantDto(1, "Vasya", "Mishin", "Penza");

		applService.saveApplicant(applDto);

		verify(applRepository, times(1)).save(applConverter.fromApplicantDtoToApplicant(applDto));
	}
}