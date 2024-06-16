package com.stubizsolutions.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stubizsolutions.bindings.LoginForm;
import com.stubizsolutions.bindings.RegisterForm;
import com.stubizsolutions.bindings.ResetPwdForm;
import com.stubizsolutions.constants.AppConstants;
import com.stubizsolutions.entities.User;
import com.stubizsolutions.props.AppProps;
import com.stubizsolutions.services.UserService;

//Change One


@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private AppProps props;
	
	@GetMapping("/")
	public String index(Model model) 
	{
	   model.addAttribute("login", new LoginForm());
		
	   return "index";
	}
	
	@PostMapping("/login")
	public String logInCheck(@ModelAttribute("login") LoginForm login,Model model) 
	{
		User user = userService.login(login);
		
		if (user==null) 
		{
			
			
			model.addAttribute(AppConstants.ERR_MSG,props.getMessages().get("invalidLogin"));
			
			return "index";
		}
		
		if (user.getPwdUpdated().equals("NO")) 
		{
			ResetPwdForm formObj = new ResetPwdForm();
			formObj.setUserId(user.getUserId());
			
			model.addAttribute(AppConstants.RESET_PWD,formObj);
			
			return AppConstants.RESET_PWD;
		}
		
		
		return "redirect:dashboard";
		
	}
	
	@PostMapping("/updatePwd")
	public String updatePwd(ResetPwdForm resetPwd,Model model) 
	{
		if (!resetPwd.getNewPwd().equals(resetPwd.getConfirmPwd()))
		{
			
		   model.addAttribute(AppConstants.ERR_MSG, props.getMessages().get("invalidPwds"));
		   
		   return AppConstants.RESET_PWD;
		}
		
		boolean status = userService.resetPwd(resetPwd);
		
		if (status)
		{
			return "redirect:dashboard";
		}
		
		 model.addAttribute(AppConstants.ERR_MSG, props.getMessages().get("pwdUpdateFail"));
		 
		 return AppConstants.RESET_PWD;
	}
	
	@GetMapping("/register")
	public String loadRegisterPage(Model model)
	{
		model.addAttribute("registerForm", new RegisterForm());
		
		Map<Integer,String> countries = userService.getCountries();
		model.addAttribute("countries", countries);
		
		return "register";
	}
	
	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer, String> getStates(@RequestParam("countryId") Integer countryId)
	{
		return userService.getStates(countryId);
	}
	
	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer, String> getCities(@RequestParam("stateId") Integer stateId)
	{
		return userService.getCities(stateId);
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegisterForm registerForm,Model model) 
	{
		boolean saveUser = userService.saveUser(registerForm);
		
		if(saveUser) 
		{
			model.addAttribute("succMsg", props.getMessages().get("regSuccess"));
			
		}
		else 
		{
		model.addAttribute(AppConstants.ERR_MSG, props.getMessages().get("regFail"));
		}
		
		Map<Integer,String> countries = userService.getCountries();
		model.addAttribute("countries", countries);
		
		return "register";
	}

}
