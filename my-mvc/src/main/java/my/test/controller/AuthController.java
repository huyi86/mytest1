package my.test.controller;

import java.util.List;

import my.test.pojo.Authority;
import my.test.service.AuthService;
import my.test.vo.DataGrid;
import my.test.vo.PageResult;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

import com.github.pagehelper.PageInfo;

@RequestMapping("auth")
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;
    

    @RequestMapping("listAll")
    @ResponseBody
    @RequiresRoles("admin")
    public DataGrid listAll(@RequestParam("page") int page, @RequestParam("rows") int rows) {
        DataGrid result = new DataGrid();
        PageInfo<Authority> pageinfo = authService.findAuthInPage(page, rows);
        result.setRows(pageinfo.getList());
        result.setTotal(pageinfo.getTotal());
        return result;
    }
    
    @ExceptionHandler({UnauthorizedException.class})  
    public String processUnauthorizedException(NativeWebRequest request, UnauthorizedException e) {  
        return "unauthorized";  
    } 
    @ExceptionHandler({UnauthenticatedException.class})  
    public String processUnauthenticatedException(NativeWebRequest request, UnauthenticatedException e) {  
        return "unauthenticated";  
    } 
    
    @RequestMapping("treeList")
    @ResponseBody
    public List<Authority> treeList(@RequestParam(value = "id", defaultValue = "0") Long pid) {
        return authService.findAuthorityTree(pid);
    }

    @RequestMapping("save")
    @ResponseBody
    @RequiresRoles("admin")
    public PageResult save(Authority auth) {
        try {
            if(auth.getPid()==null)
                auth.setPid(0L);
            authService.saveAuth(auth);
            return PageResult.build(200, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return PageResult.build(500, "添加失败");
        }
    }

}
