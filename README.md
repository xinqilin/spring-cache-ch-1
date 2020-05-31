<link href="http://thomasf.github.io/solarized-css/solarized-light.min.css" rel="stylesheet"></link>

# spring-cache-ch-1 練習

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




   
