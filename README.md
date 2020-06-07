<link href="http://thomasf.github.io/solarized-css/solarized-light.min.css" rel="stylesheet"></link>

# spring-cache-ch-1 recording

### 基礎環境
1. DB<br>
2. POJO<br>
3. 配置持久層<br>
   1.connection DB<br>
   2.使用myBatis mapper(@MapperScan("path"))<br>
   3.寫好controller service mapper <br>
   4.檢查錯誤 (departmentId在DB中是駝峰命名 ，springBoot要開啟才吃的到 不然null)<br>
   (mybatis.configuration.map-underscore-to-camel-case=true)
   5.log  properties: logging.level.(path) 一般都是DAO層


### cache
1. 開啟cache annotation  @EnableCaching
2. 在要cache的fun標註上去 @Cacheable @CacheEvict @ CachePut
3 .cacheable 參數 cacheNames/value :指定要放cache名字 <br>
	``cacheNames={"名字"}``
4. key: <K,V>型式 spEL語法 #id #a0 #a1 #root.args[0] <br>
	getEmp[index] <br>
	``key="#root.methodName+'['+ id +']'" ``
5. keyGenerator(可自訂義 自行添加@Configuration/@Bean): #key生成器，可以自生成    (key/keyGenerator 二選一用，  會撞)
6. cacheManager / cacheResolver 
7. condition:where condition =>spEL <br>
	``condition="#id>0" `` <br>
	``condition="#a0>0"`` 第一個param>0 <br>
	``condition="#a0>1 and #root.methodNames eq 'aaa' " ``多個條件
8. unless : nagtive condition 否定cache 當unless condition =true 則不cache &nbsp;&nbsp; ex: #result==null <br>
 用法與condition相同  依樣是spEL 但滿足條件不cache
9. sync: 非同步

### @CachePut 有點類似 putMapping效果 ，修改 更新 儲存
1. 跟cacheable不同 ， 不管如何都先調用function然後儲存/覆蓋
2. *** 用cachePut若無更新剛剛cache的data &nbsp;--->&nbsp; 檢查cacheNames的key需跟cachePut相同 ，不然會新增兩筆cache <br>
	key="#result.id"
	key="#emp.id"
3. !!! @Cacheable的key 不能用 #result (順序問題)  @CachePut可以

### @CacheEvict
1. 系統做delete data 時，那筆data不會再用到了 ，所以會同步 清掉cache中那筆data的緩存
2. @CacheEvict(cacheNames= "emp" , key="#id") &nbsp;&nbsp;&nbsp;注意!!! 要刪對key 若用自訂義autoGeneratorKey 記得刪對
3. allEntries=true  &nbsp;&nbsp; <--刪掉全部cache  &nbsp;&nbsp; default是false
4. beforeInvocation=true &nbsp;&nbsp;<--在function之前執行 &nbsp;&nbsp; default是false <br>
	若function 噴exception 則beforeInvocation=false &nbsp;不會執行 反之則執行

### @Caching
1. interface 內容物:<br>
	Cacheable[] &nbsp; cacheable() &nbsp; default {}; <br>
	CachePut[] &nbsp; put() &nbsp; default {}; <br>
	CacheEvict[]&nbsp; evict() &nbsp; default {}; <br>
	<br>用法:
	
	```java
	
		@Caching(
				cacheable= {
						@Cacheable(value="emp",key="#lastName")
				},
				put= {
						@CachePut(value="emp",key="#result.id"),
						@CachePut(value="emp",key="#result.email")
				}
			)
			
			在記錄@Cacheable(value="emp",key="#lastName")的同時也記錄這一筆的 id 跟 email
	
	```
	
```
中間有噴exception
Null key returned for cache operation (maybe you are using named params on classes without debug info?)
cache key 沒有lastName
cacheable= {
			@Cacheable(value="emp",key="#lastName")
		}
		
因為我的入參是 name
key="#lastName"  --> key="#name"  // 解決
```
	

### @CacheConfig
```
@CacheConfig(cacheNames="emp")
在cache setting頁 的class 加上 @CacheConfig(cacheNames="XXX")
這樣這一頁中的 cacheName="XXX" 、 value="XXX"  都不用寫了 
```
### cache 原理
1. CacheAutoConfiguration.class 內
2. org.springframework.boot.autofiguration.cache.GenericCacheconfiguration<br>
	.<br>
	.<br>
	.<br>
	.<br>
	11個 <br>
	看config 是否有用到 --->properties : debug=true  <br>
	靠cacheManager 、 concurrentMapCacheManager 管理cache components
