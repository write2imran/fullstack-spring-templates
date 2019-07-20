package com.bytecloud.springrestapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bytecloud.springrestapi.bean.User;

@Service
public class UserService {
	private static List<User> users = new ArrayList<User>();
	private static int userCount = 4;
	
	//Some users data
	static {
	
		users.add(new User(1, "carl", "carl","Carl", "Dismuk", "carl"));
		users.add(new User(2, "ron", "ron","Ron", "Monson", "ron"));
		users.add(new User(3, "steve", "ortiz","steve", "Ortiz", "ron"));
		users.add(new User(4, "imran", "imran","Imran", "A. Rauf", "imran"));
		
	}

	public List<User> all() {
		return users;
	}

	//authenticate
	public User authenticate(String username, String password) {

		User _user = null;
		for (User user : users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				_user = user;
				break;
			}
		}

		if (_user == null)
			throw new RuntimeException("Authentication:: User not found");
		return _user;
	}
	
	public User retrieveById(int id) {

		User _user = null;
		for (User user : users) {
			if(user.getId()==id) {
				_user = user;
				break;
			}
		}

		if (_user == null)
			throw new RuntimeException("Retrieving ID:: User not found");
		return _user;
		
	}
	
	public User createUser(User user) {

		users.add(user);
		return user;
	}

	public User update(User user) {
		User deletedUser = deleteById(user.getId());
		if (deletedUser == null) {
			throw new RuntimeException("Updating:: User not found");
		}
		users.add(user);
		return user;
	}

	public User deleteById(int id) {
		
		User _user = null;
		for (User user : users) {
			if(user.getId()==id) {
				_user = user;
				break;
			}
		}
		

		if (_user == null)
			throw new RuntimeException("Deleting:: User not found");

		if (users.remove(_user)) {
			return _user;
		}

		throw new RuntimeException("Delete Unsuccessful");
	}
}