package in.com.luv2code.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.com.luv2code.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
	@Query("SELECT id,firstName,lastName FROM Doctor")
	List<Object[]> getDoctorIdAndNames();

}
