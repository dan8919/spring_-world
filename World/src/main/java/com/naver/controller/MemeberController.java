package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")  
//member/insert ,member/select이렇게 나옴
public class MemeberController {

	@Autowired
	private MemberService memberService;
	
	
	/*
	 * @RequestMapping(value="/read",method=RequestMethod.GET) public void
	 * read(Model model,String id) {
	 * 
	 * MemberDTO read=memberService.read(id); 
	 * model.addAttribute("read", read); }
	 */
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public void list(Model model) {
		List<MemberDTO>list=memberService.list();
		
		model.addAttribute("list", list);
	}
	
	
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(MemberDTO dto) {
		memberService.insert(dto);
		
		return "redirect:/member/list";
		}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		return "member/insert";
	}
	
	
	
	
	
	
}
