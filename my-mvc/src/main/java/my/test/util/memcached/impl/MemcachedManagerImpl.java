package my.test.util.memcached.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import my.test.util.SecurityUtils;
import my.test.util.memcached.MemcachedManager;
import my.test.util.memcached.client.MemcachedClient;
import my.test.util.memcached.client.SockIOPool;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

public class MemcachedManagerImpl  implements MemcachedManager{

	public static final Log log = LogFactory.getLog(MemcachedManager.class);

	private MemcachedClient mcc;
	private SockIOPool pool;

	private List<String> servers;
	private int initConn = 5;
	private int minConn = 5;
	private int maxConn = 250;
	private int maxIdle = 1000 * 10;
	private int maintSleep = 1000 * 30;
	private int socketTO = 1000;
	private int socketConnectTO = 1000;
	private long compressThreshold = 64 * 1024;
	
	//Added by Tangm at 20150507 For UserController
	private String channel;
	private static final String SEPARATOR = "-";
		

	public MemcachedManagerImpl() {
		
	}

	public MemcachedManagerImpl(String profile) {
		try {
			servers = new ArrayList<String>();
			Properties prop = new Properties();
			InputStream in = MemcachedManager.class
					.getResourceAsStream(profile);
			prop.load(in);

			// servers
			int i = 0;
			boolean ismore = true;
			while (ismore) {
				i++;
				String memcached = prop.getProperty("memcached.n" + i);
				if (memcached == null) {
					ismore = false;
				} else {
					servers.add(memcached);
				}
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
	}

	public void init() {
		// 创建MemCachedClient
		mcc = new MemcachedClient();

		// 创建服务器列表及其权重
		List<Integer> weights = new ArrayList<Integer>();
		if (servers == null || servers.size() < 1) {
			weights.add(1);
		} else {
			for (int i = 0; i < servers.size(); i++) {
				weights.add(1);
			}
		}

		// 创建Socket连接池对象
		pool = SockIOPool.getInstance();

		// 设置服务器信息
		pool.setServers(servers.toArray(new String[0]));
		pool.setWeights(weights.toArray(new Integer[0]));
		pool.setFailover(true);

		// 设置初始连接数、最小和最大连接数以及最大处理时间
		pool.setInitConn(initConn);
		pool.setMinConn(minConn);
		pool.setMaxConn(maxConn);
		pool.setMaxIdle(maxIdle);

		// 设置主线程睡眠时间
		pool.setMaintSleep(maintSleep);

		// 设置TCP参数、连接超时等
		pool.setNagle(false);
		pool.setSocketTO(socketTO);
		pool.setSocketConnectTO(socketConnectTO);
		pool.setAliveCheck(true);

		// 初始化连接池
		pool.initialize();

		// 压缩设置，超过指定大小（单位为K）的数据都会被压缩
		mcc.setCompressEnable(true);
		mcc.setCompressThreshold(compressThreshold);
	}

	/**
	 * 添加对象到缓存中，构成方法重载
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean add(String key, Object value) {		
		String keyStr = toKeyString(key);
		Object v = this.get(key);
		if(v == null){
			return mcc.add(keyStr, value);
		}else{
			return mcc.replace(keyStr, value);
		}
	}
	
	public boolean add(String key, Object value, String groupKey) {
		String keyStr = toKeyString(key);
		List<String> group = new ArrayList<String>();
		Object obj = get(groupKey);
		if (obj != null) {
			try {
				List<String> list = (List<String>) obj;
				if(list.contains(keyStr)){
					return mcc.replace(keyStr, value);
				}
				group.addAll(list);
			} catch (Exception e) {
				log.error("添加group出错");
			}
			
			group.add(keyStr);
			replace(groupKey, group);
		} else {
			group.add(keyStr);
			add(groupKey, group);
		}

		return add(key, value);
	}

	public boolean add(String key, Object value,Date expiry) {
		String keyStr = toKeyString(key);
		Object v = this.get(key);
		if(v == null){
			return mcc.add(keyStr, value, expiry);
		}else{
			return mcc.replace(keyStr, value, expiry);
		}
	}
	
	public boolean add(String key,long secs,Object value){
		String keyStr = toKeyString(key);
		Date now = new Date();
		now.setTime(now.getTime()+secs*1000);
		Object v = this.get(key);
		if(v == null){
			return mcc.add(keyStr, value, now);
		}else{
			return mcc.replace(keyStr, value, now);
		}
	}
	
	public boolean add(String key, Object value,Date expiry, String groupKey) {
		String keyStr = toKeyString(key);
		List<String> group = new ArrayList<String>();
		Object obj = get(groupKey);
		if (obj != null) {
			try {
				List<String> list = (List<String>) obj;
				if(list.contains(keyStr)){
					return mcc.replace(keyStr, value, expiry);
				}
				group.addAll(list);
			} catch (Exception e) {
				log.error("添加group出错");
			}
			
			group.add(keyStr);
			replace(groupKey, group);
		} else {
			group.add(keyStr);
			add(groupKey, group);
		}

		return add(key, value ,expiry);
	}
	
	public boolean replace(String key, Object value) {
		String keyStr = toKeyString(key);
		return mcc.replace(keyStr, value);
	}

	public boolean replace(String key, Object value, Date expiry) {
		String keyStr = toKeyString(key);
		return mcc.replace(keyStr, value ,expiry);
	}
	
	/**
	 * 根据指定的关键字获取对象
	 */
	public Object get(String key) {
		String keyStr = toKeyString(key);
		return mcc.get(keyStr);
	}

	public List<String> getKeys(String groupKey) {
		return (List<String>) get(groupKey);
	}

	public List<Object> getByGroupKey(String groupKey) {
		List<Object> groupValues = new ArrayList<Object>();
		Object obj = get(groupKey);
		if (obj != null) {
			try {
				List<String> group = (List<String>) obj;
				for (int i = 0; i < group.size(); i++) {
					groupValues.add(get(group.get(i)));
				}
			} catch (Exception e) {
				log.error("添加group出错");
			}
		}
		return groupValues;
	}

	/**
	 * 根据指定的关键字删除对象
	 */
	public Object delete(String key) {
		String keyStr = toKeyString(key);
		return mcc.delete(keyStr);
	}

	public Object delete(String key, String groupKey) {
		String keyStr = toKeyString(key);
		Object obj = get(groupKey);
		if (obj != null) {
			try {
				List<String> group = (List<String>) obj;
				group.remove(keyStr);
				replace(groupKey, group);
			} catch (Exception e) {
				log.error("删除group出错");
			}
		}
		return delete(key);
	}

	public Object deleteByGroupKey(String groupKey) {
		Object obj = get(groupKey);
		if (obj != null) {
			try {
				List<String> group = (List<String>) obj;
				for (int i = 0; i < group.size(); i++) {
					mcc.delete(group.get(i));
				}
			} catch (Exception e) {
				log.error("删除group出错", e);
			}
		}
		return delete(groupKey);
	}

	public List<String> getServers() {
		return servers;
	}

	public void setServers(List<String> servers) {
		this.servers = servers;
	}

	public int getInitConn() {
		return initConn;
	}

	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaintSleep() {
		return maintSleep;
	}

	public void setMaintSleep(int maintSleep) {
		this.maintSleep = maintSleep;
	}

	public int getSocketTO() {
		return socketTO;
	}

	public void setSocketTO(int socketTO) {
		this.socketTO = socketTO;
	}

	public int getSocketConnectTO() {
		return socketConnectTO;
	}

	public void setSocketConnectTO(int socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}

	public long getCompressThreshold() {
		return compressThreshold;
	}

	public void setCompressThreshold(long compressThreshold) {
		this.compressThreshold = compressThreshold;
	}

	public void shutDown() {
		this.pool.shutDown();
	}

	private String toKeyString(final Object key) {
		String keyString = SecurityUtils.md5(key.toString()); // issue #1, key too long
		if (log.isDebugEnabled()) {
			log.debug("Object key '" + key + "' converted in '" + keyString	+ "'");
		}
		return keyString;
	}

	//Added by Tangm at 20150507 For UserController
	protected String composeKey(String key) {
		String result = this.channel + SEPARATOR + key;
		return result;
	}
	/**
	 * @param key
	 * @param expre 单位 秒
	 * @param value
	 */
	public void set(String key, int expre, String salt) {
		Assert.notNull(key, "设置memcache的key不可为空");
		Assert.isTrue(expre >= 0, "expre time must >= 0");
		String realKey = composeKey(key);
		set(realKey, expre, salt);
	}
}
