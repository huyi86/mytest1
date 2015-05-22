package my.test.shiro.cache;

import my.test.util.memcached.MemcachedManager;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;

public class SimpleCacheManagerImpl implements SimpleCacheManager {

	@Autowired
	private MemcachedManager memcachedManager;
	
	
	@Override
	public <K,V> Cache<K, V> getCache(String name) throws CacheException {
		return new MemcachedShiroCache<K, V>(name, memcachedManager);
	}


	@Override
	public void destroy() throws CacheException {
		this.memcachedManager.shutDown();
	}

}
