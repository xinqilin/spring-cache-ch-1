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






   
