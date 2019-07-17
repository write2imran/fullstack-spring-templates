package com.bytecloud.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/greeting")
public class GreetingController {
	
	
	/**
	 *  Attach return value to web response, @ResponseBody is mandatory to get the direct response at browser
	 *  
	 * @return  
	 */
	@GetMapping(value = "/hi")
	@ResponseBody
	public String sayHi() { 		
		return "Hi";
	}
	
	/**
	 * Must attach return value to external web response i.e through  JSP
	 * @return
	 */
	@GetMapping(value = "/hello")
	public String sayHello() { 		
		return "h";
	}
	
  
}
