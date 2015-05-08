package my.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import my.test.pojo.User;
import my.test.service.UserService;
import my.test.vo.DataGrid;
import my.test.vo.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
	public PageResult login(@Valid User user,BindingResult result){
		// 校验数据的合法性
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            List<String> errorMsgs = new ArrayList<String>();
            for (ObjectError objectError : errors) {
                errorMsgs.add(objectError.getDefaultMessage());
            }
            return PageResult.build(201, StringUtils.join(errorMsgs, "|"));
        }
/*		IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory("classpath:shiro-loginAndOut.ini");
		SecurityManager securityManager = managerFactory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getCode(), user.getPassword());
		try {  
	        subject.login(token);  
	    } catch(Exception e){
	    	e.printStackTrace();
	    	return new PageResult(202);
	    }
*/		return PageResult.ok();
	}
	
	@RequestMapping("/save")
	public PageResult save(User user){
		try {
			if(user!=null){
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
	public DataGrid listAll(@RequestParam("page")int page,@RequestParam("rows")int rows){
		DataGrid result = new DataGrid();
		PageInfo<User> pageInfo = userService.findAllUser(page,rows);
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	
}
