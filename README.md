#### Spring DI(Dependency Injection)
+ Dependency Injection(의존성 주입)
    + 객체간의 의존성을 자신이 아닌 외부에서 주입하는 개념

+ Inversion of Control Containers and the Dependency Injection pattern
    + Constructor Injection
    + Setter Method Injection
    + Field Injection
    
+ Dependency Injection 장점
    + Reduced Dependencied
      + 종속성이 감소한다.
      + components의 종속성이 감소하면 변경에 민감하지 않다.
    + More Reusable Code
      + 재사용성이 증가한다.
      + 일부 인터페이스의 다른 구현이 필요한 경우, 코드를 변경 할 필요없이 해당 구현을 사용하도록 components를 구성할 수 있다.
    + More Testable Code
      + 더 많은 테스트 코드를 만들 수 있다.
      + Mock 객체는 실제 구현의 테스트로 사용되는 객체
        + 종속성을 components에 주입할 수 있는 경우 이러한 종속성의 Mock 구현을 주입할 수 있다.
        + 예를 들어, Mock 객체가 올바른 객체를 반환할 때, null을 반환할 때, 예외가 발생할 때 모두 처리한다.
    + More Readable Code
      + 코드를 읽기 쉬워진다.
      + components의 종속성을 보다 쉽게 파악할 수 있으므로 코드를 쉽게 읽을 수 있다. 

+ Spring Container metadata 설정 방법
  + XML
    + 빈 객체 정의(Bean Definition)
    + 의존성 주입(Dependency Injection)
  + Java Annotations
  + Java Code

+ Spring Container
  + BeanFactory
    + 주로 단순한 DI에서만 사용한다.
    + XMLBeanFactory
  + ApplicationContext
    + Resources가 제한되어 있지 않은 모든 곳에 사용한다.
    + ClassPathXmlApplicationContext

+ Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
  ```
+ Gradle
```groovy
compile('org.springframework.boot:spring-boot-starter-web')
compile('org.springframework.boot:spring-boot-starter-test')

```
+ Constructor Injection
```java
package com.jsh.di.controller;

import com.jsh.di.repository.SpringDIRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDIConstructorController {
    private SpringDIRepository repository;

    public SpringDIConstructorController(SpringDIRepository repository){
        this.repository = repository;
    }

    @GetMapping(value="/helloConstructor")
    public String helloConstructor(){
        return repository.getHello("Constructor");
    }
}
```

+ Setter Method Injection
```java
package com.jsh.di.controller;

import com.jsh.di.repository.SpringDIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDISetterController {
    private SpringDIRepository repository;

    @Autowired
    public void setRepository(SpringDIRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value="/helloSetter")
    public String helloSetter(){
        return repository.getHello("Setter");
    }
}
```

+ Field Injection
```java
package com.jsh.di.controller;

import com.jsh.di.repository.SpringDIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringDIFieldController {
    @Autowired
    private SpringDIRepository repository;

    @GetMapping(value="/helloField")
    public String helloFiled(){
        return repository.getHello("Field");
    }
}
```

+ Constructor Injection Call
```
2021-04-12 14:18:49.894  INFO 23220 --- [nio-8080-exec-7] c.jsh.di.repository.SpringDIRepository   : hello Spring Dependency Injection Constructor
```

+ Setter Method Injection
```
2021-04-12 14:19:12.373  INFO 23220 --- [nio-8080-exec-9] c.jsh.di.repository.SpringDIRepository   : hello Spring Dependency Injection Setter
```

+ Field Injection
```
2021-04-12 14:19:25.401  INFO 23220 --- [io-8080-exec-10] c.jsh.di.repository.SpringDIRepository   : hello Spring Dependency Injection Field
```