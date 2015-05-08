package my.test.realm;

import my.test.service.UserService;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginRealm extends AuthenticatingRealm {
	@Autowired
	private UserService userService;

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = token.getPrincipal().toString();
		String password = new String((char[]) token.getCredentials());
//		 userService.getUserByUsername(username);
		if (!"zhangsan".equals(username)) {
			throw new UnknownAccountException(); // 如果用户名错误
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException(); // 如果密码错误
		}
		// 如果身份认证验证成功，返回一个AuthenticationInfo实现；
		return new SimpleAuthenticationInfo(username, password, this.getClass()
				.getSimpleName());
	}

}
