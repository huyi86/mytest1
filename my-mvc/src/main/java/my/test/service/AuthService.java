package my.test.service;

import java.util.List;

import my.test.mapper.AuthorityMapper;
import my.test.pojo.Authority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AuthService {
	
	@Autowired
	private AuthorityMapper authMapper;


	public PageInfo<Authority> findAuthInPage(int page,int rows) {
		PageHelper.startPage(page, rows);
		List<Authority> list = authMapper.selectAll();
		return new PageInfo<Authority>(list);
	}


	public List<Authority> findAuthorityTree(Long pid) {
		List<Authority> list = authMapper.selectAuthByPid(pid);
		return  list;
	}


    public void saveAuth(Authority auth) {
        authMapper.insertSelective(auth);
    }


    public List<Authority> findAuthByRole(Long id) {
        return authMapper.selectAuthByRole(id);
    }
	
	
}
