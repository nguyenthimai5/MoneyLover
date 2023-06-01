package d2.moneylover.config;

import d2.moneylover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable() // tắt cấu hình form login của Spring Security
                .csrf().disable()
                .formLogin() // sử dụng form login của riêng bạn
                .loginPage("/login")
                .loginProcessingUrl("/loginn")
                .defaultSuccessUrl("/home")
                .permitAll()
                .failureHandler((request, response, exception) -> {
                    // Xử lý thông báo lỗi
                    String errorMessage = "Tài khoản hoặc mật khẩu của bạn không chính xác";
                    request.getSession().setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("/login");
                })
                .and() // Cho phép người dùng xác thực bằng form log
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logoutSuccess=true")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403");
    }

/*    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }*/
}


