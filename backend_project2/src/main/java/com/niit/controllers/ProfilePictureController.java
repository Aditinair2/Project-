package com.niit.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.model.Users;
import com.niit.model.Error;
import com.niit.model.ProfilePicture;

@Controller
public class ProfilePictureController {
	@Autowired
	private ProfilePictureDao profilePictureDao;
	
	@RequestMapping(value="/uploadprofilepic",method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image,HttpSession session){
		Users user=(Users)session.getAttribute("user");
		if(user==null)   {
			Error error=new Error(3,"Unauthorized user");
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
		}
	ProfilePicture profilePicture=new ProfilePicture();
	profilePicture.setUsername(user.getUsername());
	profilePicture.setImage(image.getBytes());
	profilePictureDao.saveProfilePicture(profilePicture);
	return new ResponseEntity<Void>(HttpStatus.OK);
}
	@RequestMapping(value="getimage/{username}",method=RequestMethod.GET)
    public @ResponseBody byte[] getProfilePic(@PathVariable String username,HttpSession session)
{
	Users user=(Users) session.getAttribute("user");
	if(user==null)
	{
		return null;
	}
	else
	{
		ProfilePicture profilePic=profilePictureDao.getProfilePicture(username);
		if(profilePic==null)
		{
			return null;
		}
		else
		{
			return profilePic.getImage();
		}
	}
}
}