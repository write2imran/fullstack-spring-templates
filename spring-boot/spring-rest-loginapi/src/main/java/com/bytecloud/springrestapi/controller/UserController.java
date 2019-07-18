package com.bytecloud.springrestapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bytecloud.springrestapi.bean.User;
import com.bytecloud.springrestapi.bean.UserNotFoundException;
import com.bytecloud.springrestapi.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200") //In case if you call service from Angular's default port
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> all() {
		return userService.all();
	}

	@PostMapping(path = "/users/authenticate")
	public User authenticate(String username, String password) {

		User user = userService.authenticate(username, password);
		if (user == null) {
			throw new UserNotFoundException("User Not Found");
		}
		return user;
	}

	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {

		User user = userService.retrieveById(id);
		if (user == null) {
			throw new UserNotFoundException("User Not Found");
		}
		return user;
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {

		User user = userService.deleteById(id);

		System.out.println(user);

		if (user != null) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {

		System.out.println(user);
		userService.update(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/users/register")
	ResponseEntity<?> add(@Valid @RequestBody User user) {
		User createdUser = userService.update(user);
		if (createdUser == null) {
			return ResponseEntity.noContent().build();
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "/users/dummy-service")
	public User errorService() {
		throw new RuntimeException("Some Exception Occured");
	}
}