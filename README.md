# spring
## 1、Spring概述

![image-20200831173249570](https://tva1.sinaimg.cn/large/007S8ZIlgy1gia4l1uwh7j30ag03fwfd.jpg)

### 1.1 简介

- Spring : 春天 --->给软件行业带来了春天 
- 2002年，Rod Jahnson首次推出了Spring框架雏形interface21框架。 
- 2004年3月24日，Spring框架以interface21框架为基础，经过重新设计，发布了1.0正式版。 
- 很难想象Rod Johnson的学历 , 他是悉尼大学的博士，然而他的专业不是计算机，而是音乐学。 
- Spring理念 : 使现有技术更加实用 . 本身就是一个大杂烩 , 整合现有的框架技术

官网 : http://spring.io/ 

官方下载地址 : https://repo.spring.io/libs-release-local/org/springframework/spring/

 GitHub : https://github.com/spring-projects

### 1.2 优点

- Spring是一个开源免费的框架 , 容器 . 
- Spring是一个轻量级的框架 , 非侵入式的 . 
- 控制反转 IoC , 面向切面 Aop 
- 对事物的支持 , 对框架的支持

一句话概括：**Spring是一个轻量级的控制反转(IoC)和面向切面(AOP)的容器（框架）。**

### 1.3 组成

![image-20200831173458518](https://tva1.sinaimg.cn/large/007S8ZIlgy1gia4n982luj30g30bs761.jpg)

Spring 框架是一个分层架构，由 7 个定义良好的模块组成。Spring 模块构建在核心容器之上，核心容器 定义了创建、配置和管理 bean 的方式 .

![image-20200831173543565](https://tva1.sinaimg.cn/large/007S8ZIlgy1gia4o0z49vj30ga08lmyl.jpg)

组成 Spring 框架的每个模块（或组件）都可以单独存在，或者与其他一个或多个模块联合实现。每个模 块的功能如下：

- **核心容器**：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是 ==BeanFactory== ，它 是工厂模式的实现。 ==BeanFactory== 使用控制反转（IOC） 模式将应用程序的配置和依赖性规范 与实际的应用程序代码分开。
- **Spring 上下文**：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文 包括企业服务，例如 JNDI、EJB、电子邮件、国际化、校验和调度功能。
- **Spring AOP**：通过配置管理特性，Spring AOP 模块直接将面向切面的编程功能 , 集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理任何支持 AOP的对象。Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖组件，就可以 将声明性事务管理集成到应用程序中。
- **Spring DAO**：JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不 同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异 常代码数量（例如打开和关闭连接）。Spring DAO 的面向 JDBC 的异常遵从通用的 DAO 异常层次 结构。
- **Spring ORM**：Spring 框架插入了若干个 ORM 框架，从而提供了 ORM 的对象关系工具，其中包括 JDO、Hibernate 和 iBatis SQL Map。所有这些都遵从 Spring 的通用事务和 DAO 异常层次结构。
- **Spring Web 模块**：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。
- **Spring MVC 框架**：MVC 框架是一个全功能的构建 Web 应用程序的 MVC 实现。通过策略接口， MVC 框架变成为高度可配置的，MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI。

### 1.4 拓展

**Spring Boot与Spring Cloud**

- Spring Boot 是 Spring 的一套快速配置脚手架，可以基于Spring Boot 快速开发单个微服务; 
- Spring Cloud是基于Spring Boot实现的；
-  Spring Boot专注于快速、方便集成的单个微服务个体，Spring Cloud关注全局的服务治理框架； 
- Spring Boot使用了约束优于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置 , Spring Cloud很大的一部分是基于Spring Boot来实现，Spring Boot可以离开Spring Cloud独立使用开发项目，但是Spring Cloud离不开Spring Boot，属于依赖的关系。 
- SpringBoot在SpringClound中起到了承上启下的作用，如果你要学习SpringCloud必须要学习 SpringBoot。

![image-20200831174434647](https://tva1.sinaimg.cn/large/007S8ZIlgy1gia4x8nutwj30g304eq48.jpg)

## 2、IoC基础

新建一个空白的maven项

### 2.1 分析实现

我们先用我们原来的方式写一段代码 

\1. 先写一个UserDao接口

```java
public interface UserDao {
	public void getUser();
}
```

\2. 再去写Dao的实现类

```java
public class UserDaoImpl implements UserDao {
    @Override
    public void getUser() {
    		System.out.println("获取用户数据");
}
```

\3. 然后去写UserService的接口

```java
public interface UserService {
		public void getUser();
}
```

\4. 最后写Service的实现类

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
  
    @Override
    public void getUser() {
      userDao.getUser();
    }
}
```

\5. 测试一下

```java
@Test
public void test(){
    UserService service = new UserServiceImpl();
    service.getUser();
}
```

这是我们原来的方式 , 开始大家也都是这么去写的对吧 . 那我们现在修改一下 

把Userdao的实现类增加一个 .

```java
public class UserDaoMySqlImpl implements UserDao {
    @Override
    public void getUser() {
    		System.out.println("MySql获取用户数据");
    }
}
```

紧接着我们要去使用MySql的话 , 我们就需要去service实现类里面修改对应的实现 .

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoMySqlImpl();
  
    @Override
    public void getUser() {
    		userDao.getUser();
    }
}
```

在假设, 我们再增加一个Userdao的实现类 

```java
public class UserDaoOracleImpl implements UserDao {
    @Override
    public void getUser() {
    		System.out.println("Oracle获取用户数据");
    }
}
```

那么我们要使用Oracle , 又需要去service实现类里面修改对应的实现 . 假设我们的这种需求非常大 , 这种方式就根本不适用了, 甚至反人类对吧 , 每次变动 , 都需要修改大量代码 . 这种设计的耦合性太高了, 牵一 发而动全身 .

**那我们如何去解决呢 ?**

我们可以在需要用到他的地方 , 不去实现它 , 而是留出一个接口 , 利用set , 我们去代码里修改下 .

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    // 利用set实现
    public void setUserDao(UserDao userDao) {
    		this.userDao = userDao;
    }
    @Override
    public void getUser() {
    		userDao.getUser();
    }
}
```

现在去我们的测试类里 , 进行测试 ;

```java
@Test
public void test(){
    UserServiceImpl service = new UserServiceImpl();
    service.setUserDao( new UserDaoMySqlImpl() );
    service.getUser();
    //那我们现在又想用Oracle去实现呢
    service.setUserDao( new UserDaoOracleImpl() );
    service.getUser();
}
```

大家发现了区别没有 ? 可能很多人说没啥区别 . 但是同学们 , 他们已经发生了根本性的变化 , 很多地方都 不一样了 . 仔细去思考一下 , 以前所有东西都是由程序去进行控制创建 , 而现在是由我们自行控制创建对 象 , 把主动权交给了调用者 . 程序不用去管怎么创建,怎么实现了 . 它只负责提供一个接口 . 这种思想 , 从本质上解决了问题 , 我们程序员不再去管理对象的创建了 , 更多的去关注业务的实现 . 耦合 性大大降低 . 这也就是IOC的原型 !

### 2.2 IOC本质

**控制反转IoC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现IoC的一种方法**，也有人认为DI只是IoC的另一种说法。没有IoC的程序中 , 我们使用面向对象编程 , 对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是：获得依赖对象的方式反转了。

![image-20200831175614559](https://tva1.sinaimg.cn/large/007S8ZIlgy1gia59dky8wj30hb05hjt4.jpg)

**IoC是Spring框架的核心内容**，使用多种方式完美的实现了IoC，可以使用XML配置，也可以使用注解， 新版本的Spring也可以零配置实现IoC。 Spring容器在初始化时先读取配置文件，根据配置文件或元数据创建与组织对象存入容器中，程序使用时再从Ioc容器中取出需要的对象。

![image-20200831175655165](https://tva1.sinaimg.cn/large/007S8ZIlgy1gia5a2wgfaj30gq0d4q48.jpg)

采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。

**控制反转是一种通过描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IoC容器，其实现方法是依赖注入（Dependency Injection,DI）。**

## 3、HelloSpring

### 3.1、导入Jar包

注 : spring 需要导入commons-logging进行日志记录 . 我们利用maven , 他会自动下载对应的依赖项 .

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.1.10.RELEASE</version>
</dependency>
```

### 3.2、编写代码

\1. 编写一个Hello实体类

```java
public class Hello {
    private String name;
    public String getName() {
    		return name;
    }
    public void setName(String name) {
    		this.name = name;
    }
    public void show(){
    		System.out.println("Hello,"+ name );
    }
}
```

\2. 编写我们的spring文件 , 这里我们命名为beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">
<!--bean就是java对象 , 由Spring创建和管理-->
    <bean id="hello" class="com.epfox.pojo.Hello">
    		<property name="name" value="Spring"/>
    </bean>
</beans>
```

\3. 我们可以去进行测试了 .

```java
@Test
public void test(){
    //解析beans.xml文件 , 生成管理相应的Bean对象
    ApplicationContext context = new
ClassPathXmlApplicationContext("beans.xml");
    //getBean : 参数即为spring配置文件中bean的id .
    Hello hello = (Hello) context.getBean("hello");
    hello.show();
}
```

### 3.3、思考

- Hello 对象是谁创建的 ? 【 hello 对象是由Spring创建的 】
- Hello 对象的属性是怎么设置的 ? 【hello 对象的属性是由Spring容器设置的 】

这个过程就叫控制反转 :

- 控制 : 谁来控制对象的创建 , 传统应用程序的对象是由程序本身控制创建的 , 使用Spring后 , 对象是由Spring来创建的
- 反转 : 程序本身不创建对象 , 而变成被动的接收对象 .

依赖注入 : 就是利用set方法来进行注入的.

==IOC是一种编程思想，由主动的编程变成被动的接收==

可以通过newClassPathXmlApplicationContext去浏览一下底层源码 .

### 3.4、修改案例一

我们在案例一中， 新增一个Spring配置文件beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="MysqlImpl" class="com.epfox.dao.impl.UserDaoMySqlImpl"/>
    <bean id="OracleImpl" class="com.epfox.dao.impl.UserDaoOracleImpl"/>
    <bean id="ServiceImpl" class="com.epfox.service.impl.UserServiceImpl">
        <!--注意: 这里的name并不是属性 , 而是set方法后面的那部分 , 首字母小写-->
        <!--引用另外一个bean , 不是用value 而是用 ref-->
        <property name="userDao" ref="OracleImpl"/>
    </bean>
</beans>
```

测试！

```java
@Test
public void test2(){
		ApplicationContext context = new
ClassPathXmlApplicationContext("beans.xml");
		UserServiceImpl serviceImpl = (UserServiceImpl)
context.getBean("ServiceImpl");
    serviceImpl.getUser();
}
```

OK , 到了现在 , 我们彻底不用再程序中去改动了 , 要实现不同的操作 , 只需要在xml配置文件中进行修改 , 所谓的IoC,一句话搞定 : 对象由Spring 来创建 , 管理 , 装配 !

## 4、IOC创建对象方式

### 4.1.通过无参构造方法来创建

\1. User.java

```java
public class User {
    private String name;

    public User(){
        System.out.println("user无参构造方法");
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name="+name);
    }
}
```

\2. beans.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
      http://www.springframework.org/schema/beans/spring-beans.xsd">
  <bean id="user" class="com.epfox.pojo.User">
    <property name="name" value="epfox"/>
  </bean>
</beans>
```

\3. 测试类

```java
@Test
public void test(){
		ApplicationContext context = new
ClassPathXmlApplicationContext("beans.xml");
    //在执行getBean的时候, user已经创建好了 , 通过无参构造
    User user = (User) context.getBean("user");
    //调用对象的方法 .
    user.show();
}
```

结果可以发现，在调用show方法之前，User对象已经通过无参构造初始化了！

### 4.2.通过有参构造方法来创建

\1. UserT . java

```java
public class UserT {
    private String name;

    public UserT(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name="+name);
    }
}
```

\2. beans.xml 有三种方式编写

```xml
<!-- 第一种根据index参数下标设置 -->
<bean id="userT" class="com.epfox.pojo.UserT">
    <!-- index指构造方法 , 下标从0开始 -->
    <constructor-arg index="0" value="epfox2"/>
</bean>
```

```xml
<!-- 第二种根据参数名字设置 -->
<bean id="userT" class="com.epfox.pojo.UserT">
    <!-- name指参数名 -->
    <constructor-arg name="name" value="epfox2"/>
</bean>
```

```xml
<!-- 第三种根据参数类型设置 -->
<bean id="userT" class="com.epfox.pojo.UserT">
		<constructor-arg type="java.lang.String" value="epfox2"/>
</bean>
```

结论：在配置文件加载的时候。其中管理的对象都已经初始化了！

## 5、Spring配置

### 5.1. 别名

alias 设置别名 , 为bean设置别名 , 可以设置多个别名

```java
<!--设置别名：在获取Bean的时候可以使用别名获取-->
<alias name="userT" alias="userNew"/>
```

### 5.2. Bean的配置

```xml
<!--bean就是java对象,由Spring创建和管理-->
<!--
  id: bean 的唯一标识符 相当于我们学的对象名
  class: bean 对象所对应的全限定名 ： 包名+类型
  name: 也是别名，而且name可以多个别名
  -->
    <bean id="userT" class="com.epfox.pojo.UserT" name="user2 u2,u3;u4">
        <property name="name" value="epfox666@github" />
    </bean>
```

### 5.3. import

```xml
    <import resource="beans.xml"/>
    <import resource="beans2.xml"/>
    <import resource="beans3.xml"/>
```

## 6、依赖注入（DI）

- 依赖注入（Dependency Injection,DI）。 
- 依赖 : 指Bean对象的创建依赖于容器 . Bean对象的依赖资源 . 
- 注入 : 指Bean对象所依赖的资源 , 由容器来设置和装配 .

### 6.1 构造器注入

我们在之前的案例4已经详细讲过了

### 6.2 set注入 (重点)

要求被注入的属性 , 必须有set方法 , set方法的方法名由set + 属性首字母大写 , 如果属性是boolean类型 , 没有set方法 , 是 is .

测试pojo类 :

Address.java

```java
public class Address {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                '}';
    }
}
```

Student.java

```java
public class Student {
    private String name;
    private Address address;
    private String[] books;
    private List<String> hobby;
    private Map<String,String> card;
    private Set<String> games;
    private String wife;
    private Properties info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public Map<String, String> getCard() {
        return card;
    }

    public void setCard(Map<String, String> card) {
        this.card = card;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", hobby=" + hobby +
                ", card=" + card +
                ", games=" + games +
                ", wife='" + wife + '\'' +
                ", info=" + info +
                '}';
    }
}

```

1、常量注入

```xml
<bean id="student" class="com.epfox.pojo.Student">
  <!--   第一种  普通注入 value   -->
  <property name="name" value="epfox"/>
</bean>
```

测试：

```java
@Test
public void test(){
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    Student student = (Student) context.getBean("student");
    System.out.println(student);
}
```

2、Bean注入

==注意点：这里的值是一个引用，ref==

```xml
<bean id="address" class="com.epfox.pojo.Address" >
    <property name="address" value="北京"/>
 </bean>
<bean id="student" class="com.epfox.pojo.Student">
  <property name="name" value="epfox"/>
  <property name="address" ref="address"/>
</bean>
```

3、数组注入

```xml
<bean id="student" class="com.epfox.pojo.Student">
    <property name="name" value="epfox"/>
    <property name="address" ref="address"/>
    <property name="books">
        <array>
            <value>JAVA大全1</value>
            <value>JAVA大全2</value>
            <value>JAVA大全3</value>
            <value>JAVA大全4</value>
        </array>
    </property>
</bean>
```

4、List注入

```xml
<property name="hobby">
  <list>
    <value>听歌</value>
    <value>敲代码</value>
    <value>看电影</value>
  </list>
</property>
```

5、Map注入

```xml
<property name="card">
  <map>
    <entry key="1" value="a"/>
    <entry key="2" value="b"/>
    <entry key="3" value="c"/>
  </map>
</property>
```

6、set注入

```xml
<property name="games">
  <set>
    <value>LOL</value>
    <value>COC</value>
    <value>BOB</value>
  </set>
</property>
```

7、Null注入

```xml
<!--  null  -->
<property name="wife">
  <null/>
</property>
```

8、Properties注入

```xml
<!--  properties  -->
<property name="info">
  <props>
    <prop key="1">a</prop>
    <prop key="2">b</prop>
    <prop key="3">c</prop>
  </props>
</property>
```

测试结果：

```java
Student{name='epfox', address=Address{address='北京'}, books=[JAVA大全1, JAVA大全2, JAVA大全3, JAVA大全4], hobby=[听歌, 敲代码, 看电影], card={1=a, 2=b, 3=c}, games=[LOL, COC, BOB], wife='null', info={3=c, 2=b, 1=a}}
```

### 6.3 拓展注入实现

User.java ： 【注意：构造器可以忽略】

```java
public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```

1、P命名空间注入 : 需要在头文件中假如约束文件

```xml
导入约束 : xmlns:p="http://www.springframework.org/schema/p"
<!--P(属性: properties)命名空间 , 属性依然要设置set方法-->
<bean id="user" class="com.epfox.pojo.User" p:name="epfox" p:age="18"/>
```

2、c 命名空间注入 : 需要在头文件中假如约束文件

```xml
导入约束 : xmlns:c="http://www.springframework.org/schema/c"
<!--C(构造: Constructor)命名空间 , 属性依然要设置set方法-->
<bean id="user2" class="com.epfox.pojo.User" c:name="epfox6" c:age="19" scope="prototype"/>
```

发现问题：爆红了，刚才我们没有写有参构造！ 

解决：把有参构造器加上，这里也能知道，c 就是所谓的构造器注入！ 

测试代码：

```java
@Test
public void test3(){
  ApplicationContext context = new ClassPathXmlApplicationContext("userbeans.xml");
  User user = context.getBean("user2", User.class);
  System.out.println(user);
}
```

### 6.4 Bean的作用域

在Spring中，那些组成应用程序的主体及由Spring IoC容器所管理的对象，被称之为bean。简单地讲， bean就是由IoC容器初始化、装配及管理的对象 .

![image-20200903160746078](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidizhgl3dj30gg07en0g.jpg)

几种作用域中，request、session作用域仅在基于web的应用中使用（不必关心你所采用的是什么web 应用框架），只能用在基于web的Spring ApplicationContext环境。

#### 6.4.1 Singleton

当一个bean的作用域为Singleton，那么Spring IoC容器中只会存在一个共享的bean实例，并且所有对 bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例。Singleton是单例类型，就是 在创建起容器时就同时自动创建了一个bean的对象，不管你是否使用，他都存在了，每次获取到的对象 都是同一个对象。注意，Singleton作用域是Spring中的缺省作用域。要在XML中将bean定义成 singleton，可以这样配置：

```xml
<bean id="ServiceImpl" class="cn.csdn.service.ServiceImpl" scope="singleton">
```

测试：

```java
@Test
public void test03(){
		ApplicationContext context = new
ClassPathXmlApplicationContext("userbeans.xml");
    User user = (User) context.getBean("user");
    User user2 = (User) context.getBean("user");
    System.out.println(user==user2);
}
```

#### 6.4.2 Prototype

当一个bean的作用域为Prototype，表示一个bean定义对应多个对象实例。Prototype作用域的bean会 导致在每次对该bean请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法） 时都会创建一个新的bean实例。Prototype是原型类型，它在我们创建容器的时候并没有实例化，而是 当我们获取bean的时候才会去创建一个对象，而且我们每次获取到的对象都不是同一个对象。根据经 验，对有状态的bean应该使用prototype作用域，而对无状态的bean则应该使用singleton作用域。在 XML中将bean定义成prototype，可以这样配置：

```xml
<bean id="account" class="com.foo.DefaultAccount" scope="prototype"/>
或者
<bean id="account" class="com.foo.DefaultAccount" singleton="false"/>
```

#### 6.4.3 Request

当一个bean的作用域为Request，表示在一次HTTP请求中，一个bean定义对应一个实例；即每个HTTP 请求都会有各自的bean实例，它们依据某个bean定义创建而成。该作用域仅在基于web的Spring ApplicationContext情形下有效。考虑下面bean定义：

```xml
<bean id="loginAction" class=cn.csdn.LoginAction" scope="request"/>
```

针对每次HTTP请求，Spring容器会根据loginAction bean的定义创建一个全新的LoginAction bean实例，且该loginAction bean实例仅在当前HTTP request内有效，因此可以根据需要放心的更改所建实例的内部状态，而其他请求中根据loginAction bean定义创建的实例，将不会看到这些特定于某个请求的状态变化。当处理请求结束，request作用域的bean实例将被销毁。

#### 6.4.4 Session

当一个bean的作用域为Session，表示在一个HTTP Session中，一个bean定义对应一个实例。该作用域 仅在基于web的Spring ApplicationContext情形下有效。考虑下面bean定义：

```xml
<bean id="userPreferences" class="com.foo.UserPreferences" scope="session"/>
```

针对某个HTTP Session，Spring容器会根据userPreferences bean定义创建一个全新的 userPreferences bean实例，且该userPreferences bean仅在当前HTTP Session内有效。与request作用域一样，可以根据需要放心的更改所创建实例的内部状态，而别的HTTP Session中根据 userPreferences创建的实例，将不会看到这些特定于某个HTTP Session的状态变化。当HTTP Session 最终被废弃的时候，在该HTTP Session作用域内的bean也会被废弃掉。

## 7、Bean的自动装配

- 自动装配是使用spring满足bean依赖的一种方法 
- spring会在应用上下文中为某个bean寻找其依赖的bean。

Spring中bean有三种装配机制，分别是：

\1. 在xml中显式配置； 

\2. 在java中显式配置； 

\3. 隐式的bean发现机制和自动装配。

这里我们主要讲第三种：自动化的装配bean。

Spring的自动装配需要从两个角度来实现，或者说是两个操作：

\1. 组件扫描(component scanning)：spring会自动发现应用上下文中所创建的bean； 

\2. 自动装配(autowiring)：spring自动满足bean之间的依赖，也就是我们说的IoC/DI；

组件扫描和自动装配组合发挥巨大威力，使的显示的配置降低到最少。

**推荐不使用自动装配xml配置 , 而使用注解 .**

### 7.1、测试环境搭建

\1. 新建一个项目

\2. 新建两个实体类，Cat Dog 都有一个叫的方法

```java
public class Cat {
    public void shout(){
        System.out.println("miao~");
    }
}
```

```java
public class Dog {
    public void shout(){
        System.out.println("wang~");
    }
}
```

\3. 新建一个用户类 People

```java
public class People {
    private Cat cat;
    private Dog dog;
    private String name;

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

```

\4. 编写Spring配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans.xsd">    
<!-- 手动装配   -->
    <bean id="cat" class="com.epfox.pojo.Cat"/>
    <bean id="dog" class="com.epfox.pojo.Dog"/>

    <bean id="people" class="com.epfox.pojo.People">
    <property name="name" value="epfox"/>
    <property name="dog" ref="dog"/>
    <property name="cat" ref="cat"/>
  </bean>
```

\5. 测试

```java
@Test
public void test1(){
  ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
  People people = context.getBean("people", People.class);
  people.getDog().shout();
  people.getCat().shout();
  System.out.println(people);
}
```

结果正常输出，环境OK

### 7.2、byName

**autowire byName (按名称自动装配)**

由于在手动配置xml过程中，常常发生字母缺漏和大小写等错误，而无法对其进行检查，使得开发效率降低。

采用自动装配将避免这些错误，并且使配置简单化。

测试：

\1. 修改bean配置，增加一个属性 autowire="byName"

```xml
    <!-- 自动装配 byName  id要对应  id写错会报错 -->
    <bean id="cat" class="com.epfox.pojo.Cat"/>
    <bean id="dog" class="com.epfox.pojo.Dog"/>
<!---->
    <bean id="people" class="com.epfox.pojo.People" autowire="byName">
        <property name="name" value="epfox"/>
    </bean>
```

\2. 再次测试，结果依旧成功输出！

\3. 我们将 cat 的bean id修改为 catXXX

\4. 再次测试， 执行时报空指针java.lang.NullPointerException。因为按byName规则找不对应set方法，真正的setCat就没执行，对象就没有初始化，所以调用时就会报空指针错误。

**小结：**

当一个bean节点带有 autowire byName的属性时。

\1. 将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。

\2. 去spring容器中寻找是否有此字符串名称id的对象。

\3. 如果有，就取出注入；如果没有，就报空指针异常。



### 7.3、byType

**autowire byType (按类型自动装配)**

使用autowire byType首先需要保证：同一类型的对象，在spring容器中唯一。如果不唯一，会报不唯一的异常。

```xml
NoUniqueBeanDefinitionException
```

测试：

\1. 将user的bean配置修改一下 ： ==autowire="byType"==

\2. 测试，正常输出

\3. 在注册一个cat 的bean对象！

```xml
<bean id="cat" class="com.epfox.pojo.Cat"/>
<bean id="cat2" class="com.epfox.pojo.Cat"/>
<bean id="dog" class="com.epfox.pojo.Dog"/>
<bean id="people" class="com.epfox.pojo.People" autowire="byName">
  <property name="name" value="epfox"/>
</bean>
```

\4. 测试，报错：NoUniqueBeanDefinitionException

\5. 删掉cat2，将cat的bean名称改掉！测试！因为是按类型装配，所以并不会报异常，也不影响最后的结果。甚至将id属性去掉，也不影响结果。

```xml
    <!-- 自动装配 byType  id要唯一  多个id会报错-->
    <bean class="com.epfox.pojo.Cat"/>
    <bean class="com.epfox.pojo.Dog"/>
<!---->
    <bean id="people" class="com.epfox.pojo.People" autowire="byType">
        <property name="name" value="epfox"/>
    </bean>
```

这就是按照类型自动装配！

### 7.4 使用注解

jdk1.5开始支持注解，spring2.5开始全面支持注解。

准备工作： 利用注解的方式注入属性。

\1. 在spring配置文件中引入context文件头

```xml
xmlns:context="http://www.springframework.org/schema/context"

http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd
```

\2. 开启属性注解支持！

```xml
<context:annotation-config/>
```

#### 7.4.1、@Autowired

- @Autowired是按类型自动转配的，不支持id匹配。
- 需要导入 spring-aop的包！

测试：

\1. 将User类中的set方法去掉，使用@Autowired注解

```java
public class People {
    @Autowired
    private Cat cat;
    @Autowired
    private Dog dog;
    private String name;

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}

```

\2. 此时配置文件内容

```xml
<context:annotation-config/>
<bean id="dog" class="com.epfox.pojo.Dog"/>
<bean id="cat" class="com.epfox.pojo.Cat"/>
<bean id="people" class="com.epfox.pojo.People"/>
```

\3. 测试，成功输出结果！

【科普时间】

@Autowired(required=false) 说明： false，对象可以为null；true，对象必须存对象，不能为null。

```java
//如果允许对象为null，设置required = false,默认为true
@Autowired(required = false)
private Cat cat;
```

#### 7.4.2、@Qualifier

- @Autowired是根据类型自动装配的，加上@Qualifier则可以根据byName的方式自动装配
- @Qualifier不能单独使用。

测试实验步骤：

\1. 配置文件修改内容，保证类型存在对象。且名字不为类的默认名字！

```xml
    <context:annotation-config/>
    <bean id="cat2" class="com.epfox.pojo.Cat"/>
    <bean id="cat1" class="com.epfox.pojo.Cat"/>
    <bean id="dog2" class="com.epfox.pojo.Dog"/>
    <bean id="dog1" class="com.epfox.pojo.Dog"/>
    <bean id="people" class="com.epfox.pojo.People"/>
```

\2. 没有加Qualifier测试，直接报错

\3. 在属性上添加Qualifier注解

```java
@Autowired
@Qualifier(value = "cat2")
private Cat cat;
@Autowired
@Qualifier(value = "dog2")
private Dog dog;
```

\4. 测试，成功输出！

#### 7.4.3、@Resource

- @Resource如有指定的name属性，先按该属性进行byName方式查找装配；
- 其次再进行默认的byName方式进行装配；
- 如果以上都不成功，则按byType的方式自动装配。
- 都不成功，则报异常。

实体类：

```java
public class People {
    @Resource(name = "cat2")
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;
}
```

beans.xml

```xml
<context:annotation-config/>
<bean id="cat2" class="com.epfox.pojo.Cat"/>
<bean id="cat1" class="com.epfox.pojo.Cat"/>
<bean id="dog" class="com.epfox.pojo.Dog"/>

<bean id="people" class="com.epfox.pojo.People"/>
```

测试：结果OK

配置文件2：beans.xml ， 删掉cat2

```xml
<context:annotation-config/>
<bean id="cat1" class="com.epfox.pojo.Cat"/>
<bean id="dog" class="com.epfox.pojo.Dog"/>

<bean id="people" class="com.epfox.pojo.People"/>
```

实体类上只保留注解

```java
@Resource
private Cat cat;
@Resource
private Dog dog;
```

结果：OK

结论：先进行byName查找，失败；再进行byType查找，成功。

### 7.5、小结

@Autowired与@Resource异同：

\1. @Autowired与@Resource都可以用来装配bean。都可以写在字段上，或写在setter方法上。

\2. @Autowired默认按类型装配（属于spring规范），默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用

\3. @Resource（属于J2EE复返），默认按照名称进行装配，名称可以通过name属性进行指定。如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。 当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。



它们的作用相同都是用注解方式注入对象，但执行顺序不同。@Autowired先byType，@Resource先byName。



## 8、使用注解开发

### 8.1、说明

在spring4之后，想要使用注解形式，必须得要引入aop的包

![image-20200903182224799](https://tva1.sinaimg.cn/large/007S8ZIlgy1gidmvk00wfj30hk07vwgz.jpg)

在配置文件当中，还得要引入一个context约束

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">


</beans>
```

### 8.2、Bean的实现

我们之前都是使用 bean 的标签进行bean注入，但是实际开发中，我们一般都会使用注解！

\1. 配置扫描哪些包下的注解

```xml
<!--指定注解扫描包-->
<context:component-scan base-package="com.epfox.pojo"/>
```

\2. 在指定包下编写类，增加注解

```java
@Component //等价于 <bean id="user" class="com.epfox.pojo.User"/>
public class User {
    public String name = "epfox";
    }
}
```

\3. 测试

```java
@Test
public void test(){
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    User user = (User) context.getBean("user");
    System.out.println(user.name);

}
```

### 8.3、属性注入

使用注解注入属性

\1. 可以不用提供set方法，直接在直接名上添加@value("值")

```java
@Component //等价于 <bean id="user" class="com.epfox.pojo.User"/>
public class User {

    @Value("epfox") //等价于 <property name="name" value="epfox"/>
    public String name;
}
```

\2. 如果提供了set方法，在set方法上添加@value("值");

```java
@Component //等价于 <bean id="user" class="com.epfox.pojo.User"/>
public class User {

    public String name;

    @Value("epfox") //等价于 <property name="name" value="epfox"/>
    public void setName(String name) {
        this.name = name;
    }
}
```

### 8.4、衍生注解

我们这些注解，就是替代了在配置文件当中配置步骤而已！更加的方便快捷！

**@Component三个衍生注解**

为了更好的进行分层，Spring可以使用其它三个注解，功能一样，目前使用哪一个功能都一样。

- @Controller：web层
- @Service：service层
- @Repository：dao层

写上这些注解，就相当于将这个类交给Spring管理装配了！

### 8.5、自动装配注解

在Bean的自动装配已经讲过了，可以回顾！

### 8.6、作用域

@scope

- singleton：默认的，Spring会采用单例模式创建这个对象。关闭工厂 ，所有的对象都会销毁。
- prototype：多例模式。关闭工厂 ，所有的对象不会销毁。内部的垃圾回收机制会回收

```java
@Controller
@Scope("prototype")
public class User {
        @Value("epfox")
        public String name;
}
```



### 8.7、小结

**XML与注解比较**

- XML可以适用任何场景 ，结构清晰，维护方便
- 注解不是自己提供的类使用不了，开发简单方便

**xml与注解整合开发** ：推荐最佳实践

- xml管理Bean
- 注解完成属性注入
- 使用过程中， 可以不用扫描，扫描是为了类上的注解

```xml
1 <context:annotation-config/>
```

作用：

- 进行注解驱动注册，从而使注解生效
- 用于激活那些已经在spring容器里注册过的bean上面的注解，也就是显示的向Spring注册
- 如果不扫描包，就需要手动配置bean
- 如果不加注解驱动，则注入的值为null！

### 8.8、基于Java类进行配置

JavaConfig 原来是 Spring 的一个子项目，它通过 Java 类的方式提供 Bean 的定义信息，在 Spring4 的

版本， JavaConfig 已正式成为 Spring4 的核心功能 。

测试：

\1. 编写一个实体类，User

```java
@Component //将这个类标注为Spring的一个组件，放到容器中！
public class User {
    private String name;

    public String getName() {
        return name;
    }
    @Value("Epfox")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

\2. 新建一个config配置包，编写一个MyConfig配置类

```java
@Configuration //代表这是一个配置类
@ComponentScan("com.epfox.pojo")
public class EpfoxConfig {

    @Bean //通过方法注册一个bean，这里的返回值就Bean的类型，方法名就是bean的id！
    public User getUser(){
        return new User();
    }
}
```

\3. 测试

```java
@Test
public void test(){
    ApplicationContext context = new AnnotationConfigApplicationContext(EpfoxConfig.class);
    User getUser = (User) context.getBean("getUser");
    System.out.println(getUser.getName());
}
```

\4. 成功输出结果！



**导入其他配置如何做呢？**

\1. 我们再编写一个配置类！

```java
@Configuration //代表这是一个配置类
public class EpfoxConfig2 {
}
```

\2. 在之前的配置类中我们来选择导入这个配置类

```java
@Configuration //代表这是一个配置类
@ComponentScan("com.epfox.pojo")
@Import(MyConfig2.class) //导入合并其他配置类，类似于配置文件中的 inculde 标签
public class EpfoxConfig {

    @Bean //通过方法注册一个bean，这里的返回值就Bean的类型，方法名就是bean的id！
    public User getUser(){
        return new User();
    }
}

```

关于这种Java类的配置方式，我们在之后的SpringBoot 和 SpringCloud中还会大量看到，我们需要知道这些注解的作用即可！

## 9、代理模式

为什么要学习代理模式，因为AOP的底层机制就是动态代理！

代理模式：

- 静态代理
- 动态代理

学习aop之前 , 我们要先了解一下代理模式！

![image-20200904162604725](https://i.loli.net/2020/09/04/uU5kNezdVn7H8O1.png)



### 9.1、静态代理

**静态代理角色分析**

- 抽象角色 : 一般使用接口或者抽象类来实现
- 真实角色 : 被代理的角色
- 代理角色 : 代理真实角色 ; 代理真实角色后 , 一般会做一些附属的操作 .
- 客户 : 使用代理角色来进行一些操作 .

代码实现

Rent . java 即抽象角色

```java
//抽象角色：租房
public interface Rent {
    public void rent();
}
```

Host . java 即真实角色

```java
//真实角色: 房东，房东要出租房子
public class Host implements Rent{

    public void rent() {
        System.out.println("房东要出租房子");
    }
}
```

Proxy . java 即代理角色

```java
//代理角色：中介
public class Proxy implements Rent{
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }
        public void rent() {
        host.rent();
        seeHouse();
        hetong();
        fare();
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    //合同
    public void hetong(){
        System.out.println("签租赁合同");
    }
    //收中介费
    public void fare(){
        System.out.println("收中介费");
    }
    
    
    //    public void setHost(Host host) {
//        this.host = host;
//    }
```

Client . java 即客户

```java
//客户类，一般客户都会去找代理！
public class Client {
    public static void main(String[] args) {
        Host host = new Host();
//        host.rent();  //直接租房
        //代理 中介帮房东租房子 但是 代理角色一般会有一些附属操作
        Proxy proxy = new Proxy(host);
        //你不用管面对房东 直接找中介租房即可！
        proxy.rent();
        
        
        //        Proxy proxy = new Proxy();
//        proxy.setHost(host);
//        proxy.rent();
    }
}
```

分析： 在这个过程中，你直接接触的就是中介，就如同现实生活中的样子，你看不到房东，但是你依旧 租到了房东的房子通过代理，这就是所谓的代理模式，程序源自于生活，所以学编程的人，一般能够更 加抽象的看待生活中发生的事情。

### 9.2、静态代理的好处

- 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
- 公共的业务由代理来完成 . 实现了业务的分工 ,
- 公共业务发生扩展时变得更加集中和方便 .

缺点 :

- 类多了 , 多了代理类 , 工作量变大了 . 开发效率降低 .

我们想要静态代理的好处，又不想要静态代理的缺点，所以 , 就有了动态代理 !

### 9.3、静态代理再理解

同学们练习完毕后，我们再来举一个例子，巩固大家的学习！

练习步骤：

\1. 创建一个抽象角色，比如咋们平时做的用户业务，抽象起来就是增删改查！

```java
//抽象角色：增删改查业务
public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void query();
}
```

\2. 我们需要一个真实对象来完成这些增删改查操作

```
//真实对象，完成增删改查操作的人
public class UserServiceImpl implements UserService{
    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("修改了一个用户");
    }

    public void query() {
        System.out.println("查询了一个用户");
    }
}
```

\3. 需求来了，现在我们需要增加一个日志功能，怎么实现！

- 思路1 ：在实现类上增加代码 【麻烦！】
- 思路2：使用代理来做，能够不改变原来的业务情况下，实现此功能就是最好的了！

\4. 设置一个代理类来处理日志！ 代理角色

```java
//代理角色，在这里面增加日志的实现
public class UserServiceProxy implements UserService{

    private UserServiceImpl userService;
//    public UserServiceProxy(){}     //但是spring不建议这样做 spring建议使用set方法

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
//    public UserServiceProxy(UserServiceImpl userService){
//        this.userService = userService;
//    }
    public void add() {
        log("add");
        userService.add();

    }

    public void delete() {
        log("delete");
        userService.delete();
    }

    public void update() {
        log("update");
        userService.update();
    }

    public void query() {
        log("query");
        userService.query();
    }

    //打印日志
    public void log(String msg){
        System.out.println("[Debug]使用了"+msg+"方法");
    }
}
```

\5. 测试访问类：

```java
public class Client {
    public static void main(String[] args) {
        //真实业务
        UserServiceImpl userService = new UserServiceImpl();
//        userServiceImpl.add();    //直接调用不使用代理
        //代理类
        UserServiceProxy proxy = new UserServiceProxy();
        //使用代理类实现日志功能！
        proxy.setUserService(userService);
        proxy.delete();

//        UserServiceProxy proxy = new UserServiceProxy(userServiceImpl);
//        proxy.delete();
    }
}
```

OK，到了现在代理模式大家应该都没有什么问题了，重点大家需要理解其中的思想；

==我们在不改变原来的代码的情况下，实现了对原有功能的增强，这是AOP中最核心的思想==

【聊聊AOP：纵向开发，横向开发】

![image-20200904163549595](https://i.loli.net/2020/09/04/m8OJvI4Mij2Scxw.png)

### 9.4、动态代理

- 动态代理的角色和静态代理的一样 .
- 动态代理的代理类是动态生成的 . 静态代理的代理类是我们提前写好的
- 动态代理分为两类 : 一类是基于接口动态代理 , 一类是基于类的动态代理
  - 基于接口的动态代理----JDK动态代理
  - 基于类的动态代理--cglib
  - 现在用的比较多的是 javasist 来生成动态代理 . 百度一下javasist
  - 我们这里使用JDK的原生代码来实现，其余的道理都是一样的！

JDK的动态代理需要了解两个类

核心 : InvocationHandler 和 Proxy ， 打开JDK帮助文档看看



【InvocationHandler：调用处理程序】

![image-20200904163747375](https://i.loli.net/2020/09/04/KSk4mVIpWhbMJG9.png)

```java
Object invoke(Object proxy, 方法 method, Object[] args)；
//参数
//proxy - 调用该方法的代理实例
//method -所述方法对应于调用代理实例上的接口方法的实例。 方法对象的声明类将是该方法声明的接口，它可以是代理类继承该方法的代理接口的超级接口。
//args -包含的方法调用传递代理实例的参数值的对象的阵列，或null如果接口方法没有参数。 原始类型的参数包含在适当的原始包装器类的实例中，例如java.lang.Integer或java.lang.Boolean。
```

【Proxy : 代理】

![image-20200904164110703](https://i.loli.net/2020/09/04/3kbOASLB8g6qnyM.png)



![image-20200904164143703](https://i.loli.net/2020/09/04/6ityS3mjxgKqCD8.png)

```java
//生成代理类
public Object getProxy(){
return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                              rent.getClass().getInterfaces(),this);
}
```

**代码实现**

抽象角色和真实角色和之前的一样！

Rent . java 即抽象角色

```java
//抽象角色：租房
public interface Rent {
    public void rent();
}
```

Host . java 即真实角色

```java
//真实角色: 房东，房东要出租房子
public class Host implements Rent {

    public void rent() {
        System.out.println("房东要出租房子");
    }
}
```

ProxyInvocationHandler. java 即代理角色

```java
//等会儿我们会用这个类，自动生成代理类！
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }
    //生成得到代理类 重点是第二个参数，获取要代理的抽象角色！之前都是一个角色，现在可以代理一类角色
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }
    // proxy : 代理类 method : 代理类的调用处理程序的方法对象.
    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现！
        seeHouse();
        Object result = method.invoke(rent, args);
        hetong();
        return result;
    }
    //看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }
    //合同
    public void hetong(){
        System.out.println("签租赁合同");
}
}
```

Client . java

```java
//租客
public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //代理实例的调用处理程序
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setRent(host);//将真实角色放置进去！
        Rent proxy = (Rent) pih.getProxy(); //动态生成对应的代理类！
        proxy.rent();


    }
}
```

核心：**一个动态代理 , 一般代理某一类业务 , 一个动态代理可以代理多个类，代理的是接口！**

### 9.5、深化理解

我们来使用动态代理实现代理我们后面写的UserService！

我们也可以编写一个通用的动态代理实现的类！所有的代理对象设置为Object即可！

```java
//等会儿我们会用这个类，自动生成代理类！
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    //生成得到代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    //处理代理实例，并返回结果
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log(method.getName());
        //动态代理的本质，就是使用反射机制实现！
        Object result = method.invoke(target, args);
        return result;
    }

    public void log(String methodName){
        System.out.println("执行了"+methodName+"方法");
    }
}
```

测试！

```java
public class Test {
    public static void main(String[] args) {
        //真实对象
        UserServiceImpl userService = new UserServiceImpl();
        //代理对象的调用处理程序
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        pih.setTarget(userService); //设置要代理的对象
        UserService proxy = (UserService)pih.getProxy(); //动态生成代理类！
        proxy.delete();
    }
}
```

【测试，增删改查，查看结果】

### 9.6、动态代理的好处

静态代理有的它都有，静态代理没有的，它也有！

- 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
- 公共的业务由代理来完成 . 实现了业务的分工 ,
- 公共业务发生扩展时变得更加集中和方便 .
- 一个动态代理 , 一般代理某一类业务
- 一个动态代理可以代理多个类，代理的是接口！

## 10、AOP

### 10.1 什么是AOP

AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现 程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的 一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使 得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

![image-20200905092629518](https://i.loli.net/2020/09/05/frwbpP1XHJzF496.png)

### 10.2 Aop在Spring中的作用

**提供声明式事务；允许用户自定义切面**

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要 关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 .... 
- 切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。 
- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。 
- 目标（Target）：被通知对象。 
- 代理（Proxy）：向目标对象应用通知之后创建的对象。 
- 切入点（PointCut）：切面通知 执行的 “地点”的定义。 
- 连接点（JointPoint）：与切入点匹配的执行点。

![image-20200905092848621](https://i.loli.net/2020/09/05/YGafIVZlh6smk7y.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice:

![image-20200905092859717](https://i.loli.net/2020/09/05/tvTBEbhR1zJFSqM.png)

即 Aop 在 不改变原有代码的情况下 , 去增加新的功能 .

### 10.3 使用Spring实现Aop

【重点】使用AOP织入，需要导入一个依赖包！

```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.4</version>
</dependency>
```

#### **第一种方式**

**通过 Spring API 实现**

首先编写我们的业务接口和实现类

```java
public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void query();
}
```

```java
public class UserServiceImpl implements UserService{
    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("修改了一个用户");
    }

    public void query() {
        System.out.println("查询了一个用户");
    }
}
```

然后去写我们的增强类 , 我们编写两个 , 一个前置增强 一个后置增强

```java
public class Log implements MethodBeforeAdvice {
    //method: 要执行的目标对象的方法
    //args:被调用的方法的参数
    //target:目标对象
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName()+"的"+method.getName()+"被执行了");
    }
}
```

```java
public class AfterLog implements AfterReturningAdvice {

    //returnValue 返回值
    //method被调用的方法
    //args 被调用的方法的对象的参数
    //target 被调用的目标对象
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行了"+method.getName()+"方法，返回结果为:" + returnValue);
    }
}
```

最后去spring的文件中注册 , 并实现aop切入实现 , 注意导入约束 .

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">


        <!--注册bean-->
        <bean id="userService" class="com.epfox.service.UserServiceImpl"/>
        <bean id="log" class="com.epfox.log.Log"/>
        <bean id="afterLog" class="com.epfox.log.AfterLog"/>



        <!--方式一：使用原生Spring API接口-->
        <!--配置aop:需要导入aop的约束-->
        <aop:config>
            <!--切入点 expression:表达式匹配要执行的方法 expression:要执行的位置-->
            <aop:pointcut id="pointcut" expression="execution(* com.epfox.service.UserServiceImpl.*(..))"/>
            <!--执行环绕增加！ advice-ref执行方法 . pointcut-ref切入点-->
            <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
            <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
        </aop:config>
</beans>
```

测试

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
```

Aop的重要性 : 很重要 . 一定要理解其中的思路 , 主要是思想的理解这一块 .

Spring的Aop就是将公共的业务 (日志 , 安全等) 和领域业务结合起来 , 当执行领域业务时 , 将会把公共业 务加进来 . 实现公共业务的重复利用 . 领域业务更纯粹 , 程序猿专注领域业务 , 其本质还是动态代理 .



#### 第二种方式

**自定义类来实现Aop**

目标业务类不变依旧是userServiceImpl

第一步 : 写我们自己的一个切入类

```java
public class DiyPointCut {
    public void before(){
        System.out.println("========方法执行前========");
    }
    public void after(){
        System.out.println("========方法执行后========");
    }
}
```

去spring中配置

```xml
<!--方式二：自定义类-->
         <bean id="diy" class="com.epfox.diy.DiyPointCut"/>

        <aop:config>
            <!--自定义切面，ref 要引用的类-->
            <aop:aspect ref="diy">
                <!--切入点-->
                <aop:pointcut id="point" expression="execution(* com.epfox.service.UserServiceImpl.*(..))"/>
                <!--通知-->
                <aop:before method="before" pointcut-ref="point"/>
                <aop:after method="after" pointcut-ref="point"/>
            </aop:aspect>
        </aop:config>
```

测试：

```java
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
```

#### 第三种方式

**使用注解实现**

第一步：编写一个注解实现的增强类

```java
//第三种方式 使用注解实现
@Aspect
public class AnnotationPointcut {

    @Before("execution(* com.epfox.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("========方法执行前========");
    }
    @After("execution(* com.epfox.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("========方法执行后========");
    }
    @Around("execution(* com.epfox.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");

//        Signature signature = jp.getSignature();//获得签名
//        System.out.println("signature:"+signature);
        //执行方法
        Object proceed = jp.proceed();

        System.out.println("环绕后");

//        System.out.println("proceed"+proceed);
    }
}
```

第二步：在Spring配置文件中，注册bean，并增加支持注解的配置

```xml
<!--方式三-->
<bean id="annotationPointcut" class="com.epfox.diy.AnnotationPointcut"/>
<!--开启注解支持-->
<aop:aspectj-autoproxy/>
```

aop:aspectj-autoproxy：说明

```xml
通过aop命名空间的<aop:aspectj-autoproxy />声明自动为spring容器中那些配置@aspectJ切面
的bean创建代理，织入切面。当然，spring 在内部依旧采用
AnnotationAwareAspectJAutoProxyCreator进行自动代理的创建工作，但具体实现的细节已经被
<aop:aspectj-autoproxy />隐藏起来了
<aop:aspectj-autoproxy />有一个proxy-target-class属性，默认为false，表示使用jdk动态
代理织入增强，当配为<aop:aspectj-autoproxy poxy-target-class="true"/>时，表示使用
CGLib动态代理技术织入增强。不过即使proxy-target-class设置为false，如果目标类没有声明接
口，则spring将自动使用CGLib动态代理。
```

## **11、整合Mybatis**

步骤：

\1. 导入相关jar包

1.junit         2.mybatis        3.mysql-connector-java       4.spring相关          5. aspectJ AOP 织入器

6.mybatis-spring整合包 【重点】         7.lombok          8. 配置Maven静态资源过滤问题！

```xml
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.20</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.10.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.1.10.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.4</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.2</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.10</version>
        </dependency>
    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```

\2. 编写配置文件

\3. 代码实现

### **回忆MyBatis**

**编写pojo实体类**

```java
@Data
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

实现mybatis的配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <package name="com.epfox.pojo"/>
    </typeAliases>

    <environments default="development">
    <environment id="development">
        <transactionManager type="JDBC"/>
        <dataSource type="POOLED">
            <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="url" value="jdbc:mysql://localhost:3306/mybatis ?serverTimezone=UTC&amp;characterEncoding=utf8&amp;useUnicode=true&amp;useSSL=false"/>
            <property name="username" value="root"/>
            <property name="password" value="123456"/>
        </dataSource>
    </environment>
    </environments>
    <mappers>
    <mapper class="com.epfox.mapper.UserMapper"/>
    </mappers>

</configuration>
```

UserDao接口编写

```java
public interface UserMapper {
    public List<User> selectUser();
}
```

接口对应的Mapper映射文件.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.epfox.mapper.UserMapper">

    <select id="selectUser" resultType="user">
        select * from user;
    </select>

</mapper>
```

测试类

```java
@Test
public void test() throws IOException {
    String resources = "mybatis-config.xml";
    InputStream in = Resources.getResourceAsStream(resources);
    SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
    SqlSession sqlSession = sessionFactory.openSession(true);
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    List<User> userList = mapper.selectUser();
    for (User user : userList) {
        System.out.println(user);
    }
}
```

### **MyBatis-Spring学习**

引入Spring之前需要了解mybatis-spring包中的一些重要类；

 http://www.mybatis.org/spring/zh/index.html

![image-20200905094544807](https://i.loli.net/2020/09/05/lekhSsA5xjBIMfO.png)

如果使用 Maven 作为构建工具，仅需要在 pom.xml 中加入以下代码即可：

```xml
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>2.0.2</version>
</dependency>
```

要和 Spring 一起使用 MyBatis，需要在 Spring 应用上下文中定义至少两样东西：一个 SqlSessionFactory 和至少一个数据映射器类。

在 MyBatis-Spring 中，可使用 SqlSessionFactoryBean 来创建 SqlSessionFactory 。 要配置 这个工厂 bean，只需要把下面代码放在 Spring 的 XML 配置文件中：

```xml
<!--配置SqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
</bean>
```

注意： SqlSessionFactory 需要一个 DataSource （数据源）。 这可以是任意的 DataSource ，只需要和配置其它 Spring 数据库连接一样配置它就可以了。

在基础的 MyBatis 用法中，是通过 SqlSessionFactoryBuilder 来创建 SqlSessionFactory 的。 而在 MyBatis-Spring 中，则使用 SqlSessionFactoryBean 来创建。

在 MyBatis 中，你可以使用 SqlSessionFactory 来创建 SqlSession 。一旦你获得一个 session 之后，你可以使用它来执行映射了的语句，提交或回滚连接，最后，当不再需要它的时候，你 可以关闭 session。

SqlSessionFactory 有一个唯一的必要属性：用于 JDBC 的 DataSource 。这可以是任意的 DataSource 对象，它的配置方法和其它 Spring 数据库连接是一样的。

一个常用的属性是 configLocation ，它用来指定 MyBatis 的 XML 配置文件路径。它在需要修改 MyBatis 的基础配置非常有用。通常，基础配置指的是  或  元素。

需要注意的是，这个配置文件并不需要是一个完整的 MyBatis 配置。确切地说，任何环境配置 （  ），数据源（  ）和 MyBatis 的事务管理器 （  ）都会被忽略。 SqlSessionFactoryBean 会创建它自有的 MyBatis 环境配置（ Environment ），并按要求设置自定义环境的值。

SqlSessionTemplate 是 MyBatis-Spring 的核心。作为 SqlSession 的一个实现，这意味着可 以使用它无缝代替你代码中已经在使用的 SqlSession 。

模板可以参与到 Spring 的事务管理中，并且由于其是线程安全的，可以供多个映射器类使用，你应该总 是用 SqlSessionTemplate 来替换 MyBatis 默认的 DefaultSqlSession 实现。在同一应用程 序中的不同类之间混杂使用可能会引起数据一致性的问题。

可以使用 SqlSessionFactory 作为构造方法的参数来创建 SqlSessionTemplate 对象。

```xml
<!--SqlSessionTemplate:就是我们使用的sqlSession-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能使用构造器注入sqlSessionFactory，因为它没有set方法-->
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>
```

现在，这个 bean 就可以直接注入到你的 Mapper bean 中了。你需要在你的 bean 中添加一个 SqlSession 属性，就像下面这样：

```java
public class UserMapperImpl implements UserMapper {

    //sqlSession不用我们自己创建了，Spring来管理
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

按下面这样，注入 SqlSessionTemplate ：

```xml
<bean id="userMapper" class="com.epfox.mapper.UserMapperImpl">
    <property name="sqlSession" ref="sqlSession"/>
</bean>
```

### **整合实现一**

\1. 引入Spring配置文件spring-dao.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
```

\2. 配置数据源替换mybaits的数据源

```xml
<!--配置数据源：数据源有非常多，可以使用第三方的，也可使使用Spring的-->
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mybatis ?serverTimezone=UTC&amp;characterEncoding=utf8&amp;useUnicode=true&amp;useSSL=false"/>
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
</bean>
```

\3. 配置SqlSessionFactory，关联MyBatis

```xml
<!--配置SqlSessionFactory-->
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!--关联Mybatis-->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
    <property name="mapperLocations"
              value="classpath:com/epfox/mapper/*.xml"/>
</bean>
```

\4. 注册sqlSessionTemplate，关联sqlSessionFactory；

```xml
<!--SqlSessionTemplate:就是我们使用的sqlSession-->
<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
    <!--只能使用构造器注入sqlSessionFactory，因为它没有set方法-->
    <constructor-arg index="0" ref="sqlSessionFactory"/>
</bean>
```

\5. 增加Mapper接口的实现类；私有化sqlSessionTemplate

```java
public class UserMapperImpl implements UserMapper {

    //sqlSession不用我们自己创建了，Spring来管理
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<User> selectUser() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUser();
    }
}
```

\6. 注册bean实现

```xml
<bean id="userMapper" class="com.epfox.mapper.UserMapperImpl">
    <property name="sqlSession" ref="sqlSession"/>
</bean>
```

结果成功输出！现在我们的Mybatis配置文件的状态！发现都可以被Spring整合！

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <typeAliases>
        <package name="com.epfox.pojo"/>
    </typeAliases>

</configuration>
```

### 整合实现二

mybatis-spring1.2.3版以上的才有这个 .

官方文档截图 :

dao继承Support类 , 直接利用 getSqlSession() 获得 , 然后直接注入SqlSessionFactory . 比起方式1 , 不 需要管理SqlSessionTemplate , 而且对事务的支持更加友好 . 可跟踪源码查看

![image-20200905095500646](https://i.loli.net/2020/09/05/gzHOws3vN1tfFRI.png)



测试：

\1. 将我们上面写的UserMapperImpl修改一下 新增一个UserMapperImpl2.java

```java
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {

//    public List<User> selectUser() {
//        SqlSession sqlSession = getSqlSession();
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//        return mapper.selectUser();
//    }
    public List<User> selectUser() {
        return getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
```

\2. 修改bean的配置

```xml
<bean id="userMapper2" class="com.epfox.mapper.UserMapperImpl2">
    <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
</bean>
```

\3. 测试

```java
@Test
public void test2() throws IOException {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper userMapper = context.getBean("userMapper2", UserMapper.class);
    List<User> userList = userMapper.selectUser();
    for (User user : userList) {
        System.out.println(user);
    }
}
```

总结 : 整合到spring中以后可以完全不要mybatis的配置文件，除了这些方式可以实现整合之外，我们还 可以使用注解来实现，这个等我们后面学习SpringBoot的时候还会测试整合！

## 12、声明式事务

### 12.1、回顾事务

- 事务在项目开发过程非常重要，涉及到数据的一致性的问题，不容马虎！
- 事务管理是企业级应用程序开发中必备技术，用来确保数据的完整性和一致性。

==事务就是把一系列的动作当成一个独立的工作单元，这些动作要么全部完成，要么全部不起作用。==

事务四个属性ACID

\1. 原子性（atomicity）

- 事务是原子性操作，由一系列动作组成，事务的原子性确保动作要么全部完成，要么完全不起 作用

\2. 一致性（consistency）

- 一旦所有事务动作完成，事务就要被提交。数据和资源处于一种满足业务规则的一致性状态中

\3. 隔离性（isolation）

- 可能多个事务会同时处理相同的数据，因此每个事务都应该与其他事务隔离开来，防止数据损 坏

\4. 持久性（durability）

- 事务一旦完成，无论系统发生什么错误，结果都不会受到影响。通常情况下，事务的结果被写 到持久化存储器中

### 12.2、测试

将上面的代码拷贝到一个新项目中

在之前的案例中，我们给userDao接口新增两个方法，删除和增加用户；

```java
public interface UserMapper {
    public List<User> selectUser();

    //添加一个用户
    public int addUser(User user);
    //删除一个用户
    public int deleteUser(int id);
}
```

mapper文件，我们故意把 deletes 写错，测试！

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.epfox.mapper.UserMapper">

    <select id="selectUser" resultType="user">
        select * from user;
    </select>
    
    <insert id="addUser" parameterType="user">
        insert into user(id,name,pwd) values (#{id},#{name},#{pwd});
    </insert>
    
    <delete id="deleteUser" parameterType="int">
        deletes from user where id = #{id};
    </delete>
    


</mapper>
```

编写接口的实现类，在实现类中，我们去操作一波

```java
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {

    public List<User> selectUser() {
        User user = new User(5, "小王", "123213213");
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        mapper.addUser(user);
        mapper.deleteUser(4);
        return mapper.selectUser();
    }

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
```

测试

```java
@Test
public void test() throws IOException {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
    List<User> userList = userMapper.selectUser();
    for (User user : userList) {
        System.out.println(user);
    }
}
```

报错：sql异常，delete写错了

结果 ：插入成功！

没有进行事务的管理；我们想让他们都成功才成功，有一个失败，就都失败，我们就应该需要事务！

以前我们都需要自己手动管理事务，十分麻烦！

但是Spring给我们提供了事务管理，我们只需要配置即可；

### 12.3、Spring中的事务管理

Spring在不同的事务管理API之上定义了一个抽象层，使得开发人员不必了解底层的事务管理API就可以 使用Spring的事务管理机制。Spring支持编程式事务管理和声明式的事务管理。

**编程式事务管理**

- 将事务管理代码嵌到业务方法中来控制事务的提交和回滚
- 缺点：必须在每个事务操作业务逻辑中包含额外的事务管理代码

**声明式事务管理**

- 一般情况下比编程式事务好用。
- 将事务管理代码从业务方法中分离出来，以声明的方式来实现事务管理。
- 将事务管理作为横切关注点，通过aop方法模块化。Spring中通过Spring AOP框架支持声明式事务 管理。

**使用Spring管理事务，注意头文件的约束导入 : tx**

```XML
xmlns:tx="http://www.springframework.org/schema/tx"

http://www.springframework.org/schema/tx
http://www.springframework.org/schema/tx/spring-tx.xsd">
```

**事务管理器**

- 无论使用Spring的哪种事务管理策略（编程式或者声明式）事务管理器都是必须的。
- 就是 Spring的核心事务管理抽象，管理封装了一组独立于技术的方法。

**JDBC事务**

```xml
<!--JDBC事务-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource" />
</bean>
```

**配置好事务管理器后我们需要去配置事务的通知**

```xml
<!--配置事务通知-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <!--配置哪些方法使用什么样的事务,配置事务的传播特性-->
        <tx:method name="add" propagation="REQUIRED"/>
        <tx:method name="delete" propagation="REQUIRED"/>
        <tx:method name="update" propagation="REQUIRED"/>
        <tx:method name="search*" propagation="REQUIRED"/>
        <tx:method name="get" read-only="true"/>
        <tx:method name="*" propagation="REQUIRED"/>
    </tx:attributes>
</tx:advice>
```

**spring事务传播特性：**

事务传播行为就是多个事务方法相互调用时，事务如何在这些方法间传播。spring支持7种事务传播行 为：

- propagation_requierd：如果当前没有事务，就新建一个事务，如果已存在一个事务中，加入到这 个事务中，这是最常见的选择。 
- propagation_supports：支持当前事务，如果没有当前事务，就以非事务方法执行。 
- propagation_mandatory：使用当前事务，如果没有当前事务，就抛出异常。 
- propagation_required_new：新建事务，如果当前存在事务，把当前事务挂起。 
- propagation_not_supported：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。 
- propagation_never：以非事务方式执行操作，如果当前事务存在则抛出异常。 
- propagation_nested：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 propagation_required类似的操作

Spring 默认的事务传播行为是 PROPAGATION_REQUIRED，它适合于绝大多数的情况。

假设 ServiveX#methodX() 都工作在事务环境下（即都被 Spring 事务增强了），假设程序中存在如下的 调用链：Service1#method1()->Service2#method2()->Service3#method3()，那么这 3 个服务类的 3 个方法通过 Spring 的事务传播机制都工作在同一个事务中。

就好比，我们刚才的几个方法存在调用，所以会被放在一组事务当中！

**配置AOP**

导入aop的头文件！

```xml
<!--配置aop织入事务-->
<aop:config>
    <aop:pointcut id="txPointcut" expression="execution(* com.epfox.mapper.*.*(..))"/>
    <aop:advisor advice-ref="txAdvice" pointcut-ref="txPointcut"/>
</aop:config>
```

**进行测试**

删掉刚才插入的数据，再次测试！

```java
@Test
public void test() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper = context.getBean("userMapper", UserMapper.class);
        List<User> userList = userMapper.selectUser();
        for (User user : userList) {
            System.out.println(user);
        }
    }
```

### 思考问题？

为什么需要配置事务？

- 如果不配置，就需要我们手动提交控制事务； 
- 事务在项目开发过程非常重要，涉及到数据的一致性的问题，不容马虎！
