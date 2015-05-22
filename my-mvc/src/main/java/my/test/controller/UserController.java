package my.test.controller;

import javax.validation.Valid;

import my.test.pojo.User;
import my.test.service.UserService;
import my.test.vo.DataGrid;
import my.test.vo.PageResult;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public PageResult login(@Valid User user, BindingResult result) {
        // 校验数据的合法性
        /*
           if (result.hasErrors()) { List<ObjectError> errors = result.getAllErrors(); List<String>
           errorMsgs = new ArrayList<String>(); for (ObjectError objectError : errors) {
           errorMsgs.add(objectError.getDefaultMessage()); } return PageResult.build(201,
           StringUtils.join(errorMsgs, "|")); }
         */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getCode(), user.getPassword());
        try {
            subject.login(token);
            return PageResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.build(202, "登录失败");
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public PageResult save(User user) {
        try {
            if (user != null) {
                userService.saveUser(user);
            }
            return PageResult.build(200, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.build(500, "添加失败");
        }
    }

    @RequestMapping("listAll")
    @ResponseBody
    public DataGrid listAll(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        DataGrid result = new DataGrid();
        PageInfo<User> pageInfo = userService.findAllUser(page, rows);
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @RequestMapping("addRole")
    @ResponseBody
    public PageResult addRole(@RequestParam("id") Long id, @RequestParam("roleIds") String ids) {
        try {
            this.userService.addRole(id, ids);
            return PageResult.build(200, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.build(500, "添加失败");
        }
    }
   
}
