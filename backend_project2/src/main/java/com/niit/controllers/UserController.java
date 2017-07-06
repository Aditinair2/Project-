package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
			List<Users> users = usersDao.getallUsers();

			for (Users u : users)
				if (u.getUsername().equals(user.getUsername())) {
					Error error = new Error(2, "Username already exists");
					return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);

				}
		System.out.println(user.getUsername() +" "+ user.getFirstname() +" "+ user.getEmail());
		

			// user.setOnline(false);
			usersDao.registration(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} catch (Exception e) {
		    Error error = new Error(1, "Cannot register user details");
			return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping (value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Users users, HttpSession session){
		Users validUser=usersDao.login(users);
		if(validUser==null){
			Error error=new Error(3,"Invalid username and password...please enter valid details");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
		else{
			validUser.setOnline(true);
			validUser=usersDao.updateUser(validUser);
			session.setAttribute("user",validUser);
			return new ResponseEntity<Users>(validUser,HttpStatus.OK);
		}
		
	}
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session)
	{
		Users users=(Users)session.getAttribute("user");
		if(users==null){
			Error error=new Error(3,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
			
			
		}
		System.out.println("is session new for" +users.getUsername() + session.isNew());
		users.setOnline(false);
		usersDao.updateUser(users);
		session.removeAttribute("user");
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}

