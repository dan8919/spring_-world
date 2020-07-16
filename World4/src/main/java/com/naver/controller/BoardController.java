package com.naver.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.domain.BoardVO;
import kr.co.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Inject
	private BoardService bService;
	@RequestMapping(value="/delete/{bno}",method=RequestMethod.GET)
	public String delete(@PathVariable("bno") int bno) {
		bService.delete(bno);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(BoardVO vo) {
		
		bService.update(vo);
		
		//???????????????????????????????????????
		return "redirect:/board/read/"+vo.getBno();
		//+vo.getBno();   (해당 bno가 없으면 수정후 상세페이지를 찾을 수 없음)
	}
	
	@RequestMapping(value="/update/{bno}",method=RequestMethod.GET)
	public String update(Model model,@PathVariable("bno") int bno) {
		BoardVO vo=bService.updateUI(bno);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	@RequestMapping(value="/read/{bno}",method=RequestMethod.GET)
	public String read(Model model,@PathVariable("bno") int bno) {
		BoardVO vo=bService.read(bno);
		model.addAttribute("vo", vo);
		// void 로 하면 안됨? void로 하면 board/read/5.jsp 로 감. 현재 read.jsp밖에 없기때문에 404에러 메시지 뜸.
		return "board/read";
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) {
		List<BoardVO> list = bService.list();
		
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(BoardVO vo) {
		
		bService.insert(vo);
		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
	
	
}
