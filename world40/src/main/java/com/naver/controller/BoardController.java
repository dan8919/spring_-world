package com.naver.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.domain.BoardVO;
import kr.co.domain.PageTO;
import kr.co.service.BoardService;
import kr.co.utils.Utils;

@Controller
@RequestMapping("board")
public class BoardController {
	
	@Inject
	private BoardService bService;
	

	
	
	
	@RequestMapping(value="/searchlist",method=RequestMethod.GET)
	public String serchlist(Model model,String searchType,String keyword) {
		System.out.println(searchType);
		
	List<BoardVO>list=bService.searchlist(searchType,keyword);
		
		model.addAttribute("list", list);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		
		return "board/searchlist";
		
	}
	
	
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
		//+vo.getBno();   (�빐�떦 bno媛� �뾾�쑝硫� �닔�젙�썑 �긽�꽭�럹�씠吏�瑜� 李얠쓣 �닔 �뾾�쓬)
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
		// void 濡� �븯硫� �븞�맖? void濡� �븯硫� board/read/5.jsp 濡� 媛�. �쁽�옱 read.jsp諛뽰뿉 �뾾湲곕븣臾몄뿉 404�뿉�윭 硫붿떆吏� �쑙.
		return "board/read";
	}
	
	//�럹�씠吏� 泥섎━
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model,String  curPage) {
		//System.out.println("::::::::::::::::::::::::::::::::::::::::::::");
		//System.out.println(curPage);
		int page=-1;
		if(curPage==null) {
			page=1;
		}else {
			page=Integer.parseInt(curPage);
		}
		
		PageTO<BoardVO> to=new PageTO<BoardVO>(page);
		
		
		 to = bService.list(to);
		 
		 System.out.println(to.getBeginPageNum());
		 
		 
		model.addAttribute("to", to);
		model.addAttribute("list", to.getList());
	}
	
	
	  @RequestMapping(value = "/insert", method = RequestMethod.POST) public String
	   insert(BoardVO vo) {
	 
	   bService.insert(vo);
	  
	 
	   return "redirect:/board/list"; }
	 
	 
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert() {
		
	}
	
	
}
