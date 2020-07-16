package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.domain.LoginDTO;
import kr.co.domain.MemberDTO;
import kr.co.service.MemberService;

@Controller
@RequestMapping("member")  
//member/insert ,member/select이렇게 나옴


//배열 형태로 넣음
//@SessionAttributes({"id","pw"})
//모델에 login이라는 객체가 바인등 되어 있으면 sesion에 저장 하라
@SessionAttributes({"login"})
public class MemeberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(SessionStatus status) {
		status.setComplete();
		
		
		return "redirect:/member/list";
	}
	
	//session만들기 보다는 또다른 인터셉터 만들면 좋음
	@RequestMapping(value = "/loginpost",method = RequestMethod.POST)
	public String loginpost(LoginDTO login,Model model,HttpSession session) {
		//DB연동
		MemberDTO dto=memberService.loginpost(login);
		
		
		if(dto!=null) {
			model.addAttribute("login", dto);
			
			//
			String path=(String) session.getAttribute("path");
			if(path !=null) {
				return "redirect:"+path;
			}
			
			System.out.println(login.getId());
			System.out.println(login.getPw());
	
			return "redirect:/member/list";
			
		}else {
			return "redirect:/member/login";
			
		}
		
		
		///////
		
	     
		
	
		
		
		
	}
	
	
	
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public void login() {
		
	}
	
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
