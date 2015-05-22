package my.test.shiro;

import java.util.List;

import my.test.pojo.Authority;
import my.test.pojo.Role;
import my.test.pojo.User;
import my.test.service.AuthService;
import my.test.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class LoginRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthService authService;
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
    	System.out.println("授权");
        Subject subject = SecurityUtils.getSubject();
        String code = (String) subject.getPrincipal();
        User user = userService.findUserByUsername(code);
        List<Role> roles = user.getRoles();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : roles) {
            info.addRole(role.getCode());
            List<Authority> auths = authService.findAuthByRole(role.getId());
            for (Authority authority : auths) {
                info.addStringPermission(authority.getCode());
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("用户认证");
        UsernamePasswordToken token_UP = (UsernamePasswordToken) token;
        String code = token_UP.getUsername();
        String password = new String(token_UP.getPassword());
        
        
        User user = userService.findUserByUsername(code);
        if(user!=null){
            if(password.equals(user.getPassword())){
                return  new SimpleAuthenticationInfo(code, password, super.getName());
            }
        }
        return null;
    }

}
