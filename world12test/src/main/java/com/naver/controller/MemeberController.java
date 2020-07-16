package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") String id) {
		memberService.delete(id);
		return "redirect:/member/list";
	}
	
	
	//왜 post 방식으로?
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(MemberDTO dto) {
		memberService.update(dto);
		return "redirect:/member/list";
	
		
	}
	
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String updateui(@PathVariable("id") String id,Model model) {
		
		MemberDTO dto=memberService.updateui(id);
		model.addAttribute("dto",dto);
		return "member/update";
		
	}
	
	
	  @RequestMapping(value="/read",method=RequestMethod.GET)
	  public void read(String id,Model model) {
	  
	  MemberDTO dto=memberService.read(id); 
	  model.addAttribute("dto", dto);
	  }
	 
	
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
