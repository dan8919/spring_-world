package kr.co.inter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MyInter extends HandlerInterceptorAdapter {
//servlet-context에 설정 필요함
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("preHandle::::::::::::::::::::::::::::::::::::::");
		
		HandlerMethod hmethod = (HandlerMethod) handler;
		Method method=hmethod.getMethod();
		System.out.println(hmethod.getBean());//어떤 controller 사용했는지 확인 가능
		System.out.println(method);     //어떤 메소드를 사용했는지 확인 가능
		
		
		
		///true면 정상적으로 다음단계 진행
		return true;
	}

	//homecontroller
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	
		System.out.println("postHandle::::::::::::::::::::::::::::::::::::");
		
		Object test=modelAndView.getModel().get("test");
		modelAndView.getModel().put("show", "show");
		Object showinput=modelAndView.getModel().get("show");
		System.out.println(test);
		
		System.out.println(showinput);
		
		
		
	}

	
	

}
