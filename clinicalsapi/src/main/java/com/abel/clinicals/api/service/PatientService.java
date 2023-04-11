package com.abel.clinicals.api.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.abel.clinicals.api.dto.PatientRequestDto;
import com.abel.clinicals.api.model.Patient;
import com.abel.clinicals.api.repository.PatientRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PatientService {

	private final PatientRepository patientRepository;
	
	@Transactional
	public Patient savePatient(PatientRequestDto patientReq) {
		
	Patient patient = Patient.builder()
		.age(patientReq.getAge())
		.clinicalData(patientReq.getClinicalData())
		.firstName(patientReq.getFirstName())
		.lastName(patientReq.getLastName())
		.build();
		
		return patientRepository.save(patient);
	}
	
}
