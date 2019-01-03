# spring-boot-learning-Validation

- Bean Validation
- validation注解
- 自定义validation
- 自定义描述
- 验证方式

#### Bean Validation

版本规范

- 2009.10.12 Bean Validation 1.0 JSR-303
- 2013.04.10 Bean validation 1.1 JSR-349
- 2017.06.21 Bean validation 2.0 JSR-380

> Bean validation2.0 开始对JDK1.8的日期类型开始支持。

常用注解

- `@AssertFalse`   被注释的元素必须为false
- `@AssertTrue` 被注释的元素必须为true
- `@DecimalMax` 被指定的元素必须为一个数字，并且值小于等于指定的值
- `@DecimlMin`  被指定的元素必须为一个数字，并且值大于等于指定的值
- `@Digits(integer=,fraction=)` 验证元素是否符合格式的数字，integer指定整数的精度，fraction指定小数的精度
- `@Email` 被指定元素是否是email地址，可以为null
- `@Future` 被指定元素必须是一个将来的日期
- `@FutureOrPersent`  被指定元素必须不是一个过去的时间，可以是当前时间或者是未来时间
- `@Max` 被指定的元素必须为一个数字，并且值小于等于指定的值
- `@Min` 被指定的元素必须为一个数字，并且值大于等于指定的值
- `@Negative` 被指定的元素必须是一个负数 ，即小于0的数字
- `@NegativeOrZero` 被指定的元素必须是一个负数或者0
- `@NotBlank`  被注释元素不能为null
- `@NotEmpty` 被注释的元素必须不为null
- `@NotNull` 被注释的元素必须不为null

> `@NotNull`、`@NotEmpty`和`@NotNlank`的区别
>
> `@NotNull` 元素不能为null 如果该元素是集合或者数组 size或length 可以等于0
>
> `@NotEmpty` 对象不是是null 且集合或者数组的 size 大于0
>
> `@NotBlank` String 对象首位去空后长度大于0

- `@Null`  被注释的元素必须为null
- `@Past` 过去的时间
- `@PastOrPresent` 过去或者当前时间
- `@Pattern` 正则表达式验证
- `@Positive` 元素必须是一个正数，即大于0的数字
- `@PositiveOrZero` 元素必须是一个正数或者0
- `@Size`  被注释元素必须符合范围

#### 自定义Validation

- 定义注解
- 实现`ConstraintValidator`接口

- `@Constraint(validatedBy = { })`指定验证注解实现类

#### 自定义验证描述

- 在Resources目录下建立 `ValidationMessages_zh_CN.properties` 该文件可以根据文件后缀名称实现国际化
- 定义提示信息，中文使用`native2ascii`命令进行编码转换，将中文转换成ascii码
- 指定提示`@NUll(message={xxxx})` xxxx代表properties文件的key

#### 验证方式

- 参数校验
  - 在参数前面添加 `@valid`
- 嵌套校验
  - 在对象属性上添加`@valid`