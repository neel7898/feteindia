package com.neel.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.neel.dao.LoginDao;

@Controller
public class LoginController {
	
	Gson gson = new Gson();
	
	@Autowired
	LoginDao dao;
	
	@RequestMapping(value="/loginUser", method = RequestMethod.POST)
	@ResponseBody
	public String loginUser(
				@RequestParam String id,
				@RequestParam String password,
				HttpServletRequest request
			){
		String message = "";
		System.out.println("ID: "+id+", Password: "+password);
		HttpSession session = request.getSession();
		if(id.contains("@")){
			message = this.loginWithEmail(id,password,session);
		}else if(id.startsWith("FP")){
			message = this.restaurentLogin(id,password,session);
		}else if(id.startsWith("FC")){
			message = this.customerLogin(id,password,session);
		}
		return gson.toJson(message);
	}

	private String customerLogin(String id, String password, HttpSession session) {
		String message = "";
		String credentials = dao.getPasswordForCustID(id);
		if(password.equals(credentials)){
			session.setMaxInactiveInterval(20*60);
			session.setAttribute("sessionUser", id);
			message = "success";
		}else{
			message = "Invalid credentials";
		}
		return message+"~"+id;
	}

	private String restaurentLogin(String id, String password, HttpSession session) {
		// TODO Auto-generated method stub - check credential, set session attributes.
		String message = "";
		String credentials = dao.getPasswordForRestID(id);
		if(password.equals(credentials)){
			session.setMaxInactiveInterval(20*60);
			session.setAttribute("sessionUser", id);
			message = "success";
		}else{
			message = "Invalid credentials";
		}
		return message+"~"+id;
	}

	private String loginWithEmail(String id, String password, HttpSession session) {
		// TODO Auto-generated method stub
		String user = "";
		String message = "";
		user = dao.checkLoginType(id);
		if(user.equalsIgnoreCase("NA")){
			message = gson.toJson("NA");
		}else if(user.startsWith("FP")){
			message = restaurentLogin(user, password, session);
		}else if(user.startsWith("FC")){
			message = customerLogin(user, password, session);
		}
		return message;
	}
	
}
