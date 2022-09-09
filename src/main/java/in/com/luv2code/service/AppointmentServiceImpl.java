package in.com.luv2code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.com.luv2code.entity.Appointment;
import in.com.luv2code.exception.AppointmentNotFoundException;
import in.com.luv2code.repo.AppointmentRepository;
@Service
public class AppointmentServiceImpl implements IAppointmentService {

	@Autowired
	private AppointmentRepository appRepo;
	
	@Override
	public Long saveOneAppointment(Appointment appointment) {
		
		return appRepo.save(appointment).getId();
	}

	@Override
	public List<Appointment> getAllAppointments() {
		
		return appRepo.findAll();
	}

	@Override
	public void deleteOneAppointment(Long id) {
		
		appRepo.delete(getOneAppointmentUsingId(id));
	}

	@Override
	public Appointment getOneAppointmentUsingId(Long id) {
		
		return appRepo.findById(id).orElseThrow(
				
				()->new AppointmentNotFoundException("No appointments booked using given details")
				);
	}

	@Override
	public Long updateOneAppointment(Appointment appointment) {
		
		return appRepo.save(appointment).getId();
	}

}
