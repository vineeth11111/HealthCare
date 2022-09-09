package in.com.luv2code.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.com.luv2code.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
