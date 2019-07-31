## 本日记用于记录所有springmvc的知识点（4.3.9版本）(offlinecheck)
### Spring mvc 学习记录
-   如果想要显示logger.debug的信息，可以通过log4j的配置log4j.rootLogger = debug
-   学习SpringMvc主要是弄清楚DispatcherServlet，要学习DispatcherServlet，就从initStrategies(ApplicationContext context) 开始
-   从DispatcherServlet的initHandlerMappings方法学习HanderMapping的初始化，通过debug跟到DefaultListableBeanFactory的doGetBeanNamesForType方法，这个方法很重要，好好学习下
-   Springmvc 在哪里初始化url和controller方法的对应关系的？
-   弄清楚HandlerExecutionChain与HanderMapping关系
-   DispatcherServlet实现了Servlet接口，整个请求处理流：HttpServlet.service -> FrameworkServlet.doGet -> FrameworkServlet.processRequest -> DispatcherServlet.doService -> DispatcherServlet.doDispatch
-   DispatcherServlet的doDispatch是主要的分发过程
-   HandlerMapping和HandlerExecutionChain是什么关系,以及与HandlerAdapter什么关系
### 好文章
- [debug方式深入springMVC源码的实现](https://www.jianshu.com/p/fd19e464697d)

### 杂技术
- postman工具的form-data和raw什么区别？
