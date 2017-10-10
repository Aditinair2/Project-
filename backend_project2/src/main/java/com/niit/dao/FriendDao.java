package com.niit.dao;
import java.util.List;

import com.niit.model.Friend;
import com.niit.model.Users;

public interface FriendDao {
	List<Users> listOfSuggestedUsers(String username);
	void friendRequest(String fromUsername, String toUsername);
	List<Friend> listOFPendingRequests(String toUsername);
	void updatePendingRequest(String fromId ,String toId,char status);
	List<Friend> listOfFriends(String username);

}
