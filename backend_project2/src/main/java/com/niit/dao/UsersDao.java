package com.niit.dao;

import java.util.List;

import com.niit.model.Users;

public interface UsersDao {
 void registration (Users users);
 List<Users> getallUsers();
Users login(Users users);
Users updateUser(Users users);
}
