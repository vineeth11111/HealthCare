package in.com.luv2code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.com.luv2code.constants.UserRoles;
import in.com.luv2code.entity.Patient;
import in.com.luv2code.entity.User;
import in.com.luv2code.exception.PatientNotFoundException;
import in.com.luv2code.repo.PatientRepository;
import in.com.luv2code.util.UserUtil;
@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired 
	private IUserService userService;

	@Override
	public Long saveOnePatient(Patient patient) {
		Long id = patientRepo.save(patient).getId();
		if(id!=null)
		{
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			User user2 = new User();
			user2.setPassword(UserUtil.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			userService.saveUser(user);
			//TODO: Email part is pending
			}
		
		System.out.println("you mail id is"+patient.getEmail()+" and password is "+UserUtil.genPwd());
		
		return id;
	}

	@Override
	public List<Patient> getAllPatients() {
		
		return patientRepo.findAll();
	}

	@Override
	public void deleteOnePatient(Long id) {
		patientRepo.delete(getOnePatientUsingId(id));

	}

	@Override
	public Patient getOnePatientUsingId(Long id) {
		
		return patientRepo.findById(id).orElseThrow(
				()->new PatientNotFoundException("Patient with given details not exists"));
	}

	@Override
	public Long updateOnePatient(Patient patient) {
		
		return patientRepo.save(patient).getId();
	}

}
