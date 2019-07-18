package com.bytecloud.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BasicController {
	 
	
	/**
	 *  Attach return value to web response, @ResponseBody is mandatory to get the direct response at browser
	 *  
	 * @return  
	 */ 
	@GetMapping(value = "/")
	public String index() {
		
		return "index";		
	}
	
	/**
	 * Must attach return value to external web response i.e through  JSP
	 * @return
	 */
	@GetMapping(value = "/hello")
	@ResponseBody
	public String sayHello() {
		return "<h2>Hello, World!!!</h2>";
	}  
	
  
}
