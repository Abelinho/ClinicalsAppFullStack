package com.abel.clinicals.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.abel.clinicals.api.model.Patient;
import com.abel.clinicals.api.repository.PatientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientServiceTest {

	@Autowired
	@MockBean
    PatientRepository patientRepository;
	
	//@Autowired
	//private TestEntityManager entityManager;


    @Test
    @Transactional
    public void testSavePatient() {
        // create a new patient
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setAge(30);
        patient.setClinicalData(null);

        // save the patient
        Patient savedPatient = patientRepository.save(patient);

        // assert that the saved patient has the correct information
        assertThat(savedPatient).isNotNull();
        assertThat(savedPatient.getFirstName()).isEqualTo(patient.getFirstName());
        assertThat(savedPatient.getLastName()).isEqualTo(patient.getLastName());
        assertThat(savedPatient.getAge()).isEqualTo(patient.getAge());
        assertThat(savedPatient.getClinicalData()).isEqualTo(patient.getClinicalData());
    }
	
}