3. @Cacheable執行funtion前會先在cache中找到是否有對應的key ，若無則按照function的參數作為key去查詢並創建 &nbsp;&nbsp; -->key=SimpleKey(params)


### 自訂義cacheConfig
@Configuration  @Bean

```java

public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				// TODO Auto-generated method stub
			}
		};
	}

```

### cache 的東西在哪裡?
1. 東西都在concurrentHashMap 內
2. 開發時 東西會存在 redis、memcached、ehcache 中


### redis實作原理
1. 引入redis的starter，container中保存的是 RedisCacheManager
2. RedisCacheManager幫我們創建RedisCache作為緩存，RedisCache操作redis緩存data
3. default 的存儲方式(K,V)都是Object，利用序列化儲存
4. 更改為json儲存

```
1. 引入redis 的 starter，cacheManager變成RedisCacheManager
2. default的 RedisCacheManager，操作redis的時候是RedisTemplate<Object,Object> 要改成我們自己要用的 RedisTemplate<Object,Emp>

```

### redis使用 local(redis-server c:\Redis\redis.windows.conf)(redis-cli)(auth 123456)
1. docker images
2. docker pull redis回來(偶數開頭 穩定版 奇數開頭非穩定版)
3. docker run -d -p 6379:6379 --name myredis image名
4. docker ps
5. import redis starter

```
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-redis -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

```
6. 配置redis properties<br>
spring.redis.host=redis在的ip address <br>
spring.redis.password=XXXXXX <br>
一樣有用autoConfig 就會有 XXXAutoConfiguration  <br>
主要就 StringRedisTemplate  (主要都字串 <K,V>)、RedisTemplate (都可以 <K,V>) <br>
``String、List、Set、Hash(散列)、ZSet(有序集合)``
``stringRedisTemplate.opsForValue().XXX操作 --> String``
``stringRedisTemplate.opsForXXX().XXX操作``

```
補充 Redis properties


# REDIS (RedisProperties)
# Redis資料庫索引（預設為0）
spring.redis.database=0  
# Redis伺服器地址
spring.redis.host=127.0.0.1
# Redis伺服器連線埠
spring.redis.port=6379  
# Redis伺服器連線密碼（預設為空）
spring.redis.password=  
# 連線池最大連線數（使用負值表示沒有限制）
spring.redis.jedis.pool.max-active=8  
# 連線池最大等待時間（使用負值表示沒有限制）
spring.redis.jedis.pool.max-wait=-1
# 連線池中的最大空閒連線
spring.redis.jedis.pool.max-idle=8  
# 連線池中的最小空閒連線
spring.redis.jedis.pool.min-idle=0  
# 連線超時時間（毫秒）
spring.redis.timeout=0

----------------------------yml這邊是網路上參考的------------------
spring:
  redis:
    host: 127.0.0.1
    port: 6379
    database: 0
    timeout: 60s  # 数据库连接超时时间，2.0 中该参数的类型为Duration，这里在配置的时候需要指明单位
    # 连接池配置，2.0中直接使用jedis或者lettuce配置连接池
    jedis:
      pool:
        # 最大空闲连接数
        max-idle: 500
        # 最小空闲连接数
        min-idle: 50
        # 等待可用连接的最大时间，负数为不限制
        max-wait:  -1s
        # 最大活跃连接数，负数为不限制
        max-active: -1

```

```
額外補充JPA properties

#JPA Configuration:

spring.jpa.hibernate.ddl-auto=update

spring.jpa.database=mysql

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.globally_quoted_identifiers=true
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect
```


### 在Test下測試

```java

	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	public void testRedis() {
		stringRedisTemplate.opsForValue().append("msg","hello" ); //設定
		System.out.println(stringRedisTemplate.opsForValue().get("msg")); // 拿東西
	}

```

### redis 存入emp data
# 若想把emp存進redis POJO 需序列化<br>
``implements Serializable `` 則存入的資料是序列化後的成果

```
//存入序列化後的data  (有點像亂碼)

@Test
	public void testEmpRedis() {
		Employee empId=employeeMapper.getEmpById(1);
		redisTemplate.opsForValue().set("emp-01", empId);
		
	}
```



# 系統啟動後 把emp data 存入 <br>

自訂義， 在redisAutoConfiguration內可找到

```java

//原始狀態

@Configuration
public class MyRedisConfig {

	
	@Bean
	public RedisTemplate<Object, Employee> residTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException{
		RedisTemplate<Object, Employee> template=new RedisTemplate<Object, Employee>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
```


