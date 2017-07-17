package com.niit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.model.Users;
import com.niit.model.Error;
import com.niit.model.Friend;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
public class FriendController {
@Autowired
private FriendDao friendDao;
@RequestMapping(value="/suggesteduserslist",method=RequestMethod.GET)
public ResponseEntity<?> getSuggestedUsersList( HttpSession session)
{
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error=new Error(3,"Unauthorised user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
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
@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
public ResponseEntity<?> pendingRequests(HttpSession session){
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error=new Error(3,"Unauthorised user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	List<Friend> pendingRequests= friendDao.listOFPendingRequests(users.getUsername());
	return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
}
@RequestMapping(value="/updatependingrequest/{fromId}/{status}",method=RequestMethod.PUT)
public ResponseEntity<?> updatePendingRequests(@PathVariable String fromId,@PathVariable char status,HttpSession session){
	Users users=(Users)session.getAttribute("user");
	if(users==null){
		Error error=new Error(3,"Unauthorised user");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		
	}
	friendDao.updatePendingRequest(fromId, users.getUsername(), status);
	return new ResponseEntity<Void>(HttpStatus.OK);
	
}
}
