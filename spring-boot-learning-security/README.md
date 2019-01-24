# spring-boot-learning-security



### Security Configure

- `WebMvcConfigurer`
- `AbstractSecurityWebApplicationInitializer`
  - Spring  和Spring MVC 的区别

### Security 请求授权

```java
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests() //该方法有多个字节点，每个匹配器按照其声明顺序进行匹配
            .antMatchers( "/user/**","/org","" ) // 配置多个认证的URL，例如以/user 开头或者等于/org 等
            .authenticated() //设置前面匹配的url进行验证
            .and()
            .antMatchers("/home")
            .permitAll() //设置不进行验证的url
            .formLogin() //允许用户使用基于表单的登陆方式进行验证
            .loginPage( "" ) //自定义登陆页面
            .httpBasic() //允许用户使用HTTP身份验证进行验证
            .logout() // 访问/logout进行登出。如果是用CSRF保护时(默认)，则该请求必须是post
            .logoutUrl("/my/logout")//自定义登出路径
            .logoutSuccessUrl("/")//设置成功登出时访问的路径
            .logoutSuccessHandler() //设置成功登出后处理类，如果设置该类则忽略`logoutSuccessUrl`
            .invalidateHttpSession(true) // 成功退出时，是否使Session失效，默认true
            .addLogoutHandler() //添加最后一个处理Handler
            
}
```

处理注销

- 使HTTP会话无效
- 清除已配置任何RememberMe身份信息
- 清除`SecurityContextHolder`
- 重定向`/login?logout`

### 配置多个HttpSecurity

```java
@EnableWebSecurity
public class MultiHttpSecurityConfig {
    @Bean                                                             
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();        manager.createUser(users.username("user").password("password").roles("USER").build());    manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }

    @Configuration
    @Order(1)                                                        
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                .antMatcher("/api/**")                               
                .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                    .and()
                .httpBasic();
        }
    }

    @Configuration                                                   
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin();
        }
    }
}
```

> @Order 默认最后初始化

#### 自定义登陆

- 继承`org.springframework.security.core.userdetails.User`扩展自己用户信息
- 通过实现`UserDetailsService.loadUserByUsername`业务来进行自定义登陆
- 在`AuthenticationManagerBuilder`添加自定义登陆。2.0以上版本需要添加`PasswordEncoder`

#### CSRF

CSRF跨站请求伪造（Cross-site Request forgery）,也称one-click attack 或者Session riding，通常缩写为CSRF和XSRF，是一种挟持用户在当前已登录的Web应用程序上执行非本意的操作的攻击方式。

- CSRF Token

  服务器端为客户端生成的令牌，该令牌将用于请求合法性校验，一般通过请求头或者请求参数传递

  - 请求参数   `_csrf: _csrf.token`
  - 请求头 `X-CSRF-TOKEN:_csrf.token`

- CSRF Token Repository

  服务器端组件，用来创建和存储CSRF Token信息，Security 提供了Cookie 和Session 两种方式

- CSRF Matcher

  用于判断请求是否需要CSRF 校验

#### CSRF 攻防逻辑

1. 利用CSRF Token 仓库 存储和创建Http 请求所需要的Token
2. 通过CSRF Token 校验请求匹配器， 来判断当前强求是否需要CSRF Token 校验。如果需要执行第3步
3. 从请求头或者请求参数中获取CSRF Token 
   1. 如果没有获取到Token，将转向错误页面，相应的状态码设置为403
   2. 如果CSRF Token 的值获取到时，执行下一步
4. 从CSRF Token 仓库中取出Token 和客户端传递的Token进行比较。
   1. 如果相等，验证成功，继续向下执行
   2. 校验失败，将会调入错误的页面，状态码返回403

#### CSRF Repository

`org.springframework.security.web.csrf.CsrfTokenRepository`

- Cookie
  - 实现`org.springframework.security.web.csrf.CookieCsrfTokenRepository`
  - CSRF Token 存储： 客户端、Web浏览器、Web浏览器Cookie 
  - 有效时间： Web浏览器会话期间
  - 特别注意： Cookie方式安全系数相对较低
- Session
  - 实现`org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository`
  - CSRF Token 存储： 服务端、HttpSession （Servlet 容器）
  - 有效时间：HttpSession 最大的活动间隔（setMaxInactiveInterval(int)）
  - 特别注意：在分布式场景下Servlet 需要支持Session 复制

#### CSP

Content Security Policy内容安全策略。Spring Security 默认不开启。

- `ContentSecurityPolicy("")`来添加容许访问的非同源资源。
- HTTP请求头会增加`X-Content-Security-policy`

#### X-Frame-Options header

使用`X-Frame-Options`来方式`iframe`劫持问题

- `frameOptions`开启Frame验证
- `XFrameOptionsMode`验证类型
  - `DENY`默认 拒绝当前页面使用iframe
  - `SAMEORIGIN` 允许同源 使用
  - `ALLOW-FROM` origin为允许frame加载的页面地址。

#### XSS

默认请求开启，并指示浏览器在检查到XSS攻击时所要执行的操作。在Http中填入如下标头

```http
X-XSS-Protection: 1; mode=block
```

#### Security Role 验证方法权限

- `EnableGlobalMethodSecurity(secured = true)` 开启`@Secured`验证
- 在方法上标注`@Secured（"RULE_USER"）` 并且设置方法的访问权限