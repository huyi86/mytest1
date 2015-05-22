package my.test.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

public class ShiroCachemanager implements CacheManager,Destroyable{
	
	private SimpleCacheManager simpleCacheManager;
	
	
	
	public void setSimpleCacheManager(SimpleCacheManager simpleCacheManager) {
		this.simpleCacheManager = simpleCacheManager;
	}

	@Override
	public void destroy() throws Exception {
		this.simpleCacheManager.destroy();
	}

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return this.simpleCacheManager.getCache(name);
	}

}
