package kr.co.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAdvice {
	//around advice 반듯이 넣어줘야 하는 파라미터 
	@Around("execution(* kr.co.service.AOPTestService*.*(..))")
	public void duration(ProceedingJoinPoint pjp) throws Throwable {
		long start=System.currentTimeMillis();
		
		pjp.proceed();
		
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	
	//애프터 어드바이스
	@After("execution(* kr.co.service.AOPTestService*.*(..))")
	public void end(JoinPoint jp) {
		
		System.out.println("************************");
	}
	
	
	
	//조인포인트 설정
	@Before("execution(* kr.co.service.AOPTestService*.*(..))")
	public void start(JoinPoint jp) {
		System.out.println(":::::::::::::::::::::::::::::::");
		System.out.println(jp.getKind());
		System.out.println(jp.getSignature());
		System.out.println(jp.getTarget());
		System.out.println(":::::::::::::::::::::::::::::::");
	}

}
