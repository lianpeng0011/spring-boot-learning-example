# Spring boot 系列  JPA



### Java Persistence API

#### JPI1.0

 2016年JPI1.0最终规范发布（JSR-220）。整合了查询语句Query 和对象关系映射（ORM）元数据定义

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
  - `@Id`
- 复合主键
  - `@EmbeddedId`
  - `@IdClass`

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

#### 主键生成策略 GeneratedValue



