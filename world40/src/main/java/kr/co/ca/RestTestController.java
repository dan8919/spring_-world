package kr.co.ca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



import kr.co.domain.LoginDTO;
//4踰꾩쟾 遺��꽣 �깮源�
@RestController
public class RestTestController {
	
	
	@RequestMapping(value="rt5",method=RequestMethod.GET)
	public Map<String, String> rt5(){
		/*
		 * @RequestBody String test1 System.out.println(test1); //속성 확인
		 * System.out.println(test1.getClass().getName());
		 */
	Map<String, String> map1 =new HashMap<>();
	map1.put("name","lee");
	map1.put("age","23");
		
		return map1;
	}
	
	
	
	
	@RequestMapping(value="rt1",method=RequestMethod.POST)
	public String rt1(@RequestBody Map<String, String> test1){
		System.out.println(test1);
		System.out.println(test1.get("test1"));
		//속성 확인
		System.out.println(test1.getClass().getName());
		
		return null;
	}
	
	
	@RequestMapping(value="rt2",method=RequestMethod.POST)
	public Map<String, Object> rt2(@RequestBody Map<String, Object> map)
{
		System.out.println(map.get("tes"));
		System.out.println(map.get("test2"));
		
	return map;
	
}
	@RequestMapping(value="rt3",method=RequestMethod.POST)
	public String rt3(@RequestBody Map<String, Object> map) {
		//Object obj=map.get("test4");
		   List<Map<String, Object>> list=(List<Map<String, Object>>) map.get("test4");
		//System.out.println(obj);
		
		   for(Map<String, Object> m:list) {
			   System.out.println(":::::::::::::::::::");
			   System.out.println(m.get("id"));
			   System.out.println(m.get("name"));
			   System.out.println(m.get("age"));
			   
			   System.out.println("::::::::::::::::::::::");
		   }
		
		return "hello";
	}
	
	@RequestMapping(value="rt4",method=RequestMethod.POST)
	public String tr4(@RequestBody Map<String,Object> map) {
		
		List<Map<String, Object>> list=(List<Map<String, Object>>) map.get("liststr");
		//System.out.println(list);
		
		for(Map<String, Object> m:list) {
			System.out.println("::::::::::::::::::::::::");
			System.out.println(m.get("id"));
			System.out.println(m.get("name"));
			System.out.println(m.get("age"));
			System.out.println("::::::::::::::::::::::::::");
			
		}
		
		return "world";
		
	}
	
}