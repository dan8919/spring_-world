package kr.co.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyController {

	@RequestMapping(value="/show",method=RequestMethod.GET)
	public String show() {
		
		return "show";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list() {

	}
}
