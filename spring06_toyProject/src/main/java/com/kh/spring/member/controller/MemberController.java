package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.kh.spring.member.model.dto.Member;
import com.kh.spring.member.model.service.MemberService;

	//1. @Controller : 해당 클래스를 applicationContext에 bean으로 등록
	//				   Controller와 관련된 annotation을 사용할 수 있게 해준다.
	//2. @RequestMapping : 요청 URL과 Controller의 메서드 매핑을 지원
	//				   클래스 위에 선언할 경우, 해당 클래스의 모든 매서드가 지정된 경로를 상위경로로 가진다.
	//3. @getMapping : Get 방식의 요청 URL과 Controller의 메서드 매핑을 지원
	//4. @PostMapping : Post 방식의 요청 URL과 Controller의 메서드 매핑을 지원
	//5. @RequestParam : 요청 파라밑를 컨트롤러 메서드의 매개변수에 바인드
	//					 단, Content-type이 application/x-www-form-urlEncoded 인 경우에만 가능
	//					 FormHttpMessageConverter가 동작
	//6. @ModelAttribute : 요청 파라미터를 setter사용해 메서드 매개변수에 선언된 객체에 바인드
	//				 	   Model객체의 attribute로 자동으로 저장
	//7. @RequestBody : 요청 body를 읽어서 자바의 객체에 바인드
	//					application/x-www-form-urlEncoded를 지원하지 않는다.
	//8. @RequestHeader : 요청 헤더를 메서드의 매개변수에 바인드
	//9. @SessionAttribute : 원하는 session이 속성값을 매개변수에 바인드
	//10. @CookieValue : 원하는 cookie값을 매개변수에 바인드
	//11. @PathVariable : url 템플릿에 담긴 파라미터값을 매개변수에 바인드
	//12. @ResponseBody : 메서드가 반환하는 값을 응답 body에 작성
	//13. Servlet객체를 컨트롤러의 매갭변수에 선언해 주입받을 수 있다.
	//	  HttpServletRequest, HttpServletResponse, HttpSession
	
@Controller
@RequestMapping("member")
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("join-form")
	public void joinForm() {}
	
	@PostMapping("join")
	public String join(Member member) {
		memberService.insertMember(member);
		return "index";
	}
	
	@PostMapping("join-json")
	public String oinWithJson(@RequestBody Member member) {
		logger.debug(member.toString());
		return "index";
	}
	
	//로그인 페이지 이동 메서드
	//메서드명 : login
	@GetMapping("login")
	public void login() {
	}
	
	//로그인 실행 메서드
	//메서드명 : loginImpl
	//재지정할 jsp : mypage
	@PostMapping("login")
	public String loginImpl(Member member, HttpSession session) {
		Member certifiedUser = memberService.authenticateUser(member);
		session.setAttribute("authentication", certifiedUser);
		logger.debug(certifiedUser.toString());
		return "redirect:/member/mypage";
	}
	
	@GetMapping("mypage")
	public void mypage(@CookieValue(name = "JSESSIONID") String sessionId
					, @SessionAttribute(name = "authentication") Member member) {
		logger.debug("@CookieValue : " + sessionId);
		logger.debug("@SessionAttribute : " + member);
	}
}