package in.com.luv2code.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.com.luv2code.entity.Specialization;
import in.com.luv2code.exception.SpecializationNotFoundException;
import in.com.luv2code.repo.SpecializationRepository;
import in.com.luv2code.util.MyCollectionsUtil;
@Service
public class SpecializationServiceImpl implements ISpecializationService {

	@Autowired
	private SpecializationRepository specRepo;
	
	@Override
	public Long saveOneSpecialization(Specialization specialization) {
		return specRepo.save(specialization).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		
		
		return specRepo.findAll();
		
	}

	@Override
	public void deleteOneSpecialization(Long id) {
		specRepo.delete(getOneSpecializationUsingId(id));

	}

	@Override
	public Specialization getOneSpecializationUsingId(Long id) {
		
		return specRepo.findById(id).orElseThrow(
				()->new SpecializationNotFoundException("Spec not found"));
	}

	@Override
	public Long updateOneSpecialization(Specialization specialization) {
		
		return specRepo.save(specialization).getId();
	}

	@Override
	public Map<Long, String> getSpecIdAndName() {
		List<Object[]> map = specRepo.getSpecIdAndName();
		
		return MyCollectionsUtil.convertToMap(map);
	}

}
