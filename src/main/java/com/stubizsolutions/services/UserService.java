package com.stubizsolutions.services;

import java.util.Map;

import com.stubizsolutions.bindings.LoginForm;
import com.stubizsolutions.bindings.RegisterForm;
import com.stubizsolutions.bindings.ResetPwdForm;
import com.stubizsolutions.entities.User;

public interface UserService 
{
	public Map<Integer,String> getCountries();
	
	public Map<Integer,String> getStates(Integer countryId);
	
	public Map<Integer, String> getCities(Integer stateId);
	
	public User getUser(String email);
	
	public boolean saveUser(RegisterForm formObj);
	
	public User login(LoginForm formObj);
	
	public boolean resetPwd(ResetPwdForm formObj);
	

}
