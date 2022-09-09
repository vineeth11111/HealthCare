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
import in.com.luv2code.entity.Doctor;
import in.com.luv2code.entity.User;
import in.com.luv2code.exception.DoctorNotFoundException;
import in.com.luv2code.service.IDoctorService;
import in.com.luv2code.service.ISpecializationService;
import in.com.luv2code.service.IUserService;
import in.com.luv2code.util.UserUtil;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	ISpecializationService specService;
	
	
	@Autowired
	private IDoctorService doctorService;
	

	@Autowired 
	private IUserService userService;
	
	//get dynamic specs
	private void dynamicUI(Model model)
	{
		model.addAttribute("specializations",specService.getSpecIdAndName());
	}
	
	@RequestMapping("/register")
	public String DoctorRegister(@RequestParam(name="message", required=false)String message,
			Model model)
	{
		dynamicUI(model);
		model.addAttribute("message",message);
		return "DoctorRegister";
	}
	
	@PostMapping("/save")
	public String saveOneDoctor(@ModelAttribute Doctor doctor,
			RedirectAttributes attributes)
	{
		Long id = doctorService.saveOneDoctor(doctor);
		
		if(id!=null)
		{
			User user = new User();
			user.setDisplayName(doctor.getFirstName()+" "+doctor.getLastName());
			user.setPassword(UserUtil.genPwd());
			user.setRole(UserRoles.DOCTOR.name());
			userService.saveUser(user);
			//TODO: Email part is pending
		}
		
		attributes.addAttribute("message","Doctor created successfully");
		
		return "redirect:register";
	}
	
	@RequestMapping("/all")
	public String getAllDoctors(Model model,
			@RequestParam(name="message", required=false)String message)
	{
		List<Doctor> list = doctorService.getAllDoctors();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		
		return "DoctorData";
	}
	
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam Long id,
			RedirectAttributes attributes)
	{
		String message = null;
		
		try {
			doctorService.deleteOneDoctor(id);
			message = "Doctor deleted successfully !!";
			
		}catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
		}
		
		attributes.addAttribute("message",message);
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String EditDoctor(@RequestParam Long id,
			RedirectAttributes attributes,
			Model model)
	{
		String message = null;
		dynamicUI(model);
		
		try {
			Doctor doctor = doctorService.getOneDoctorUsingId(id);
			model.addAttribute("doctor",doctor);
			return "DoctorEdit";
			
			
		}catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
			attributes.addAttribute(message,"message");
			return "redirect:all";
		}
		
	}
	
	@PostMapping("/update")
	public String updateDoctor(@ModelAttribute Doctor doctor,
			RedirectAttributes attributes)
	{
		doctorService.updateOneDoctor(doctor);
		attributes.addAttribute("message","Doctor updated successfully");
		return "redirect:all";
	}

	
	

}
