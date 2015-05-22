package my.test.shiro.cache.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.test.util.memcached.MemcachedManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroSessionRepositoryImpl implements ShiroSessionRepository{

	public static final Log log = LogFactory.getLog(ShiroSessionRepositoryImpl.class);
	
	/** 
     * Memcached session key前缀 
     */  
    private final String MEMCACHED_SHIRO_SESSION = "shiro-session:"; 
    
	@Autowired
	private MemcachedManager memcachedManager;
	
	@Override
	public void saveSession(Session session) {
		if (session == null || session.getId() == null) {  
			log.error("session或者session id为空");  
            return;  
        }  
 
        try {  
        	Object obj=memcachedManager.get(this.getMemcachedSessionKey(session.getId()));
        	if(obj == null){
        		memcachedManager.add(this.getMemcachedSessionKey(session.getId()), session ,MEMCACHED_SHIRO_SESSION) ;
        	} else {
        		memcachedManager.replace(this.getMemcachedSessionKey(session.getId()), session);
        	}
        } catch (Exception e) {  
        	log.error("保存session失败", e);  
        }  
	}

	@Override
	public void deleteSession(Serializable sessionId) {
		if (sessionId == null) {  
			log.error("id为空");  
            return;  
        }  
		
        try {  
        	memcachedManager.delete(this.getMemcachedSessionKey(sessionId) ,MEMCACHED_SHIRO_SESSION);  
        } catch (Exception e) {  
        	log.error("删除session失败", e);  
        } 
	}

	@Override
	public Session getSession(Serializable sessionId) {
		if (sessionId == null) {  
			log.error("id为空");  
            return null;  
        }  
		
        Session session = null;  
        try {  
        	session = (Session)memcachedManager.get(this.getMemcachedSessionKey(sessionId));
        } catch (Exception e) {  
        	log.error("获取id为" + sessionId  + "的session失败", e);  
        } 
        
        return session; 
	}

	@Override
	public Collection<Session> getAllSessions() {
		Set<Session> sessions = new HashSet<Session>();  
        try {  
        	List<Object> list= memcachedManager.getByGroupKey(MEMCACHED_SHIRO_SESSION);
        	for(int i=0;i<list.size();i++){
        		sessions.add((Session)list.get(i));
        	}
        } catch (Exception e) {  
            log.error("获取所有session失败", e);  
        } 
 
        return sessions;  
	}

	/** 
     * 获取memcached中的session key 
     *  
     * @param sessionId 
     * @return 
     */  
    private String getMemcachedSessionKey(Serializable sessionId) {  
        return this.MEMCACHED_SHIRO_SESSION + sessionId;  
    } 

}
