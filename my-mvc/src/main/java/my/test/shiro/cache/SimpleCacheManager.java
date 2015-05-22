package my.test.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

/**
 * 
 * 缓存管理器接口
 * 
 * @author shadow
 * 
 */

public interface SimpleCacheManager {

	

	/**
	 * 获取缓存堆
	 * 
	 * @param name
	 * @return
	 * @throws CacheException
	 */
	public abstract <K, V> Cache<K, V> getCache(String name);


	/**
	 * 注销管理器
	 */
	public abstract void destroy() throws CacheException;

}
