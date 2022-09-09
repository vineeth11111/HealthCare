package in.com.luv2code.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.com.luv2code.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
