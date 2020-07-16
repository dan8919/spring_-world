package kr.sc.controller;

import javax.inject.Inject;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.service.SchoolService;

@Controller
@RequestMapping("school")
public class SchoolController {
	
	@Inject
	private SchoolService sService;
	
	
	@RequestMapping
	public String insert() {
		return "school/insert";
		
	}
	

}
