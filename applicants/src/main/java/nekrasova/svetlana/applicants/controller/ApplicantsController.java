package nekrasova.svetlana.applicants.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import nekrasova.svetlana.applicants.exception.ApplValidationException;
import nekrasova.svetlana.applicants.model.ApplicantDto;
import nekrasova.svetlana.applicants.service.ApplicantsService;

@Controller
public class ApplicantsController {

	@Autowired
	private ApplicantsService applicantsService;

	@GetMapping("/index")
	public String showAllApplicants(Model model) {
		model.addAttribute("applicants", applicantsService.findAll());
		return "index";
	}

	@GetMapping("/addnew")
	public String showAddNewApplicantForm(Map<String, Object> model) {
		ApplicantDto appl = new ApplicantDto();
		model.put("appl", appl);
		return "add-applicant";
	}

	@PostMapping("/addapplicant")
	public String saveApplicant(@ModelAttribute("appl") ApplicantDto appl) {

		try {
			applicantsService.saveApplicant(appl);
			return "redirect:/index";
		} catch (ApplValidationException e) {
			return "add-applicant";
		}
	}

	@GetMapping("/edit/{id}")
	public String showUpdateApplicantForm(@PathVariable("id") long id, Model model) {
		ApplicantDto appl = applicantsService.findById(id).get();

		model.addAttribute("appl", appl);
		return "update-applicant";
	}

	@PostMapping("/update/{id}")
	public String updateApplicant(@ModelAttribute("appl") ApplicantDto appl) {

		try {
			applicantsService.saveApplicant(appl);
			return "redirect:/index";
		} catch (ApplValidationException e) {
			appl.setId(appl.getId());

			return "update-applicant";
		}

	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		applicantsService.removeApplicant(id);
		return "redirect:/index";
	}

	@PostMapping("/upload-csv-file")
	public String uploadCSVFile(@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {

			try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

				CsvToBean<ApplicantDto> csvToBean = new CsvToBeanBuilder<ApplicantDto>(reader)
						.withType(ApplicantDto.class).withIgnoreLeadingWhiteSpace(true).build();

				List<ApplicantDto> appls = csvToBean.parse();

				for (ApplicantDto appl : appls) {
					applicantsService.saveApplicant(appl);
				}

			} catch (Exception ex) {
			}
		}

		return "redirect:/index";
	}

}
