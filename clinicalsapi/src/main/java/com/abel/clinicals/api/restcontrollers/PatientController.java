package com.abel.clinicals.api.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abel.clinicals.api.dto.PatientRequestDto;
import com.abel.clinicals.api.model.ClinicalData;
import com.abel.clinicals.api.model.Patient;
import com.abel.clinicals.api.repository.PatientRepository;
import com.abel.clinicals.api.service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class PatientController {

	private PatientRepository patientRepository;
	
	private PatientService patientService;

	@Autowired
	PatientController(PatientService patientService,PatientRepository patientRepository) {
		this.patientService = patientService;
		this.patientRepository=patientRepository;
	}

	@RequestMapping(value = "/patients", method = RequestMethod.POST)
	public Patient savePatient(@RequestBody @Valid PatientRequestDto patientRequest) {
		log.info("Patient to be saved: "+patientRequest.getFirstName());
		
		return patientService.savePatient(patientRequest);
	}

	@RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
	public Patient getPatient(@PathVariable("id") int id) {
		
		return patientRepository.findById(id).get();
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public List<Patient> getPatients() {
		
		return patientRepository.findAll();
	}

	@RequestMapping(value = "/patients/analyze/{id}", method = RequestMethod.GET)
	public Patient analyse(@PathVariable("id") int id) {
		
		Patient patient = patientRepository.findById(id).get();
		List<ClinicalData> clinicalData = new ArrayList<>(patient.getClinicalData());
		for (ClinicalData eachEntry : clinicalData) {
			if (eachEntry.getComponentName().equals("hw")) {
				String[] heightAndWeight = eachEntry.getComponentValue().split("/");
				if (heightAndWeight != null && heightAndWeight.length > 1) {
					float feetToMetres = Float.parseFloat(heightAndWeight[0]) * 0.4536F;
					float BMI = Float.parseFloat(heightAndWeight[1]) / (feetToMetres * feetToMetres);
					ClinicalData bmiEntry = new ClinicalData();
					bmiEntry.setComponentName("BMI");
					bmiEntry.setComponentValue(Float.toString(BMI));
					patient.getClinicalData().add(bmiEntry);
				}
			}
		}
		return patient;
	}

}
