package my.test.shiro.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.test.util.memcached.MemcachedManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;


public class MemcachedShiroCache<K, V> implements Cache<K, V>{

	public static final Log log = LogFactory.getLog(MemcachedShiroCache.class);
	
	/** 
     * Memcached session key前缀 
     */  
    private final String MEMCACHED_SHIRO_SESSION = "shiro-session:"; 
	
	private MemcachedManager memcachedManager;
	
	public void setMemcachedManager(MemcachedManager memcachedManager) {
		this.memcachedManager = memcachedManager;
	} 
	  
    private String name;  
  
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MemcachedShiroCache(String name, MemcachedManager memcachedManager) {  
        this.name = name;  
        this.memcachedManager = memcachedManager;  
    }  
    
	@Override
	public void clear() throws CacheException {
		this.memcachedManager.deleteByGroupKey(MEMCACHED_SHIRO_SESSION);
	}

	@Override
	public V get(K k) throws CacheException {
		return (V)this.memcachedManager.get(k.toString());
	}

	@Override
	public Set<K> keys() {
		Set<K> keys = new HashSet<K>();
		List<String> list = this.memcachedManager.getKeys(MEMCACHED_SHIRO_SESSION);
		for(int i=0;i<list.size();i++){
			keys.add((K)list.get(i));
		}
		return keys;
	}

	@Override
	public V put(K arg0, V arg1) throws CacheException {
		this.memcachedManager.add(arg0.toString(), arg1);
		return arg1;
	}

	@Override
	public V remove(K arg0) throws CacheException {
		Object obj=this.memcachedManager.get(arg0.toString());
		this.memcachedManager.delete(arg0.toString());
		return obj==null?null:(V)obj;
	}

	@Override
	public int size() {
		if (keys() == null)  
            return 0;  
        return keys().size(); 
	}

	@Override
	public Collection<V> values() {
		Set<V> values = new HashSet<V>();
		List<Object> list = this.memcachedManager.getByGroupKey(MEMCACHED_SHIRO_SESSION);
		for(int i=0;i<list.size();i++){
			values.add((V)list.get(i));
		}
		return values;
	}

}
