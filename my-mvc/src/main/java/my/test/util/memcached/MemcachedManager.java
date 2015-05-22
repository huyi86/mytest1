package my.test.util.memcached;

import java.util.Date;
import java.util.List;

public interface MemcachedManager {
	
	public  static  String offerUrl = "http://www.51offer.com";

	public void init() ;

	/**
	 * 添加对象到缓存中，构成方法重载
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) ;

	public boolean add(String key, Object value, String groupKey) ;

	public boolean add(String key, Object value,Date expiry);
	
	public boolean add(String key, Object value,Date expiry, String groupKey);
	
	public boolean add(String key,long secs,Object value);
	
	public boolean replace(String key, Object value) ;
	
	public boolean replace(String key, Object value, Date expiry);
	
	/**
	 * 根据指定的关键字获取对象
	 */
	public Object get(String key) ;

	public List<String> getKeys(String groupKey) ;

	public List<Object> getByGroupKey(String groupKey);

	/**
	 * 根据指定的关键字删除对象
	 */
	public Object delete(String key) ;

	public Object delete(String key, String groupKey) ;

	public Object deleteByGroupKey(String groupKey) ;


	public void shutDown() ;

	public void set(String key, int expre, String salt);

}
