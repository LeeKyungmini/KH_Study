package com.kh.spring.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring.member.model.dto.Member;

//가상으로 만들어지는 web.xml을 사용해 테스트 환경을 구축
@WebAppConfiguration
//Junit을 실행할 방법
//테스트 때 사용할 가상의 applicationContext를 생성하고 관리
@RunWith(SpringJUnit4ClassRunner.class)
//가상의 applicationContext를 생성할 때 사용할 spring bean 설정파일의 위치를 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MybatisTest {
	
	//Junit annotation
	//@Before : 테스트 전에 실행될 메서드
	//@Test : 테스트 메서드
	//@After : 테스트 이후에 실행될 메서드
	
	//SqlSessionTemplate의 주요 메서드
	//selectOne : 단일행 select문 실행
	//selectList : 다중행 select문 실행
	//insert : 메서드의 결과값은 쿼리에 의해 영향을 받는 row 수
	//update : 메서드의 결과값은 쿼리에 의해 영향을 받는 row 수
	//delete : 메서드의 결과값은 쿼리에 의해 영향을 받는 row 수
	//** procedure 호출은 dml 쿼리메서드 중에서 선택
	
	
	@Autowired
	private SqlSessionTemplate session;
	private final String NAMESPACE = "com.kh.spring.mybatis.MybatisMapper.";
	private String userId = "DEV";
	
	@Test
	public void selectOneTest() {
		session.selectOne(NAMESPACE + "selectPassswordByUserId", userId);
	}
	
	@Test
	public void selectOneAsDto() {
		Member member = session.selectOne(NAMESPACE + "selectMemberByUserId", userId);
		System.out.println(member);
	}

	@Test
	public void selectListAsMap() {
		List<Map<String,Object>> res = session.selectList(NAMESPACE + "selectRentAndMemberByUserId", userId);
		for (Map<String, Object> map : res) {
			System.out.println(map);
		}
	}
	
	@Test
	public void selectListUsingResultMap() {
		List<Map<String,Object>> res = session.selectList(NAMESPACE + "selectRentBookByUserId", userId);
		for (Map<String, Object> map : res) {
			System.out.println(map);
		}
 	}
	
	@Test
	public void insertWithDto() {
		Member member = new Member();
		member.setUserId("mybatis");
		member.setPassword("abcdefg");
		member.setEmail("pclass@kh.com");
		member.setTell("010-0000-1111");

		session.insert(NAMESPACE + "insertWithDto", member);
	}
	
	@Test
	public void insertWithMap() {
		Member member = new Member();
		member.setUserId("spring-easy");
		
		Map<String,Object> commandMap = new HashMap<String, Object>();
		commandMap.put("member", member);
		commandMap.put("title","세션과 일곱 쿠키들");
		commandMap.put("rentBookCnt", 1);
		
		session.insert(NAMESPACE + "insertWithMap", commandMap);
	}
	
	@Test
	public void delete() {
		session.delete(NAMESPACE + "delete", "spring-easy");
	}
	
	//session.update
	//사용자 아이디가 DEV인 회원을 찾아 비밀번호를 ppppp로 변경하시오
	@Test
	public void update() {
		Member member = new Member();
		member.setUserId("DEV");
		member.setPassword("ppppp");
		session.update(NAMESPACE + "update", member);
	}
	
	@Test
	public void procedure() {
		session.update(NAMESPACE + "procedure", "100001");
	}
	
	//mybatis mapper escape 처리
	// <![CDATA[escape 처리할 내용]]>
	// 비교연산자 escape
	// &lt; &lt;= &gt; &gt;=
	
	//1. 도서명 : 쿠키와 세션,
	//   작가 : 김영아
	//   도서번호 : 시퀀스 사용인 도서를 BOOK 테이블에 저장하기
	//   메서드 이름 : test01
	
	@Test
	public void test01() {
		session.insert(NAMESPACE + "test01", Map.of("title","쿠키와세션","author","김영아"));
	}
	
	//2. 연장횟수가 2회 이상인 모든 대출도서 정보를
	//   연장횟수 0회로 초기화 해주세요.
	//   메서드 이름 : test02
	
	@Test
	public void test02() {
		int cnt = 0;
		session.update(NAMESPACE + "test02", cnt);
	}
	
	//3. 2021년 9월 이후 10월 이전 가입된 회원정보를 삭제 
	//   메서드 이름 : test03
	
	@Test
	public void test03() {
		session.delete(NAMESPACE + "test03");
	}
	
	//4. 대출 횟수가 가장 많은 3권의 도서를 조회
	//   메서드 이름 : test04
	
	@Test
	public void test04() {
		session.selectList(NAMESPACE + "test04");
	}
	
	
	
	
	
	

}
