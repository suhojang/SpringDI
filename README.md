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

+ Setter Method Injection Call
```
2021-04-12 14:19:12.373  INFO 23220 --- [nio-8080-exec-9] c.jsh.di.repository.SpringDIRepository   : hello Spring Dependency Injection Setter
```

+ Field Injection Call
```
2021-04-12 14:19:25.401  INFO 23220 --- [io-8080-exec-10] c.jsh.di.repository.SpringDIRepository   : hello Spring Dependency Injection Field
```

+ ETC(Spring Container Annotation)
```
@Contoller 어노테이션을 붙이면 핸들러가 스캔할 수 있는 빈(Bean) 객체가 되어 서블릿용 컨테이너에 생성됩니다. 
마찬가지로 @Repository, @service 어노테이션은 해당 클래스를 루트 컨테이너에 빈(Bean) 객체로 생성해주는 어노테이션입니다.
둘 다 Bean 객체를 생성해주고 딱히 다른 기능을 넣어주는게 아니라서 뭘 써도 상관 없긴한데 명시적으로 구분해주기 위해 각자 분리해서 사용합니다. 
부모 어노테이션인 @Component를 붙여줘도 똑같이 루트 컨테이너에 생성되지만 가시성이 떨어지기 때문에 잘 사용하지 않습니다.
참고로 객체 내에서 데이터 변경 작업이 있는 VO(DTO) 객체와 같은 경우는 동기화 문제로 인해 Bean 객체로 사용하지 않습니다. 
Bean 객체는 항상 데이터 변경이 없는 객체에 한해 사용하는 점에 유의해야 합니다.

컨트롤러 : @Controller (프레젠테이션 레이어, 웹 요청과 응답을 처리함)
로직 처리 : @Service (서비스 레이어, 내부에서 자바 로직을 처리함)
외부I/O 처리 : @Repository (퍼시스턴스 레이어, DB나 파일같은 외부 I/O 작업을 처리함)

@Resource
필드명 또는 생성자 파라미터 변수의 이름과 Bean 객체의 ID를 매핑시켜서 먼저 찾는 방식입니다. 
만약 매핑되는 ID가 없으면 타입을 검색해서 찾아줍니다. 
그런데 같은 타입도 없다면 예외를 발생시킵니다. 
(name="ID")로 ID를 강제 지정할 수도 있습니다.
타입 또는 이름이 항상 맞으면 좋겠지만 오버라이딩을 사용하는 경우 둘 다 달라질 수 있기 때문에 항상 이름을 명시해서 사용해주는 것이 나중에 보기도 좋고 안정적입니다.
```
```java
@Resource(name="sqlSessionFactory")
private SqlSessionFactory sqlFactory;
```
```
@Inject
타입이 같은 Bean을 먼저 찾습니다. 
하지만 같은 타입의 Bean 객체가 여러 개 있다면 다음은 이름으로 찾는데, 그래도 없다면 예외가 발생합니다. 
부모 클래스 타입에다가 여러 자식 클래스의 Bean 객체 중 하나를 오버라이딩 시키는 경우 발생할 수 있는 문제입니다. 
따라서 @Named 어노테이션을 사용해 정확한 Bean ID를 지정해주는 것이 좋습니다.
하나의 필드에 적용할 때는 아래와 같이 사용해주면 됩니다.
```
```java
@Inject	
@Named("sqlSessionFactory")
private SqlSessionFactory sqlFactory;
```

```
@Autowired
스프링에서 제공해주는 어노테이션입니다. 
스프링 의존적이라 나중에 프레임워크를 바꿀 계획이 있다면 위의 두 어노테이션을 사용하는 것이 좋습니다.
사용법과 동작은 @Inject와 거의 유사합니다. 
특정 Bean 객체를 강제로 지정해주는 어노테이션만 @Qualifier를 사용해주면 됩니다.
```
```java
@Autowired
@Qualifier("sqlSessionFactory")
private SqlSessionFactory sqlFactory;
```

```
만약 찾는 Bean 객체가 없을 경우 발생하는 예외를 피하고 싶다면 required 속성값을 false로 줍니다.
못 찾을 경우 null 값으로 존재할테니 나중에 이 객체를 사용하는 곳에서는 null일 경우에 대한 처리를 별도로 해주면 됩니다.
```
```java
@Autowired(required=false)
@Qualifier("^__^")
private SqlSessionFactory sqlFactory;
```
