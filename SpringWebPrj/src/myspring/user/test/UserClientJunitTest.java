package myspring.user.test;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import myspring.user.dao.UserDao;
import myspring.user.service.UserService;
import myspring.user.vo.UserVO;

public class UserClientJunitTest {
	private ApplicationContext context;
//	@Autowired
//	UserService service;
	
	@Before
	public void init() {
		context = new GenericXmlApplicationContext("config/beans.xml");				
	}
	
	@Test
	public void daoTest() {
		UserDao dao = context.getBean("userDao", UserDao.class);
		
//		dao.insert(new UserVO("dooly1","둘리1", "남", "서울"));
//		dao.insert(new UserVO("dooly2","둘리2", "남", "서울"));
//		dao.insert(new UserVO("dooly3","둘리3", "남", "서울"));
//		dao.update(new UserVO("dooly3","둘리30", "여", "부산"));
//		dao.delete("dooly3");
		
		List<UserVO> list = dao.readAll();
		for (UserVO userVO : list) {
			System.out.println(userVO);
		}
		
		UserVO vo = dao.read("gildong");
		System.out.println(vo);
	}
	
	@Test
	@Ignore
	public void configTest() {
		SqlSession session = context.getBean("sqlSession",SqlSession.class);
		System.out.println(session.getClass().getName());
		
		UserVO vo = session.selectOne("userNS.selectUserById", "gildong");
		System.out.println(vo);
	}
	
	
	@Test
	@Ignore
	public void dataSourceTest() {
		DataSource ds = (DataSource) context.getBean("dataSource");
		try {
			System.out.println(ds.getConnection());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	@Ignore
	public void getUserTest() {
		UserService service = (UserService) context.getBean("userService");
		UserVO user = service.getUser("gildong");
		System.out.println("User 정보 : " + user);
		assertEquals("홍길동", user.getName());
	}

	@Test
	@Ignore
	public void updateUserTest() {
		UserService service = (UserService) context.getBean("userService");
		service.updateUser(new UserVO("gildong", "홍길동2", "남2", "서울2"));

		UserVO user = service.getUser("gildong");
		System.out.println(user);
	}

	@Test
	@Ignore
	public void insertUserTest() {
		UserService service = (UserService) context.getBean("userService");
		service.insertUser(new UserVO("dooly", "둘리", "남", "경기"));

		for (UserVO user : service.getUserList()) {
			System.out.println(user);
		}
	}
	
	@Test
	@Ignore
	public void deleteUserTest() {
		UserService service = (UserService) context.getBean("userService");
		service.deleteUser("dooly");

		for (UserVO user : service.getUserList()) {
			System.out.println(user);
		}
	}

	
}
