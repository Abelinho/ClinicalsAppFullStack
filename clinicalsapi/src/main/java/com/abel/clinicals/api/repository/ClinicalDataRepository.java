package com.abel.clinicals.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abel.clinicals.api.model.ClinicalData;

public interface ClinicalDataRepository extends JpaRepository<ClinicalData, Integer> {

	List<ClinicalData> findByPatientIdAndComponentNameOrderByMeasuredDateTime(int patientId, String componentName);

}
