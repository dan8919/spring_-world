package kr.co.ca;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//수정test1
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	//안녕5
	//안녕6
	//안녕7
	//안녕8
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */


	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert(Model model, String name) {
		
		name=toKor(name);

		model.addAttribute("name", name);

		// return "insert";
	}

	private String toKor(String name) {
		
		
		try {
			return new String(name.getBytes("8859_1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		model.addAttribute("serverTime", "�븞�뀞�븯�꽭�슂");

		return "home";
	}//안녕2

}//안녕1

//안녕4
