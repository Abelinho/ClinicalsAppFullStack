package com.abel.clinicals.api.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.abel.clinicals.api.model.ClinicalData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientRequestDto {
	
	@NotEmpty(message = "lastName field is required")
	private String lastName;
	@NotEmpty(message = "lastName field is required")
	private String firstName;
	@NotNull(message = "age field is required")
	private int age;

	private List<ClinicalData> clinicalData;
	
}
