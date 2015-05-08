package my.test.realm;

import org.apache.shiro.authc.AbstractAuthenticator;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;

public class MyAuthenticator extends AbstractAuthenticator {

	@Override
	protected AuthenticationInfo doAuthenticate(AuthenticationToken arg0)
			throws AuthenticationException {
		return null;
	}

}
