package in.com.luv2code.service;

import java.util.List;
import java.util.Map;

import in.com.luv2code.entity.Doctor;

public interface IDoctorService {
	
	Long saveOneDoctor(Doctor doctor);
	List<Doctor> getAllDoctors();
	void deleteOneDoctor(Long id);
	Doctor getOneDoctorUsingId(Long id);
	Long updateOneDoctor(Doctor doctor);
	
	Map<Long,String> getDoctorIdAndNames();
}
