package com.niit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.model.Users;
import com.niit.model.Error;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class FriendController {
@Autowired
private FriendDao friendDao;
@RequestMapping(value="/suggestedUsersList",method=RequestMethod.POST)
public ResponseEntity<?> getSuggestedUsersList(@RequestBody Users users)
{
	List<Users> suggestedUsers=friendDao.listOfSuggestedUsers(users.getUsername());
	return new ResponseEntity<List<Users>>(suggestedUsers,HttpStatus.OK);
	
}
@RequestMapping(value="/friendrequest/{toUsername}",method=RequestMethod.GET)
public ResponseEntity<?> friendRequest(@PathVariable String toUsername,HttpSession session){
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error=new Error(3,"Unauthorised user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	String fromUsername=users.getUsername();
	friendDao.friendRequest(fromUsername, toUsername);
	return new ResponseEntity<Void>(HttpStatus.OK);
}
}
