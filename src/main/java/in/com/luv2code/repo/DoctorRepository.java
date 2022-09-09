package in.com.luv2code.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.com.luv2code.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	@Query("SELECT id,firstName,lastName FROM Doctor")
	public List<Object[]> getDoctorIdAndNames();
	
	@Query("SELECT doc  FROM Doctor doc INNER JOIN doc.specialization as spec WHERE spec.id =:specId")
	public List<Doctor> findDoctorBySpecName(Long specId);

}
