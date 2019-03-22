# SpringLearn
##   spring in action的学习代码, maven工程
-  昨天弄个简单的springmvc程序（_07_SpringInAction_Chapter5_SpringWeb）失败了，提示信息有：Failed to start component [StandardEngine[Tomcat]， 上网找可能是servlet-api版本问题导致，把依赖中的servlet-api删除就可以了， 发现个问题，springmvc的依赖中并没有依赖servlet-api(包含HttpServlet)，而我们自己实现简单的servlet程序时，要有HttpServlet的，这是为什么？, 今天又发现即使该工程可以生成war包在tomcat下执行，但是工程不能编译通过，提示找不到ServletException，很奇怪。找到原因了，该工程要引入servlet-api的包，但是如果加<scope>provided</scope> 就既能编译通过，也能在tomcat下执行，如果不加scope，tomcat就不能执行

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
2. 
