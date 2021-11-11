package lu.davidson.market.configuration;

import lu.davidson.market.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class JWTConnectorSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter{
        @Value("${jwt.secret-key}")
        private String secretKey;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/tools/**")
                    .cors().and()
                    .csrf().disable()
                    .authorizeRequests().anyRequest().authenticated().and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        private JwtFilter jwtFilter() {
            return new JwtFilter(secretKey);
        }

    }


}
