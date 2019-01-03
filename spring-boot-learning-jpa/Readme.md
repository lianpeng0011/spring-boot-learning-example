# Spring boot 系列  JPA



### Java Persistence API

#### JPI1.0

 2006年JPI1.0最终规范发布（JSR-220）。整合了查询语句Query 和对象关系映射（ORM）元数据定义

#### JPI2.0

  2009年JPI2.0最终规范发布（JSR-317）。在1.0的基础上添加了Criteria查询、元数据API以及校验支持。



---

### 实体类 （Entity Class)

- 实体类必须使用`@Entity`或XML描述
- 实体类必须提供默认的构造方法，并且构造方法必须是`public` 或`protected`
- 实体类必须为顶级类，不是为`Enum`或`interface`
- 实体类不能使用`final`
- 实体类支持继承、多态关联以及多态查询

#### 字段和属性的访问

- 默认访问类型
  - 非`@transient`的字段
  - 非`@transient`的属性
- 显示访问类型
  - 注解类型  如：实体类、映射超类和嵌套类
  - 注解 
    - `@Access(AccessType.FIELD)`字段
    - `@Access(AccessTYPE.PROPERTY)`属性

#### 实体主键（Primary Key）

每个实体必须存在主键，主键必须定义在实体类

- 简单主键

  - `@Id`  指定主键列
  - @GeneratedValue 用于标注主键生成策略，通过strategy来指定生成策略，默认为GenerationType .AUTO。
  - GenerationType 
    - AUTO：JAP默认选择合适的策略，默认值
    - TABLE：通过表产生主见，框架借由表模拟序列生成主键，使用该策略可以使数据库更方便迁移
    - IDENTITY：采用数据库ID自增长的方式来进行主键自增，Oracle不支持该策略
    - SEQUENCE：通过序列产生主键，通过`@SequenceGenerator`注解指定序列名，Mysql不支持这种方式。

  > `@GeneratedValue` 策略自定义：
  >
  > `@GeneratedValue(strategy="uuid")`
  >
  > `@GenericGenerator(name="uuid", strategy="package.classname")` 
  >
  > 自定义策略类必须实现IdentifierGenerator接口
- 复合主键
  - `@EmbeddedId` 嵌套式主键
  - `@IdClass` 复合主键

#### 实体关系

实体关系可能一对一、一对多、多对一或多对多，这些关系是多态的，可以是单向或者双向。

- 注解表达方式
  - OneToOne
  - OneToMany
  - ManyToOne
  - ManyToMany
- XML表达方式

#### 实体双向关系

实体双向关系是指实体之间不仅存在拥有方（owning），也存在倒转方（inverse）。主方决定数据入库操作。

- 规则
  - 倒转必须通过`@OneToOne`、`@OneToMany`或者`@ManyToMany`中的`mappedBy`属性方法关联到拥有端的字段或者属性中。
  - `OneToMany`、`@ManyToOne`双向关联中，多方必须是主要方，因此`@ManyToOne`不能指定`MappedBy`属性方法
  - 双向一对一关系中，主方相当于包含外键的一方
  - 双向多对多关系中，任何一方可能拥有主方

#### 实体继承（Inheritance）

实体可以继承其他实体。实体之间支持继承、多态关联、多态查询。

- 继承方式：

  - 继承抽象实体类

    - @Inheritance  使用`strategy`设置继承策略，策略类型

      - TABLE_REP_CLASS 每个实体类生成一张独立表，子类生成表中包含父类属性
      - JONED  每个实体类生成不同的表，表之间通过外键进行关联
      - SINGLE_TABLE 子类和父类公用一张表，创建一个新的字段用于区分对象的类型。

      > @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
      >
      > @DiscriminationCloumn(name="TYPE",discriminationType=DiscriminationType.STRING)
      >
      > @DiscriminatorValue("type_value")
      >
      > 使用`@DiscriminationCloumn`注解在表中新添加类型列，用于区分数据入库的实体类型，`discriminationType`属性用于确定该字段的类型。如：STRING、INTEGER、CHAR
      >
      > 使用`DiscrimiatorValue`注解设置该类对应的类型值

  - 继承已映射父类型

    - @MappedSuperclass 公用属性抽象成一个父类，父类不会生成表，父类的属性会映射到子类的表中
    - @AssociationOverride 当一个实体类中引用两个相同的对象，就会发生冲突，使用该注解决绝冲突

    ```java
    @Entity(name = "Book")
    @AttributeOverrides({
    	@AttributeOverride(
    		name = "ebookPublisher.name",
    		column = @Column(name = "ebook_publisher_name")
    	),
    	@AttributeOverride(
    		name = "paperBackPublisher.name",
    		column = @Column(name = "paper_back_publisher_name")
    	)
    })
    @AssociationOverrides({
    	@AssociationOverride(
    		name = "ebookPublisher.country",
    		joinColumns = @JoinColumn(name = "ebook_publisher_country_id")
    	),
    	@AssociationOverride(
    		name = "paperBackPublisher.country",
    		joinColumns = @JoinColumn(name = "paper_back_publisher_country_id")
    	)
    })
    public static class Book {
    
    	@Id
    	@GeneratedValue
    	private Long id;
    
    	private String title;
    
    	private String author;
    
    	private Publisher ebookPublisher;
    
    	private Publisher paperBackPublisher;
    
    	//Getters and setters are omitted for brevity
    }
    ```

  - 继承非实体类型

