package in.com.luv2code.service;

import java.util.List;

import in.com.luv2code.entity.Appointment;

public interface IAppointmentService {

	Long saveOneAppointment(Appointment appointment);
	List<Appointment> getAllAppointments();
	void deleteOneAppointment(Long id);
	Appointment getOneAppointmentUsingId(Long id);
	Long updateOneAppointment(Appointment appointment);
}
