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
3 .cacheable 參數 cacheNames/value :指定要放cache名字
4. key: <K,V>型式 spEL語法 #id #a0 #a1 #root.args[0] 
5. keyGenerator: #key生成器，可以自生成    (key/keyGenerator 二選一用，  會撞)
6. cacheManager / cacheResolver 
7. condition:where condition =>spEL
8. unless : nagtive condition 否定cache 當unless condition =true 則不cache  ex: #result==null
9. sync: 非同步
   
