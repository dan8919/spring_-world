package kr.co.ca;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	// null나옴

	// who
	@RequestMapping("test5")
	public void test5(@ModelAttribute("str2") String mm) {
		System.out.println(mm);

	}

	// 주소 뒤에 데이터가 안붙음
	@RequestMapping("test4")
	public String test4(RedirectAttributes rtts) {

		rtts.addFlashAttribute("str2", "who");
		return "redirect:/test5";
	}

	// 주소 뒤에 데이터가 붙음 , http://localhost:8089/test3?str1=what
	@RequestMapping("test2")
	public String test2(Model model) {
		model.addAttribute("str1", "what");

		return "redirect:/test3";
	}

	// 주소 뒤에 데이터가 안 붙음,model에 바인딩 하는 방법
	@RequestMapping("test1")
	public String test1(Model model) {
		model.addAttribute("msg", "~~~~~~~~~~~~~~~~~~~~~~~");
		return "test1";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

}
