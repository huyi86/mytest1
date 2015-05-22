package my.test.shiro.cache.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

public class CustomShiroSessionDAO extends AbstractSessionDAO{

	private ShiroSessionRepository shiroSessionRepository; 

	public void setShiroSessionRepository(
			ShiroSessionRepository shiroSessionRepository) {
		this.shiroSessionRepository = shiroSessionRepository;
	} 
	
	@Override
	public void delete(Session arg0) {
		shiroSessionRepository.deleteSession(arg0.getId());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return shiroSessionRepository.getAllSessions();
	}

	@Override
	public void update(Session arg0) throws UnknownSessionException {
		shiroSessionRepository.saveSession(arg0);
	}

	@Override
	protected Serializable doCreate(Session arg0) {
		Serializable sessionId = this.generateSessionId(arg0);  
        this.assignSessionId(arg0, sessionId);  
        shiroSessionRepository.saveSession(arg0);  
        return sessionId; 
	}

	@Override
	protected Session doReadSession(Serializable arg0) {
		return shiroSessionRepository.getSession(arg0);  
	}

}
