package com.abel.clinicals.api.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.abel.clinicals.api.restcontrollers.dto.ClinicalDataRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clinicaldata")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicalData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//this ensures that Hibernate picks up the id from the db when returning this entity to the client e.g during a save operation
	private int id;//..not using @GeneratedValue above makes Hibernate return 0 to the client(Post man or UI) as the id
	private String componentName;
	private String componentValue;
	private Timestamp measuredDateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false)
	@JsonIgnore//this filters out the Json output of this field/object
	private Patient patient;

}
