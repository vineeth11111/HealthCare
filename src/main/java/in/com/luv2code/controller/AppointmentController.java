package in.com.luv2code.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.com.luv2code.entity.Appointment;
import in.com.luv2code.entity.Doctor;
import in.com.luv2code.exception.AppointmentNotFoundException;
import in.com.luv2code.service.IAppointmentService;
import in.com.luv2code.service.IDoctorService;
import in.com.luv2code.service.ISpecializationService;

@Controller
@RequestMapping("/app")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService appService;
	
	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ISpecializationService specializationService;
	
	private void dynamicUI(Model model)
	{
		model.addAttribute("doctors",doctorService.getDoctorIdAndNames());
	}
	
	@RequestMapping("/register")
	public String AppointmentRegister(@RequestParam(name="message", required=false)String message,
			Model model)
	{
		dynamicUI(model);
		model.addAttribute("message",message);
		return "AppointmentRegister";
	}
	
	@PostMapping("/save")
	public String saveOneAppointment(@ModelAttribute Appointment app,
			RedirectAttributes attributes)
	{
		appService.saveOneAppointment(app);
		
		attributes.addAttribute("message","Appointment created successfully");
		
		return "redirect:register";
	}
	
	@RequestMapping("/all")
	public String getAllAppointments(Model model,
			@RequestParam(name="message", required=false)String message)
	{
		List<Appointment> list = appService.getAllAppointments();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		
		return "AppointmentData";
	}
	
	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam Long id,
			RedirectAttributes attributes)
	{
		String message = null;
		
		try {
			appService.deleteOneAppointment(id);
			message = "Appointment deleted successfully !!";
			
		}catch(AppointmentNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
		}
		
		attributes.addAttribute("message",message);
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String EditAppointment(@RequestParam Long id,
			RedirectAttributes attributes,
			Model model)
	{
		dynamicUI(model);
		String message = null;
		
		try {
			Appointment app = appService.getOneAppointmentUsingId(id);
			model.addAttribute("appointment",app);
			return "AppointmentEdit";
			
			
		}catch(AppointmentNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
			attributes.addAttribute(message,"message");
			return "redirect:all";
		}
		
	}
	
	//view appointment page
	@GetMapping("/view")
	public String viewSlots(@RequestParam(required=false,defaultValue = "0") Long specId,
			Model model)
	{
		//fetch data for Spec DropDown
		Map<Long,String> specMap = specializationService.getSpecIdAndName();
		model.addAttribute("specialization",specMap);
		List<Doctor> docList = null;
		String message = null;
		if(specId <= 0) {//if they did not select any spec
			
			 docList = doctorService.getAllDoctors();
			 message = "Result: All Doctors";
		}else {
			 docList = doctorService.findDoctorBySpecName(specId);
			 message = "Result:"+specializationService.getOneSpecializationUsingId(specId).getName()+"Doctor ";
		}
		model.addAttribute("docList",docList);
		
		model.addAttribute("message",message);
		return "AppointmentSearch";
	}
    //book appointment
	
	
	
	

}
