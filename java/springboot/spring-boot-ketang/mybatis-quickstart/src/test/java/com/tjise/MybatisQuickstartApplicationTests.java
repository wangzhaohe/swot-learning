package com.tjise;

import com.tjise.dao.UserMapper;
import com.tjise.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisQuickstartApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testListUser(){
		List<User> list = userMapper.listUser();
		for (User user : list) {
			System.out.println("user = " + user);
		}
	}


}
