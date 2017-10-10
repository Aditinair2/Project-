package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UsersDao;
import com.niit.model.Error;
import com.niit.model.Users;

@RestController
public class UserController {
	@Autowired

	private UsersDao usersDao;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody Users user) {
		try {

			user.setEnabled(true);
			List<Users> userList = usersDao.getallUsers();

			for (Users u : userList)
				if (u.getUsername().equals(user.getUsername())) {
					Error error = new Error(2, "Username already exists");
					return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);

				}
		
		

		    user.setOnline(false);
			usersDao.registration(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} 
		catch (Exception e) 
		{
		    Error error = new Error(1, "Cannot register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping (value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Users user, HttpSession session){
		Users validUser=usersDao.login(user);
		if(validUser==null){
			Error error=new Error(3,"Invalid username and password...please enter valid details");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			validUser.setOnline(true);
			usersDao.updateUser(validUser);
			session.setAttribute("user",validUser);
			return new ResponseEntity<Users>(validUser,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session)
	{
		Users user=(Users)session.getAttribute("user");
		if(user==null){
			Error error=new Error(3,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
			
		}
		user.setOnline(false);
		usersDao.updateUser(user);
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/getuserdetails",method=RequestMethod.GET)
	public ResponseEntity<?> getUserByUsername(HttpSession session){
		Users user=(Users)session.getAttribute("user");
		if(user==null){
			Error error=new Error(3,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		user=usersDao.getUserByUsername(user.getUsername());
		return new ResponseEntity<Users>(user,HttpStatus.OK);
			
		}
	@RequestMapping(value="/updateprofile",method=RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody Users users,HttpSession session){
		Users user=(Users)session.getAttribute("user");
		if(user==null){
			Error error=new Error(3,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		usersDao.updateUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}
	
@RequestMapping(value="/viewFriend/{username}")
public ResponseEntity<?> viewUserDetails(@PathVariable String username,HttpSession session)
{
	Users user=(Users) session.getAttribute("user");
	if(user==null)
	{
		Error error=new Error(6,"Unauthorized user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	Users requiredUser=usersDao.getUserByUsername(username);
	return new ResponseEntity<Users>(requiredUser,HttpStatus.OK);
}
}


	
	


