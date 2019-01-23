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



