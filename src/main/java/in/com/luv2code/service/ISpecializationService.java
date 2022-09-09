package in.com.luv2code.service;

import java.util.List;
import java.util.Map;

import in.com.luv2code.entity.Specialization;

public interface ISpecializationService {
	
	Long saveOneSpecialization(Specialization specialization);
	List<Specialization> getAllSpecializations();
	void deleteOneSpecialization(Long id);
	Specialization getOneSpecializationUsingId(Long id);
	Long updateOneSpecialization(Specialization specialization);
	
	Map<Long,String> getSpecIdAndName();
}
