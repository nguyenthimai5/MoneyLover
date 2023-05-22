package d2.moneylover.config;

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
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
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
}
