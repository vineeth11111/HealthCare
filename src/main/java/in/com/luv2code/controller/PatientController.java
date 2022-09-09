package in.com.luv2code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.com.luv2code.constants.UserRoles;
import in.com.luv2code.entity.Patient;
import in.com.luv2code.entity.User;
import in.com.luv2code.exception.PatientNotFoundException;
import in.com.luv2code.service.IPatientService;
import in.com.luv2code.service.IUserService;
import in.com.luv2code.util.UserUtil;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired 
	private IUserService userService;
	
	@Autowired
	private UserUtil util;
	
	@RequestMapping("/register")
	public String PatientRegister(@RequestParam(name="message", required=false)String message,
			Model model)
	{
		model.addAttribute("message",message);
		return "PatientRegister";
	}
	
	@PostMapping("/save")
	public String saveOnePatient(@ModelAttribute Patient patient,
			RedirectAttributes attributes)
	{
		Long id = patientService.saveOnePatient(patient);
		if(id!=null)
		{
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			user.setPassword(UserUtil.genPwd());
			user.setRole(UserRoles.PATIENT.name());
			userService.saveUser(user);
			//TODO: Email part is pending
			}
		
		attributes.addAttribute("message","Patient created successfully");
		
		return "redirect:register";
	}
	
	@RequestMapping("/all")
	public String getAllPatients(Model model,
			@RequestParam(name="message", required=false)String message)
	{
		List<Patient> list = patientService.getAllPatients();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		
		return "PatientData";
	}
	
	@GetMapping("/delete")
	public String deletePatient(@RequestParam Long id,
			RedirectAttributes attributes)
	{
		String message = null;
		
		try {
			patientService.deleteOnePatient(id);
			message = "Patient deleted successfully !!";
			
		}catch(PatientNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
		}
		
		attributes.addAttribute("message",message);
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String EditPatient(@RequestParam Long id,
			RedirectAttributes attributes,
			Model model)
	{
		String message = null;
		
		try {
			Patient patient = patientService.getOnePatientUsingId(id);
			model.addAttribute("patient",patient);
			return "PatientEdit";
			
			
		}catch(PatientNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
			attributes.addAttribute(message,"message");
			return "redirect:all";
		}
		
	}
	
	@PostMapping("/update")
	public String updatePatient(@ModelAttribute Patient patient,RedirectAttributes attributes ) {
		
		patientService.updateOnePatient(patient);
		
		attributes.addAttribute("message", "Patient updated successfully");
		
		return "redirect:all";
		
	}

	
	

}
