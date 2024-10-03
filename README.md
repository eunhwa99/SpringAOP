# SpringAOP

- [AOP 적용 방식](https://silver-programmer.tistory.com/entry/AOP-%EC%A0%81%EC%9A%A9-%EB%B0%A9%EC%8B%9D)
- [Spring AOP란?](https://silver-programmer.tistory.com/entry/Spring-AOP-%EB%9E%80)
- [스프링 AOP 구현하기1 - @Aspect, @Around, @Pointcut](https://silver-programmer.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-AOP-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-1)
- [스프링 AOP 구현하기2 - 포인트컷 분리, @Order](https://silver-programmer.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-AOP-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0-2)
- [스프링 AOP 어드바이스 종류 - @Around, @Before, @AfterReturning, @AfterThrowing, @After]([advanced/src/test/java/hello/aop/exam/ExamTest.java](https://silver-programmer.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-AOP-%EC%96%B4%EB%93%9C%EB%B0%94%EC%9D%B4%EC%8A%A4-%EC%A2%85%EB%A5%98-Around-Before-AfterReturning-AfterThrowing-After))
- [Spring AOP - execution](https://silver-programmer.tistory.com/entry/Spring-AOP-%ED%8F%AC%EC%9D%B8%ED%8A%B8%EC%BB%B7pointcut-%EC%A7%80%EC%8B%9C%EC%9E%90exectution)
- [Spring AOP - within, args](https://silver-programmer.tistory.com/entry/Spring-AOP-%ED%8F%AC%EC%9D%B8%ED%8A%B8%EC%BB%B7pointcut-%EC%A7%80%EC%8B%9C%EC%9E%90within-args)
- [Spring AOP - @target, @within](https://silver-programmer.tistory.com/entry/Spring-AOP-%ED%8F%AC%EC%9D%B8%ED%8A%B8%EC%BB%B7pointcut-%EC%A7%80%EC%8B%9C%EC%9E%90target-within)
- [Spring AOP - @annotation, this, target](https://silver-programmer.tistory.com/entry/Spring-AOP-%ED%8F%AC%EC%9D%B8%ED%8A%B8%EC%BB%B7pointcut-%EC%A7%80%EC%8B%9C%EC%9E%90annotation-this-target)
- [Spring AOP Internal Call problem and solution](https://silver-programmer.tistory.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81-AOP%EC%9D%98-%EB%82%B4%EB%B6%80%ED%98%B8%EC%B6%9C-%EB%AC%B8%EC%A0%9C-%EB%B0%8F-%ED%95%B4%EA%B2%B0%EB%B2%95)




## JDKProxy vs CGLIB
### [Type casting](https://github.com/eunhwa99/SpringAOP/blob/main/advanced/src/test/java/hello/aop/proxys/ProxyCastingTest.java)
1. JDKProxy
![image](https://github.com/user-attachments/assets/641cade5-14ce-4f17-b76f-d2f1274546b0)

- `MemberServiceImpl` type proxy -> JDKProxy creates proxy based on `MemberService` which is an interface
- Therefore, it's impossible to cast proxy to `MemberServiceImpl`. (proxy is made based on `MemberService` type)
![image](https://github.com/user-attachments/assets/1e597fde-e133-438f-90c6-1f375211d7cd)
---
2. CGLIB  
![image](https://github.com/user-attachments/assets/e2d70318-6381-4e98-b4c4-b79441162ff8)

- `MemberServiceImpl` type proxy -> CGLIB creates proxy based on `MemberServiceImpl` which is a concrete class
- Therefore, it's possible to cast proxy to `MemberService` as well as `MemberServiceImpl`
![image](https://github.com/user-attachments/assets/eb9b1698-af11-496e-8942-9f7e71754e05)


### [Dependy Injection](https://github.com/eunhwa99/SpringAOP/blob/main/advanced/src/test/java/hello/aop/proxys/ProxyDITest.java)

1. JDKProxy

- When run the `ProxyDITest` with `spring.aop.proxy-target-class=false` option, it uses JDKProxy.
- Therefore, it produces error like below. (JDKProxy cannot inject bean to MemberServiceImpl)

```
org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'memberServiceImpl' is expected to be of type 'hello.aop.member.MemberServiceImpl' but was actually of type 'jdk.proxy3.$Proxy55'
```

![image](https://github.com/user-attachments/assets/9b5a3c4e-de37-4b0b-a117-e648d52a9720)


---
2. CGLIB  
- When run the `ProxyDITest` with `spring.aop.proxy-target-class=true` option(which is default), it uses CGLIB.
- Therefore, it does not produce any errors.

![image](https://github.com/user-attachments/assets/f75be4e3-d743-4785-9f35-9cb7c671ffbe)


Therefore, if you want to use DI to concrete class with AOP proxy applied, you can use CGLIB to create AOP proxies based on the concrete class. In addition, Spring ultimately decided to use CGLIB as the default starting from Spring Boot 2.0, so you don't have to configure anything to use CGLIB.












