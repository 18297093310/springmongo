package cn.jiestudy.springmongo;

import cn.jiestudy.springmongo.beans.User;
import cn.jiestudy.springmongo.dao.UserDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmongoApplicationTests {

	@Autowired
	private UserDao userDaoImpl;

	private static String collectionName;
	@BeforeClass
	public static void initSpring() {
		try {
			collectionName ="a";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUser() {
		for(int i=0;i<100;i++){
			User user =new User();
			user.setId(""+i);
			user.setAge(i);
			user.setName("zcy"+i);
			user.setPassword("zcy"+i);
			userDaoImpl.insert(user,collectionName);
		}
	}

	@Test
	public void testFindUser(){

		Map<String,Object> params=new HashMap<String,Object>();
		params.put("maxAge", 50);
		List<User> list=userDaoImpl.findAll(params,collectionName);
		System.out.println("user.count()=="+list.size());
	}


	@Test
	public void testUdate(){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("id", "1");
		User user=userDaoImpl.findOne(params, collectionName);
		System.out.println("user.name==="+user.getName());
		System.out.println("=============update==================");
		params.put("name", "hello");
		userDaoImpl.update(params, collectionName);
		user=userDaoImpl.findOne(params, collectionName);
		System.out.println("user.name==="+user.getName());
	}

	@Test
	public void testRemove() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", "2");
		userDaoImpl.remove(params, collectionName);
		User user = userDaoImpl.findOne(params, collectionName);
		System.out.println("user==" + user);
	}
}