```java
//自訂義序列emp

@Configuration
public class MyRedisConfig {

	
	@Bean
	public RedisTemplate<Object, Employee> empResidTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException{
		RedisTemplate<Object, Employee> template=new RedisTemplate<Object, Employee>();
		template.setConnectionFactory(redisConnectionFactory);
		//emp序列化後 傳入 defaultSerializer
		Jackson2JsonRedisSerializer<Employee> jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer<Employee>(Employee.class);
		template.setDefaultSerializer(jackson2JsonRedisSerializer);
		return template;
	}
}

```


# 測試

```java
	@Autowired
	RedisTemplate<Object,Employee> empResidTemplate;
	


	@Test
	public void testEmpRedis2() {
		Employee empId=employeeMapper.getEmpById(1);
		empResidTemplate.opsForValue().set("emp-01", empId);
	}

```
# 成功存入!!!!!
```json
{
  "id": 1,
  "lastName": "Bill",
  "email": "123@gmail.com",
  "gender": 0,
  "dId": 1003
}
```

## 執行boot cache會存入redis
1. 原本是存在boot中的concurrentCache但接上redis後 會有地方存，就不存在concurrentCache
2. cacheManager 變成 redisCacheManager



# 保存成json 
1. 引入 redis starter ，使cacheManager變成RedisCacheManager
2. default 的RedisCacheManager 操作redis是使用 RedisTemplate<Object,Object>
3. 不用default的 object類型  自訂義CacheManager 
4. 可照著 RedisCacheConfiguration.java 更改


```java 

這個寫法會噴錯 springBoot(2.0 以上，1.多不會錯)
@Bean
	public RedisCacheManager empCacheManager(RedisTemplate<Object, Employee> empResidTemplate) {
		RedisCacheManager cacheManager=new RedisCacheManager(empResidTemplate);
		cacheManager.setUsePrefix(true);
		return cacheManager;
	}


```



```
1.X版緩存時間

@Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置缓存过期时间
        cacheManager.setDefaultExpiration(10000);
        return cacheManager;
    }



補充2.X之後的 緩存時間   

@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
	    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
	    config = config.entryTtl(Duration.ofMinutes(1))     // 设置缓存的默认过期时间，也是使用Duration设置
	            .disableCachingNullValues();     // 不缓存空值

	    // 设置一个初始化的缓存空间set集合
	    Set<String> cacheNames =  new HashSet<>();
	    cacheNames.add("my-redis-cache1");
	    cacheNames.add("my-redis-cache2");

	    // 对每个缓存空间应用不同的配置
	    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
	    configMap.put("my-redis-cache1", config);
	    configMap.put("my-redis-cache2", config.entryTtl(Duration.ofSeconds(120)));

	    RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // 使用自定义的缓存配置初始化一个cacheManager
	            .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
	            .withInitialCacheConfigurations(configMap)
	            .build();
	    return cacheManager;
	}

```

# 解決2.0存入json方法
參考網友: <a href="https://blog.csdn.net/qq_41534573/article/details/104895896">講解過程請參考</a>
#### 大概就是<br>
default 是使用 JdkSerializationRedisSerializer，要換成 Jackson2JsonRedisSerializer才能作為json的緩存

```java

@Bean
	public RedisCacheConfiguration empRedisCacheConfiguration() {
		RedisSerializer<Employee> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
		SerializationPair<Employee> serializationPair = RedisSerializationContext.SerializationPair
																				 .fromSerializer(jackson2JsonRedisSerializer);

		return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationPair);
	}
	@Bean
	public RedisCacheManager empCacheManager(RedisConnectionFactory redisConnectionFactory) {
		RedisCacheManagerBuilder builder = RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
				.cacheDefaults(empRedisCacheConfiguration());
		RedisCacheManager cm = builder.build();
		return cm;
	}

```


# another way

```java
@Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
 
        //初始化json的序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer);
        //设置 value 的序列化方式为 jackson2JsonRedisSerializer
        RedisCacheConfiguration defaultCacheConfig=RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
 
        //设置默认超过期时间是100秒
        defaultCacheConfig = defaultCacheConfig.entryTtl(Duration.ofSeconds(100));
 
        //初始化RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig);
 
        //设置白名单---非常重要********
        /*
        使用fastjson的时候：序列化时将class信息写入，反解析的时候，
        fastjson默认情况下会开启autoType的检查，相当于一个白名单检查，
        如果序列化信息中的类路径不在autoType中，
        反解析就会报com.alibaba.fastjson.JSONException: autoType is not support的异常
        可参考 https://blog.csdn.net/u012240455/article/details/80538540
         */
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
 
        return cacheManager;
    }


```

### 下一個問題，當製作第二個POJO  (Dept)時，設置好controller、service，mapper，順利存入Redis，但查詢第二次從Cahce中取出data時，會噴錯，會反序列化Emp的東西






   
