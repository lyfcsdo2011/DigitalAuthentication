package com.dao;

import com.domain.Users;
import com.utils.SHAUtil;

public class UserDAO {
	public static Users[] CreateUsers() {
		Users users[] = new Users[3];
		users[0] = new Users("admin", SHAUtil.getSHA256("123456"));
		users[1] = new Users("root", SHAUtil.getSHA256("root"));
		users[2] = new Users("lyf", SHAUtil.getSHA256("lyf123"));
		return users;
	}
	public static Users getUser(String name) {
		Users users[] = CreateUsers();
		for(int i = 0; i < 3; i++) {
			if(name.equals(users[i].getUserName())) {
				return users[i];
			}
		}
		return null;
	}
}
