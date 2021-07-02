// package de.hsrm.mi.web.projekt.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter{
//     @Autowired FotoUserDetailsService fDetailsService;

//     @Bean PasswordEncoder passwordEncoder(){
//         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder authmanagerbuilder) throws Exception{
//         PasswordEncoder pwenc = passwordEncoder();

//         authmanagerbuilder.inMemoryAuthentication()
//             .withUser("friedfert")
//             .password(pwenc.encode("dingdong")) 
//             .roles("GUCKER")
//         .and() // nächster Eintrag
//             .withUser("joghurta")
//             .password(pwenc.encode("geheim123"))
//             .roles("PHOTOGRAPH");

//         authmanagerbuilder
//             .userDetailsService(fDetailsService)
//             .passwordEncoder(passwordEncoder());
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception{
//         http.authorizeRequests()
//             .antMatchers("/api").permitAll()
//             .antMatchers("/messagebroker").permitAll()
//             .antMatchers(HttpMethod.GET, "/foto/*").permitAll()
//             .antMatchers(HttpMethod.POST, "/foto").hasRole("PHOTOGRAPH")
//             .antMatchers(HttpMethod.GET, "/foto/*/del").hasRole("PHOTOGRAPH")
//             .antMatchers("/h2-console").permitAll()
//             .antMatchers("/h2-console/*").permitAll()
//             .antMatchers("/*").authenticated()
//         .and()
//             .formLogin()
//             .defaultSuccessUrl("/foto")
//             .permitAll();

//         //erlaubt DELETE operation für rest api
//         http
//             .csrf()
//             .ignoringAntMatchers("/api")
//             .ignoringAntMatchers("/h2-console")
//             .ignoringAntMatchers("/h2-console/*");

//         http.headers().frameOptions().disable();
  
//     }


    
// }
