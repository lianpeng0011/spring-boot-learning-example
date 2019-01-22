# spring-boot-learning-security



### Security Configure

- `WebMvcConfigurer`
- `AbstractSecurityWebApplicationInitializer`
  - Spring  和Spring MVC 的区别

### Security 请求授权

```java
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .authorizeRequests()
            .antMatchers( "" )
            .authenticated()
            .and()
            .formLogin() //允许用户使用基于表单的登陆方式进行验证
            .loginPage( "" ) //自定义登陆页面
            .httpBasic() //允许用户使用HTTP身份验证进行验证
            .logout() // 使用logout进行登出

}
```





