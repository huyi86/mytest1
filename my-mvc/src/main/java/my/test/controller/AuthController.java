package my.test.controller;

import java.util.List;

import my.test.pojo.Authority;
import my.test.service.AuthService;
import my.test.vo.DataGrid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

@RequestMapping("auth")
@Controller
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping("listAll")
	@ResponseBody
	public DataGrid listAll(@RequestParam("page")int page,@RequestParam("rows")int rows){
		DataGrid result = new DataGrid();
		PageInfo<Authority> pageinfo = authService.findAuthInPage(page,rows);
		result.setRows(pageinfo.getList());
		result.setTotal(pageinfo.getTotal());
		return result;
	}
	@RequestMapping("treeList")
	@ResponseBody
	public List<Authority> treeList(@RequestParam(value = "id",defaultValue="0")Long pid){
		return authService.findAuthorityTree(pid);
	}
}
