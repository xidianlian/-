package dao;

import java.util.List;

import domain.User;

public interface UserDao {

	public User findUserById(Integer id);
	
	public List<User> findUserByName(String userName);
}
