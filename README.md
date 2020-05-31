<!-- <link href="http://thomasf.github.io/solarized-css/solarized-light.min.css" rel="stylesheet"></link> -->
<style>
article,
aside,
details,
figcaption,
figure,
footer,
header,
hgroup,
nav,
section,
summary {
  display: block;
}
audio,
canvas,
video {
  display: inline-block;
}
audio:not([controls]) {
  display: none;
  height: 0;
}
[hidden] {
  display: none;
}
html {
  font-family: sans-serif;
  -webkit-text-size-adjust: 100%;
  -ms-text-size-adjust: 100%;
}
body {
  margin: 0;
}
a:focus {
  outline: thin dotted;
}
a:active,
a:hover {
  outline: 0;
}
h1 {
  font-size: 2em;
}
abbr[title] {
  border-bottom: 1px dotted;
}
b,
strong {
  font-weight: bold;
}
dfn {
  font-style: italic;
}
mark {
  background: #ff0;
  color: #000;
}
code,
kbd,
pre,
samp {
  font-family: monospace, serif;
  font-size: 1em;
}
pre {
  white-space: pre-wrap;
  word-wrap: break-word;
}
q {
  quotes: "\201C" "\201D" "\2018" "\2019";
}
small {
  font-size: 80%;
}
sub,
sup {
  font-size: 75%;
  line-height: 0;
  position: relative;
  vertical-align: baseline;
}
sup {
  top: -0.5em;
}
sub {
  bottom: -0.25em;
}
img {
  border: 0;
}
svg:not(:root) {
  overflow: hidden;
}
figure {
  margin: 0;
}
fieldset {
  border: 1px solid #c0c0c0;
  margin: 0 2px;
  padding: 0.35em 0.625em 0.75em;
}
legend {
  border: 0;
  padding: 0;
}
button,
input,
select,
textarea {
  font-family: inherit;
  font-size: 100%;
  margin: 0;
}
button,
input {
  line-height: normal;
}
button,
html input[type="button"],
input[type="reset"],
input[type="submit"] {
  -webkit-appearance: button;
  cursor: pointer;
}
button[disabled],
input[disabled] {
  cursor: default;
}
input[type="checkbox"],
input[type="radio"] {
  box-sizing: border-box;
  padding: 0;
}
input[type="search"] {
  -webkit-appearance: textfield;
  -moz-box-sizing: content-box;
  -webkit-box-sizing: content-box;
  box-sizing: content-box;
}
input[type="search"]::-webkit-search-cancel-button,
input[type="search"]::-webkit-search-decoration {
  -webkit-appearance: none;
}
button::-moz-focus-inner,
input::-moz-focus-inner {
  border: 0;
  padding: 0;
}
textarea {
  overflow: auto;
  vertical-align: top;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
html {
  font-family: 'PT Sans', sans-serif;
}
pre,
code {
  font-family: 'Inconsolata', sans-serif;
}
h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: 'PT Sans Narrow', sans-serif;
  font-weight: 700;
}
html {
  background-color: #eee8d5;
  color: #657b83;
  margin: 1em;
}
body {
  background-color: #fdf6e3;
  margin: 0 auto;
  max-width: 23cm;
  border: 1pt solid #93a1a1;
  padding: 1em;
}
code {
  background-color: #eee8d5;
  padding: 2px;
}
a {
  color: #b58900;
}
a:visited {
  color: #cb4b16;
}
a:hover {
  color: #cb4b16;
}
h1 {
  color: #d33682;
}
h2,
h3,
h4,
h5,
h6 {
  color: #859900;
}
pre {
  background-color: #fdf6e3;
  color: #657b83;
  border: 1pt solid #93a1a1;
  padding: 1em;
  box-shadow: 5pt 5pt 8pt #eee8d5;
}
pre code {
  background-color: #fdf6e3;
}
h1 {
  font-size: 2.8em;
}
h2 {
  font-size: 2.4em;
}
h3 {
  font-size: 1.8em;
}
h4 {
  font-size: 1.4em;
}
h5 {
  font-size: 1.3em;
}
h6 {
  font-size: 1.15em;
}
.tag {
  background-color: #eee8d5;
  color: #d33682;
  padding: 0 0.2em;
}
.todo,
.next,
.done {
  color: #fdf6e3;
  background-color: #dc322f;
  padding: 0 0.2em;
}
.tag {
  -webkit-border-radius: 0.35em;
  -moz-border-radius: 0.35em;
  border-radius: 0.35em;
}
.TODO {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #2aa198;
}
.NEXT {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #268bd2;
}
.ACTIVE {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #268bd2;
}
.DONE {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #859900;
}
.WAITING {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #cb4b16;
}
.HOLD {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #d33682;
}
.NOTE {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #d33682;
}
.CANCELLED {
  -webkit-border-radius: 0.2em;
  -moz-border-radius: 0.2em;
  border-radius: 0.2em;
  background-color: #859900;
}
</style>

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




   
