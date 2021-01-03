package com.ksm.wstbackoffice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Customisation for the security configuration of the @{@link ResourceServerConfigurerAdapter}.
 */
@Configuration
public class CustomResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // we specify resourceId in the wst-auth-server per user in the table oauth_client_details -> column resource_ids
        // if you want here to ignore this filter - just set null
        // spring OAuth2AuthenticationManager(line 89) performs this verification
        // default value is set in the spring ResourceServerSecurityConfigurer(line 76)
        resources
                .resourceId("backoffice");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/index.html","/swagger-resources/**", "/**/api-docs/**").permitAll()
                .anyRequest().authenticated();
    }
}
