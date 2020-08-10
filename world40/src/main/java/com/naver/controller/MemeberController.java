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
//member/insert ,member/select�씠�젃寃� �굹�샂


//諛곗뿴 �삎�깭濡� �꽔�쓬
//@SessionAttributes({"id","pw"})
//紐⑤뜽�뿉 login�씠�씪�뒗 媛앹껜媛� 諛붿씤�벑 �릺�뼱 �엳�쑝硫� sesion�뿉 ���옣 �븯�씪
@SessionAttributes({"login"})
public class MemeberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(SessionStatus status) {
		status.setComplete();
		
		
		return "redirect:/member/list";
	}
	
	//session留뚮뱾湲� 蹂대떎�뒗 �삉�떎瑜� �씤�꽣�뀎�꽣 留뚮뱾硫� 醫뗭쓬
	@RequestMapping(value = "/loginpost",method = RequestMethod.POST)
	public String loginpost(LoginDTO login,Model model,HttpSession session) {
		//DB�뿰�룞
		MemberDTO dto=memberService.loginpost(login);
		
		
		if(dto!=null) {
			model.addAttribute("login", dto);
			
			
			String path=(String) session.getAttribute("path");
			System.out.println(path);
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
	
	
	//�솢 post 諛⑹떇�쑝濡�?
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
