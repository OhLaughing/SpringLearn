# SpringLearn

### SpringBoot 的知识点记录
- spring-boot-starter-actuator:actuator是监控系统健康情况的工具。在依赖中加上即可使用
- SpringBoot中如果加入spring-boot-starter-jdbc依赖，就必须配置dataSource数据源，否则启动失败，如果确定不自动配置数据源，可以@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})来解决
- springboot 启动自动加载sql，默认是spring.datasource.schema: classpath:schema.sql，不需要配置，如果sql名称不是schema.sql,要在application.properties中指定：spring.datasource.schema: classpath:schema12.sql
- 在SpringMvc开发web应用时，要显示的创建DataSource和JdbcTemplate的bean（如下），而用SpringBoot开发时，是自动组装
```
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql")
            .build();
  }
  
  @Bean
  public JdbcOperations jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }
```  
-   SpringBoot弄清楚自动配置，首先研究下DataSourceAutoConfiguration，  我们知道EnableAutoConfiguration注解最重要的的一个地方是：
```
@Import(EnableAutoConfigurationImportSelector.class)
```
EnableAutoConfigurationImportSelector类的selectImports方法里是加载自动配置类的信息，该方法里的主要几行代码是：
```
        List<String> configurations = getCandidateConfigurations(metadata,
                        attributes);
        configurations = removeDuplicates(configurations);
        Set<String> exclusions = getExclusions(metadata, attributes);
        configurations.removeAll(exclusions);
```
getCandidateConfigurations方法里主要是SpringFactoriesLoader.loadFactoryNames（Class<?> factoryClass, ClassLoader classLoader）方法，此方法的功能是从所有依赖的jar包中查找"META-INF/spring.factories"文件，并找出key值为factoryClass的所有value值，例如传入方法的factoryClass为EnableAutoConfiguration.class，因此会找key值为EnableAutoConfiguration的value值，spring-boot-autoconfigure.jar中的spring.factories文件中就有key值为org.springframework.boot.autoconfigure.EnableAutoConfiguration的信息，
-   关于Springboot自动配置的文章：https://www.cnblogs.com/xinzhao/p/5551828.html
exclusions就是@SpringBootApplication的excludes的属性，如果@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
那么exclusions的list里就有一个元素：DataSourceAutoConfiguration
-   在Spring3.1版本之前是通过PropertyPlaceholderConfigurer实现的。 
    而3.1之后则是通过PropertySourcesPlaceholderConfigurer 实现的。
-   spring的官方文档要好好看：spring-framework-reference.pdf
-   @Configuration注解本身就是组合了@Component
-   application.propertie文件中增加debug=true,会打印详细日志，方便开发定位
-   SpringMvc+Thymeleaf开发时，要手动完成ViewResolver的bean声明（包括它依赖的bean），但是在springBoot开发时并没有手动声明bean，
-   WebMvcAutoConfiguration 类要熟练掌握
-   spring-boot-starter-xxx本质是什么？可以打开spring-boot-starter-web的jar包看下，里面没有代码，只有配置文件，主要是pom.xml文件里面的一些
依赖的jar包

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
- SpringBoot+Vue整合打包的栗子：https://segmentfault.com/a/1190000014211773

### 小目标
- 后端SpringBoot开发，前端Vue开发，不用前后端分离的方式，把前端Vue工程npm run build打包，然后放到SpringBoot的resources目录下，先完成最基本的页面访问功能



### _00_SpringBootDemos的栗子
-   _01_SpringBoot_FirstDemo: springBoot的最基本的栗子，别看基础，要把每个知识点都搞懂！
浏览器访问：localhost:8080/demo/hello，返回_01_SpringBoot_FirstDemo
-   _02_PropertySource_demo: 利用@PropertySource获取properties文件信息，加载时会把propertyes文件的信息读入Spring的Environment中，
该demo演示的基本的使用，通过Environment获取properties属性时，建议使用getRequiredProperty而不是getProperty，因为前者确保能有值，
本demo还遗留问题：1. 通过localhost:8080/user1访问返回User对象时失败。2. @PropertySource配合propertysourcesplaceholderconfigurer使用
-   _03_Import_Demo： @Import和@ImportSource的栗子，@Import是javaconfig引用其他javaconfig，@ImportSource
是JavaConfig引用xml的配置。
-   _04_SpringBoot_AutoConfig_Demo: 根据 https://www.jianshu.com/p/464d04c36fb1 实现的demo，把父模块的pom.xml改成<packaging>pom</packaging>
增加Hello模块，依赖spring-context，通过HelloConfig定义一个Hello的bean,注意：HelloConfig的路径为com.example1.HelloConfig
增加World模块，依赖Hello模块，启动Spring容器里获取Hello的bean，如果Hello模块的HelloConfig与World模块的App(配置类)的路径相同(都为com.example),
则，Spring容器中能加载Hello的bean，如果不相同，就没有Hello的bean，启动Srping就会提示：	 No qualifying bean of type 'com.example1.Hello' available
如果路径不相同，想要在World模块的Spring容器有能加载到Hello模块的的bean，解决该问题就是在HEllo模块的\main\resources\META-INF\spring.factories文件加入
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example1.HelloConfig，再次运行就正常了，说明在World模块中加载了
Hello模块的bean，在AutoConfigurationImportSelector的selectImports方法中在
```java
List<String> configurations = getCandidateConfigurations(annotationMetadata,attributes);
```
处打断点，就能看到 configurations的size = 110（不一定），里面就有com.example1.HelloConfig，因此

### _00_Spring的栗子
-   _01_ApplicationContext_Demo:一个简单的Spring栗子，只依赖了spring-context，通过mvn dependency:tree,可以发现spring-context依赖了
```
[INFO] \- org.springframework:spring-context:jar:4.3.13.RELEASE:compile
[INFO]    +- org.springframework:spring-aop:jar:4.3.13.RELEASE:compile
    [INFO]    +- org.springframework:spring-beans:jar:4.3.13.RELEASE:compile
    [INFO]    +- org.springframework:spring-core:jar:4.3.13.RELEASE:compile
    [INFO]    |  \- commons-logging:commons-logging:jar:1.2:compile
    [INFO]    \- org.springframework:spring-expression:jar:4.3.13.RELEASE:compile
    
```
-   _02_Spring_Aop： Spring aop的栗子，有时间熟悉下
   
