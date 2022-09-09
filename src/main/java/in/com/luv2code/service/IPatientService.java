package in.com.luv2code.service;

import java.util.List;

import in.com.luv2code.entity.Patient;

public interface IPatientService {
	
	Long saveOnePatient(Patient patient);
	List<Patient> getAllPatients();
	void deleteOnePatient(Long id);
	Patient getOnePatientUsingId(Long id);
	Long updateOnePatient(Patient patient);
	
}
