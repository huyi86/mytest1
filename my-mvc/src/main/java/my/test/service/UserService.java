package my.test.service;

import java.util.List;

import my.test.mapper.UserMapper;
import my.test.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserService{
	@Autowired
	private UserMapper userMapper;

	public void saveUser(User user) {
		userMapper.insertSelective(user);
	}

	public PageInfo<User> findAllUser(int page, int rows) {
		PageHelper.startPage(page, rows);
		List<User> list = userMapper.selectAll();
		return  new PageInfo<User>(list);

	}
	
}
