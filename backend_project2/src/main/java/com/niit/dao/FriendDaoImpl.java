package com.niit.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.Users;
@Repository

public class FriendDaoImpl implements FriendDao{
	@Autowired
	private SessionFactory sessionFactory;

	public List<Users> listOfSuggestedUsers(String username) {
		Session session=sessionFactory.openSession();
		SQLQuery query=session.createSQLQuery("select * from Users_info where username in"+"(select username from Users_info where username!=? minus (select fromid from Friend where toid=? union select toid from Friend where fromid=? ))");
		query.setString(0, username);
		query.setString(1, username);
		query.setString(2, username);
		query.addEntity(Users.class);
		List<Users> suggestedUsersList=query.list();
		session.close();
		return suggestedUsersList;
	}
	public void friendRequest(String fromUsername,String toUsername){
		Session session=sessionFactory.openSession();
	    Friend friend=new Friend();
	    friend.setFromId(fromUsername);
	    friend.setToId(toUsername);
	    friend.setStatus('P');
	    session.save(friend);
	    session.flush();
	    session.close();
	}
	public List<Friend> listOFPendingRequests(String toUsername) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where toId=? AND status=?");
		query.setString(0, toUsername);
		query.setCharacter(1, 'P');
		@SuppressWarnings("unchecked")
		List<Friend> pendingRequests=query.list();
		session.close();
		
		return pendingRequests;
	}
	public void updatePendingRequest(String fromId, String toId, char status) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where fromId=? AND toId=?");
		query.setString(0, fromId);
		query.setString(1, toId);
		Friend friend=(Friend)query.uniqueResult();
		friend.setStatus(status);//status can be either A or D
		session.update(friend);
		session.flush();
		session.close();
	}
	public List<Friend> listOfFriends(String username){
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Friend where(fromId=? or toId=?) and status=?");
		query.setString(0, username);
		query.setString(1, username);
		query.setCharacter(2, 'A');
		@SuppressWarnings("unchecked")
		List<Friend> friends=query.list();
		session.close();
		return friends;
		
	}
}