#### 实体类操作(Operations)

- 实体类管理器
  - EntityManager

#### 实体类生命周期(Life Cycle)

- 创建
- 持久化
- 移除
- 同步到数据库
- 刷新实例
- 淘汰

#### 实现监听器和回调方法

- 实体监听器
  - @EntityListeners
- 回调方法
  - prePersist
  - postPersist
  - preRemove
  - postRemove
  - preUpdate
  - postUpdate
  - postLoad

#### Spring Data API

- 缓存（caching）
- 查询API（Query API）
- Criteria API
- 元模型API（Metamodel API）

#### Spring Data Repository

- 核心接口
  - Repository
  - CrudRepository
  - JPARepository
  - @NoRepositoryBean 自定义实现扩展
  - @RepositoryDefinition
- 激活JPA Repository
  - @EnableJpaRepositories 开启jpa存储库扫描

#### null Handling of Repository Methods

- 可空性注释

  - @NonNullApi 包级别注释  返回值和参数是不接受null
  - @NonNull  参数或者返回值必须不等于null   参数、方法和Field
  - @Nullable  参数和返回值可以为null   参数、方法和Field

  > 注解匹配  参数 > 方法 >  package

#### 查询创建

- 查询构建器识别语法 find...By 、read...By、query...By、count...By、 get...By。by作为分隔符后面添加条件条件可以使用`And`或`Or`进行连接，也可以使用`OrderBy... ASC ` 来进行排序

- 表达式除`And` 和`Or`之外还支持`Between`、`LessThan`、`GreaterThan`和`Like`

- 表达式支持`IgnoreCase` 用于忽略 String 条件的大小写

- 驼峰风格`findByAddressZipCode`  解析器首先会在对象中查找`AddressZipCode`属性，如果没有，就采用驼峰规则进行解析。驼峰解析从右开始进行。`Object.address.zipCode`解析完成后进行调用

  > 注: 也可以通过`_`方式进行快速解析，但是不符合Java命名约定，不推荐使用

- 特殊参数处理 

  - `org.springframework.data.domain.Pageable` 分页 该操作可能很重。
  - Slice  分页时，返回下个可用页的信息
  - `org.springframework.data.domain.Sort`  排序

- Stream Query Results   需要注意的是在使用Stream后需要手动调用`close()`或者`try-with-resources`。并不是所有的Spring Data 模块都支持Stream<T>
  - `find...By...AndStream`
  - `StreamAllPaged(Pageable pageable)`

- Async Query Results    需要在方法上添加@Async注解进行标注

  ```java
  @Async
  Future<User> findByFirstname(String firstname);
  ```

  - `java.util.concurrent.Future` 返回值
  - `java.util.concurrent.CompletableFuture` 返回值
  - `org.springframework.util.concurrent.ListenableFuture` 返回值

#### XML 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans:beans xmlns:beans="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xmlns="http://www.springframework.org/schema/data/jpa"
  xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/data/jpa
    http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

  <repositories base-package="com.acme.repositories" >
      <context:exclude-filter type="regex" expression=".*SomeRepository" />
      <context:include-filter type="regex" expression=".*SomeRepository" />
    </repositories>
</beans:beans>
```

Java 配置

使用`@Enable${store}Repositories`进行激活

```java
@Configuration
@EnableJpaRepositories("com.acme.repositories")
class ApplicationConfiguration {
  @Bean
  EntityManagerFactory entityManagerFactory() {
    // …
  }
}
```

#### 聚合跟发布时间 

- `@DomainEvents`
- `@AfterDomianEventPublication`

