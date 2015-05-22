package my.test.service;

import java.util.List;

import my.test.mapper.RoleMapper;
import my.test.pojo.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    
    public PageInfo<Role> findRoleInPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Role> list = roleMapper.selectAll();
        return new PageInfo<Role>(list);
    }

    public void saveRole(Role role,String auths) {
        String[] ids = auths.split(",");
        roleMapper.insertSelective(role);
        for (String aid : ids) {
            roleMapper.saveAuthsInRole(role.getId(),Long.valueOf(aid));
        }
    }

    public List<Role> findAll() {
        return roleMapper.selectAll();
    }

}
