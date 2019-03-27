# SpringLearn
##   spring in action的学习代码, maven工程
-   昨天弄个简单的springmvc程序（_07_SpringInAction_Chapter5_SpringWeb）失败了，提示信息有：Failed to start component [StandardEngine[Tomcat]， 上网找可能是servlet-api版本问题导致，把依赖中的servlet-api删除就可以了， 发现个问题，springmvc的依赖中并没有依赖servlet-api(包含HttpServlet)，而我们自己实现简单的servlet程序时，要有HttpServlet的，这是为什么？, 今天又发现即使该工程可以生成war包在tomcat下执行，但是工程不能编译通过，提示找不到ServletException，很奇怪。找到原因了，该工程要引入servlet-api的包，但是如果加<scope>provided</scope> 就既能编译通过，也能在tomcat下执行，如果不加scope，tomcat就不能执行
-   _12_SpringInAction_Chapter7: springMvc的JavaConfig基础配置
-   _13_SpringInAction_Chapter7_MultipartFile: 完成基本的MultipartFile功能， 但是html加上下面的，就失败
```
<div id="header" th:include="page :: header"></div>
```
-   _14_SpringSecurity_Xml_Basic: spring security的基本xml配置
### Springmvc + Thymeleaf web.xml工程搭建步骤：
- 创建工程，maven依赖spring-webmvc、javax.servlet-api(provided)、thymeleaf-spring4, tomcat插件、packaging:war
- webapp/WEB-INF/web.xml 配置DispatcherServlet、contextConfigLocation（*-servlet.xml）
- *-servlet.xml文件中 配置viewResolver
- Controller
- webapp/WEB-INF/views/ 文件夹下html文件

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

### Spring Security 
-   spring security 教程： https://www.yiibai.com/spring-security,  https://www.w3cschool.cn/springsecurity/na1k1ihx.html
-   手动设置登录页面时， 用下面的方式：
<security:intercept-url pattern="/login" access="hasRole('ROLE_ANONYMOUS')"/>
或
<security:http pattern="/login" security="none"></security:http> 可以达到给登录的页面放行的目的，但是我测试中用IS_AUTHENTICATED_ANONYMOUSLY还没有成功
- https://moon-walker.iteye.com/blog/2395270
- https://www.cnblogs.com/hongxf1990/p/6530084.html
- https://docs.spring.io/spring-security/site/docs/5.2.0.BUILD-SNAPSHOT/reference/htmlsingle/#design-of-the-namespace
