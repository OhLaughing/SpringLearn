# SpringLearn
##   spring in action的学习代码, maven工程
-   昨天弄个简单的springmvc程序（_07_SpringInAction_Chapter5_SpringWeb）失败了，提示信息有：Failed to start component [StandardEngine[Tomcat]， 上网找可能是servlet-api版本问题导致，把依赖中的servlet-api删除就可以了， 发现个问题，springmvc的依赖中并没有依赖servlet-api(包含HttpServlet)，而我们自己实现简单的servlet程序时，要有HttpServlet的，这是为什么？, 今天又发现即使该工程可以生成war包在tomcat下执行，但是工程不能编译通过，提示找不到ServletException，很奇怪。找到原因了，该工程要引入servlet-api的包，但是如果加<scope>provided</scope> 就既能编译通过，也能在tomcat下执行，如果不加scope，tomcat就不能执行
-   _12_SpringInAction_Chapter7: springMvc的JavaConfig基础配置
-   _13_SpringInAction_Chapter7_MultipartFile: 完成基本的MultipartFile功能， 但是html加上下面的，就失败
```
<div id="header" th:include="page :: header"></div>
```
-   _14_SpringSecurity_Xml_Basic: spring security的基本xml配置，完成基本的用户登录， 
-   _15_SpringSecurity_JavaConfig, login登录失败，提示
```
type Status report
message /login
description The requested resource is not available.
```

### spring security登录的步骤, 基于web.xml的配置方式， 流程如下：
1. 创建maven工程， package：war, 增加配置spring-security-web、spring-security-config、thymeleaf-spring4、spring-webmvc
2. webapp/WEB-INF/web.xml中配置filter
````
<filter>
<filter-name>springSecurityFilterChain</filter-name>
<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>

<filter-mapping>
<filter-name>springSecurityFilterChain</filter-name>
<url-pattern>/*</url-pattern>
</filter-mapping>
````
3. web.xml中增加spring-security.xml配置
```
<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml, /WEB-INF/spring-security.xml</param-value>
</context-param>
```
4. 配置spring-security.xml
5. 配置Controller

### 基于javaconfig 的Spring security的工程配置流程：
1. 依赖：spirng-security-web、spirng-security-config、thymeleaf-sping4\spring-webmvc\javax.servlet-api(provided), 增加tomcat7插件，packing:war
2. 首先完成基于javaconfig的springmvc的相关配置：继承类AbstractAnnotationConfigDispatcherServletInitializer，并实现三个方法，WebConfig\RootConig的配置
3. 继承类：AbstractSecurityWebApplicationInitializer，只要继承即可，什么也不用实现
4. 最重要的：继承类 WebSecurityConfigurerAdapter，并实现三个configure()方法
5. 配置Controller
6. 配置页面（html）



### Springmvc + Thymeleaf web.xml工程搭建步骤(_11_Springmvc_Thymeleaf_Xml)：
1. 创建工程，maven依赖spring-webmvc、javax.servlet-api(provided)、thymeleaf-spring4, tomcat插件、packaging:war
2. webapp/WEB-INF/web.xml 配置包括：
（1）ContextLoaderListener
```
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```
（2）配置applicationContext
```
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
```
(3) 配置DispatcherServlet以及servlet-mapping， 如果指定servlet.xml像下面一样配置，如果不指定，默认是/WEB-INF/servlet-name-servlet.xml文件
```
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
3.  *-servlet.xml文件中 配置viewResolver
4 配置 Controller
5 webapp/WEB-INF/views/ 文件夹下html文件，与Controller相对应

### SpringMvc Thymeleaf 的java配置步骤：（详见工程：_16_SpringMvc_JavaConfig）
1. 继承AbstractAnnotationConfigDispatcherServletInitializer，并实现三个抽象方法
2. 配置WebConfig，继承WebMvcConfigurerAdapter, 配置ViewResolver的bean， @Configuration、@ComponentScan、@EnableWebMvc 注解
3. 配置RootConfig,具体分析之后再说
4. 将WebConfig和RootConfig配置类，在第一步的类中引用
5. 配置Controller
6. 配置html文件，与Controller的view相对应


### 技术积累
- ContextLoaderListener 是一种引入方式，默认读取/WEB-INF/applicationContext.xml, 若是spring-web项目，DispatcherServlet 也是一种引入方式，默认读取/WEB-INF/${servlet-name}-servlet.xml

### 第七章学习计划
1. springmvc + Thymeleaf 通过java配置的方式基本的功能
- 由于thymeleaf-spring4的版本用的不对，导致编译都不过，现阶段SpringInAction里用的thymeleaf-spring4都用用版本2
2. 完成基本的MultiPartFile功能，然后主要学Thymeleaf的知识，以及后端的交互
- profile.html： div和span的使用，两者都是独立的块元素，里面都可以使用html元素，主要的区别是div块换行，而span不换行,
<span th:text="${spitter.username}">username</span>,使用了thymeleaf，${spitter.username}是获取Model中spitter对象的username属性的方式，html通过静态的方式打开，就显示username，动态情况打开就获取对象,如果单纯只是为了测试通过thymeleaf的标签获取model的对象信息，可以通过增加下面的Controller:
```java
    @RequestMapping(value = "xiaoming")
    public String showInfo(Model model) {
        Spitter xiaoming = new Spitter();
        xiaoming.setFirstName("xiaoming");
        xiaoming.setEmail("xiaoming@126.com");
        xiaoming.setId(1234L);
        xiaoming.setUsername("LiXiaoming");
        model.addAttribute("spitter", xiaoming);
        return "profile";
    }
```
通过localhost:9090/spitter/xiaoming，就可以获取对象信息，其中model.addAttribute("spitter", xiaoming);可以写成model.addAttribute( xiaoming);
因为如果不指定model的key值，会把对象的类当做key值（类首字母小写），
测试过程中发现，通过mvn package的方式不能成功的打war包，提示找不到web.xml，后来通过增加maven-war-plugin来解决
```
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>2.4</version>
            <configuration>
                <warSourceDirectory>src/main/webapp</warSourceDirectory>
                <warName>SpitterExample</warName>
                <failOnMissingWebXml>false</failOnMissingWebXml>
            </configuration>
        </plugin>
```               
打完war包之后，拷贝到tomcat的webapps目录下，执行bin/startup.bat，可以通过localhost:8080/SpitterExample/hello来访问
-   从url输入网址到post提交文件的流程：
-（1）页面输入localhost:9090/spitter/register
定位到mapping到spitter/register的方法
```java
    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        Spitter s = new Spitter();
        model.addAttribute(s);
        return "registerForm";
    }
```
   也就是定位到showRegistrationForm方法，该方法只是把一个new的Spitter对象加入到Model中，然后registerForm页面，也就是registerForm.html
   静态打开registerForm.html文件时，可以看到有th:object等的一些Thymeleaf的标签，但是通过tomcat运行起来之后，返回给客户端的页面是不包含这些标签的，这些标签是被转换成html的格式的
-   （2）通过上一步返回一个html页面，在这个页面输入信息，然后点击按钮,提交数据，首先研究研究@RequestPart注解:
通过方法上的注解： @RequestPart(value = "profilePicture", required = false) Part fileBytes,  可以把客户端的multipart请求带到服务端，value = "profilePicture"的值就是html中的name对应的信息
```    
    <input type="file"
     name="profilePicture"
     accept="image/jpeg,image/png,image/gif" /><br/>
```
通过Part接口的方式的代码：
```java
    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @RequestPart(value = "profilePicture", required = false) Part fileBytes,
            RedirectAttributes redirectAttributes,
            @Valid Spitter spitter,
            Errors errors) throws IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        if(fileBytes != null) {
            System.out.println("file name " + fileBytes.getHeader("Content-Disposition"));
            System.out.println("file size; " + fileBytes.getSize());
            InputStream is = fileBytes.getInputStream();
            File file = new File("multipartFile.png");
            OutputStream os = new FileOutputStream(file);
            byte[] bytes = new byte[4096];
            try {
                while (is.read(bytes) != -1) {
                    os.write(bytes);
                }
            } finally {
                is.close();
                os.close();
            }
        }else{
            System.out.println("fileBytes is null");
        }
        spitterRepository.save(spitter);
        redirectAttributes.addAttribute("username", spitter.getUsername());
        redirectAttributes.addFlashAttribute(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }
```
通过MultiPartFile的方式来实现的代码：
```java
 @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @RequestPart(value = "profilePicture", required = false) MultipartFile fileBytes,
            RedirectAttributes redirectAttributes,
            @Valid Spitter spitter,
            Errors errors) throws IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        if (fileBytes != null) {
            System.out.println("file size: " + fileBytes.getSize());
            System.out.println("file name: " + fileBytes.getOriginalFilename());

            fileBytes.transferTo(new File(fileBytes.getOriginalFilename()));

        } else {
            System.out.println("fileBytes is null");
        }
        spitterRepository.save(spitter);
        redirectAttributes.addAttribute("username", spitter.getUsername());
        redirectAttributes.addFlashAttribute(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }
```
两种方式区别是：MultipartFile可以直接用transferTo的方式保存文件，而Part没有，MultipartFile可以通过getOriginalFilename()获取文件名称，Part是通过getSubmittedFileName()来获取文件名称，Part接口是在javax.servlet-api包中，如果版本不一致会导致编译时能通过，但是运行时是依赖tomcat的相应的servlet包，导致运行时失败，我本地测试时就没成功，提示空指针，而MultipartFile是springWeb的jar包

### SpringInAction 第九章
-   现在已经完成了通过xml配置的Spring security的基本功能，但是昨天一天都没有实现自己实现登录页面，网上很多例子都是jsp的，看来有时间还是要把jsp看下
，登录页面暂时先跳过有时间再弄下，接下来第九章的要完成的任务有：通过java配置的方式实现spring Security, 还有就是熟练三个configure()方法的配置
-   第九章思路整理：
spring-security.xml中通过：
```
    <security:http auto-config="true">
        <security:intercept-url pattern="/**" access="hasRole('ROLE_USER')"/>
    </security:http>
```
通过上面配置，就可以实现登录，但是登录的页面是spring默认提供的登录页面，如果想要自己实现登录页面， 可以在spring-security.xml中配置：
```
    <security:http auto-config="true">
        <security:form-login login-page="/loginPage"                      //1
                             login-processing-url="/login"                //2
                             default-target-url="/welcome"                //3
                             authentication-failure-url="/loginPage?error=error"    //4
                            always-use-default-target="true"/>                      //5
        <!-- 表示匿名用户可以访问 -->
        <security:intercept-url pattern="/loginPage" access="hasRole('ROLE_ANONYMOUS')"/>   //6      
        <security:intercept-url pattern="/**" access="hasRole('ROLE_USER')" />              //7
        <!--<security:csrf disabled="true" />-->
    </security:http>
```
此时登录发现，提示Could not verify the provided CSRF token because your session was not found. 
主要原因是；spring有csrf保护，可以通过<security:csrf disabled="true" />来禁用csrf防护，但是一般情况不要这么做,因为这样的系统有风险
上面的配置分析：
1. login-page 配置的是url路径，登录的默认路径，如果没有登录情况，其他路径都会转到该路径下，但是，该路径要允许匿名访问，通过6来设置，
因此1和6的配置必须是相同的，而且要给该值设置controller的返回页面
2. login-processing-url配置的是服务端接受登录的post请求，该值和th:action='@{/login}' method='POST'> 中的login一致
3. default-target-url 是登录成功之后转向的路径，默认为"/"，要给该路径配置mapping页面
4. authentication-failure-url 是登录页面失败是转向的url，可以通过例子来理解该配置的功能
url输入：localhost:9090/notexist，这个的请求失败，因为还没有登录
Request URL: http://localhost:9090/notexist
Request Method: GET
Status Code: 302 Found

因此会转向默认的登录页面：
Request URL: http://localhost:9090/loginPage
Request Method: GET
Status Code: 200 OK

然后在该登录页面输入用户名和密码，如果输入错误，第一个POST请求失败：

Request URL: http://localhost:9090/login
Request Method: POST
Status Code: 302 Found

然后会转向authentication-failure-url定义的错误路径:
Request URL: http://localhost:9090/loginPage?error=error
Request Method: GET
Status Code: 200 OK

5. always-use-default-target如果设置为true，登录成功就转向default-target-url的页面
6. 该功能是把登录页面设置为允许匿名访问，否则登录页面都拦截，还登录个屁啊
7. 让所有的页面访问都要有USER权限才能访问


还有一种办法是把原来的<form name='f' action='login' method='POST'>改成 thymeleaf的命名空间前缀：<form name='f' th:action='@{/login}' method='POST'>，这样就可以实现登录，原因是通过增加thymeleaf前缀后，提交表单就会自动增加一个隐藏域：_csrf，查看form data是这样的：
```
username:admin
password:admin
submit:Login
_csrf:875b17e4-b3cc-4b04-84ac-6a664d6d09e0
```
    而如果不用thymeleaf前缀，就没有_csrf域
-   通过spring security的方式实现登录，不需要对login实现post请求的处理，spring会自动处理    
### Spring Security 
-   spring security 教程： https://www.yiibai.com/spring-security,  https://www.w3cschool.cn/springsecurity/na1k1ihx.html
-   手动设置登录页面时， 用下面的方式：
<security:intercept-url pattern="/login" access="hasRole('ROLE_ANONYMOUS')"/>
或
<security:http pattern="/login" security="none"></security:http> 可以达到给登录的页面放行的目的，但是我测试中用IS_AUTHENTICATED_ANONYMOUSLY还没有成功
- https://moon-walker.iteye.com/blog/2395270
- https://www.cnblogs.com/hongxf1990/p/6530084.html
- https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#design-of-the-namespace


### 小目标
-       后端SpringBoot开发，前端Vue开发，不用前后端分离的方式，把前端Vue工程npm run build打包，然后放到SpringBoot的resources目录下，先完成最基本的页面访问功能
