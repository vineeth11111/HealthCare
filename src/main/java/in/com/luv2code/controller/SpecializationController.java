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

import in.com.luv2code.entity.Specialization;
import in.com.luv2code.exception.SpecializationNotFoundException;
import in.com.luv2code.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private ISpecializationService specService;
	
	@RequestMapping("/register")
	public String SpecializationRegister(@RequestParam(name="message", required=false)String message,
			Model model)
	{
		model.addAttribute("message",message);
		return "SpecializationRegister";
	}
	
	@PostMapping("/save")
	public String saveOneSpecialization(@ModelAttribute Specialization spec,
			RedirectAttributes attributes)
	{
		specService.saveOneSpecialization(spec);
		
		attributes.addAttribute("message","Specialization created successfully");
		
		return "redirect:register";
	}
	
	@RequestMapping("/all")
	public String getAllSpecialization(Model model,
			@RequestParam(name="message", required=false)String message)
	{
		List<Specialization> list = specService.getAllSpecializations();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		
		return "SpecializationData";
	}
	
	@GetMapping("/delete")
	public String deleteSpecialization(@RequestParam Long id,
			RedirectAttributes attributes)
	{
		String message = null;
		
		try {
			specService.deleteOneSpecialization(id);
			message = "Specialization deleted successfully !!";
			
		}catch(SpecializationNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
		}
		
		attributes.addAttribute("message",message);
		return "redirect:all";
	}
	
	@GetMapping("/edit")
	public String EditSpecialization(@RequestParam Long id,
			RedirectAttributes attributes,
			Model model)
	{
		String message = null;
		
		try {
			Specialization spec = specService.getOneSpecializationUsingId(id);
			model.addAttribute("specialization",spec);
			return "SpecializationEdit";
			
			
		}catch(SpecializationNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
			attributes.addAttribute(message,"message");
			return "redirect:all";
		}
		
	}
	
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization spec,
			RedirectAttributes attributes)
	{
		System.out.println(spec.toString());
		specService.updateOneSpecialization(spec);
		
		attributes.addAttribute("message","Specialization updated successfully");
		
		return "redirect:all";
		
	}
	
	

}
