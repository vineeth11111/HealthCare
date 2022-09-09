package in.com.luv2code.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.com.luv2code.constants.UserRoles;
import in.com.luv2code.entity.Doctor;
import in.com.luv2code.entity.User;
import in.com.luv2code.exception.DoctorNotFoundException;
import in.com.luv2code.repo.DoctorRepository;
import in.com.luv2code.util.MyCollectionsUtil;
import in.com.luv2code.util.UserUtil;
@Service
public class DoctorServiceImpl implements IDoctorService {
	
	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired 
	private IUserService userService;

	@Override
	public Long saveOneDoctor(Doctor doctor) {
	
		Long id =  doctorRepo.save(doctor).getId();
		if(id!=null)
		{
			User user = new User();
			user.setDisplayName(doctor.getFirstName()+" "+doctor.getLastName());
			user.setUsername(doctor.getEmail());
			user.setPassword(UserUtil.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			userService.saveUser(user);
			//TODO: Email part is pending
			}
		
		System.out.println("you mail id is"+doctor.getEmail()+" and password is "+UserUtil.genPwd());
		
		return id;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		
		return doctorRepo.findAll();
	}

	@Override
	public void deleteOneDoctor(Long id) {
		
		doctorRepo.delete(getOneDoctorUsingId(id));
		
	}

	@Override
	public Doctor getOneDoctorUsingId(Long id) {
		
		
		return doctorRepo.findById(id).orElseThrow(
				()->new DoctorNotFoundException("doctor not found"));
	}

	@Override
	public Long updateOneDoctor(Doctor doctor) {
		
		return doctorRepo.save(doctor).getId();
	}

	@Override
	public Map<Long, String> getDoctorIdAndNames() {
		List<Object[]> list = doctorRepo.getDoctorIdAndNames();
		
		return MyCollectionsUtil.convertToMap3(list);
	}

	

}
