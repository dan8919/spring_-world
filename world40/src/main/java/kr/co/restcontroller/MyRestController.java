package kr.co.restcontroller;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.domain.ReplyVO;
import kr.co.service.ReplyService;


@RestController
public class MyRestController {
	@Inject
	private ReplyService rService;
	//delete �궘�젣
	@RequestMapping(value="/replies",method = RequestMethod.DELETE)
	public String delete(@RequestBody ReplyVO vo) {
		
		int i=rService.delete(vo.getRno());
		if(i==1) {
			return "success";
		}
		return "fail";
	}
	
	//put �닔�젙
	@RequestMapping(value="/replies/{rno}",method=RequestMethod.PUT)
	public String Update(@PathVariable("rno") int rno,@RequestBody ReplyVO vo) {
		System.out.println(vo);
		vo.setRno(rno);
		int i=rService.update(vo);
		System.out.println(rno);
		if(i==1) {
			return "success";
			
		}
		
		
		return "fail";
		
	}
	//GET議고쉶
	@RequestMapping(value = "/replies/all/{bno}",method = RequestMethod.GET)
	public List<ReplyVO> list(@PathVariable("bno") int bno){
		List<ReplyVO> list=rService.list(bno);
		return list;
	}
	
	//post �깮�꽦
	@RequestMapping(value="/replies",method=RequestMethod.POST)
	public String insert(@RequestBody ReplyVO vo) {
		int i=rService.insert(vo);
		if(i==1) {
			return "success";
			
		}else {
			return "fail";
		}
	}

}
