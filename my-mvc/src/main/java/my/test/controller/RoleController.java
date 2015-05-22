package my.test.controller;

import java.util.List;

import my.test.pojo.Role;
import my.test.service.RoleService;
import my.test.vo.DataGrid;
import my.test.vo.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

@RequestMapping("role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;
    

    @RequestMapping("listAll")
    @ResponseBody
    public DataGrid listAll(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        DataGrid result = new DataGrid();
        PageInfo<Role> pageinfo = roleService.findRoleInPage(page, rows);
        result.setRows(pageinfo.getList());
        result.setTotal(pageinfo.getTotal());
        return result;
    }
    @RequestMapping("getAll")
    @ResponseBody
    public List<Role> getAll() {
        return roleService.findAll();
    }

   
    @RequestMapping("save")
    @ResponseBody
    public PageResult save(Role role,@RequestParam("auths")String auths) {
        try {
            roleService.saveRole(role,auths);
            return PageResult.build(200, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.build(500, "添加失败");
        }
    }

}
