# SpringLearn
##   spring in action的学习代码, maven工程
-   昨天弄个简单的springmvc程序（_07_SpringInAction_Chapter5_SpringWeb）失败了，提示信息有：Failed to start component [StandardEngine[Tomcat]， 上网找可能是servlet-api版本问题导致，把依赖中的servlet-api删除就可以了， 发现个问题，springmvc的依赖中并没有依赖servlet-api(包含HttpServlet)，而我们自己实现简单的servlet程序时，要有HttpServlet的，这是为什么？, 今天又发现即使该工程可以生成war包在tomcat下执行，但是工程不能编译通过，提示找不到ServletException，很奇怪。找到原因了，该工程要引入servlet-api的包，但是如果加<scope>provided</scope> 就既能编译通过，也能在tomcat下执行，如果不加scope，tomcat就不能执行
-   _12_SpringInAction_Chapter7: springMvc的JavaConfig基础配置
-   _13_SpringInAction_Chapter7_MultipartFile: 完成基本的MultipartFile功能， 但是html加上下面的，就失败
```
<div id="header" th:include="page :: header"></div>
```

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
