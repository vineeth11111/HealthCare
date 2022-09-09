package in.com.luv2code.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.com.luv2code.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	
//	@Query("SELECT COUNT(code) FROM Specialization WHERE code=:code")
//	Integer getSpecCodeCount(String specCode);
	
	@Query("SELECT id,name FROM Specialization")
	List<Object[]> getSpecIdAndName();

}
