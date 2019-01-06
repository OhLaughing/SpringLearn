## _01_Spring_FirstSight/

maven 的依赖关系：
```
[INFO] +- junit:junit:jar:4.11:test
[INFO] |  \- org.hamcrest:hamcrest-core:jar:1.3:test
[INFO] +- org.springframework:spring-context:jar:4.3.9.RELEASE:compile
[INFO] |  +- org.springframework:spring-aop:jar:4.3.9.RELEASE:compile
[INFO] |  +- org.springframework:spring-beans:jar:4.3.9.RELEASE:compile
[INFO] |  +- org.springframework:spring-core:jar:4.3.9.RELEASE:compile
[INFO] |  |  \- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  \- org.springframework:spring-expression:jar:4.3.9.RELEASE:compile
[INFO] +- org.aspectj:aspectjweaver:jar:1.8.13:compile
[INFO] +- log4j:log4j:jar:1.2.17:compile
[INFO] +- org.mockito:mockito-core:jar:2.15.0:test
[INFO] |  +- net.bytebuddy:byte-buddy:jar:1.7.9:test
[INFO] |  +- net.bytebuddy:byte-buddy-agent:jar:1.7.9:test
[INFO] |  \- org.objenesis:objenesis:jar:2.6:test
[INFO] \- org.springframework:spring-test:jar:4.3.9.RELEASE:test
```
context依赖aop、core、beans、expression几个基础模块
