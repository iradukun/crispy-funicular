package com.hcm.backend_service.v2.config;

import com.hcm.backend_service.v2.enums.ERole;
import com.hcm.backend_service.v2.security.CustomUserDetailsService;
import com.hcm.backend_service.v2.security.JwtAuthenticationEntryPoint;
import com.hcm.backend_service.v2.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userService;


    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


    @Bean()
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers(
                        "/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg", "/**/*.jpg", "/**/*.html",
                        "/**/*.css", "/**/*.js").permitAll()
                .antMatchers(
                        "/api/v2/auth/**", "/api/v2/users/**").permitAll()
                .antMatchers( "/v2/api-docs",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-resources/**",
                        "/swagger/**",
                        "/swagger-ui/**",
                        "/webjars/**").permitAll()
//                .antMatchers("/api/v2/schedules").hasAnyRole(String.valueOf(ERole.SCHEDULE_MANAGER))
//                .antMatchers("/api/v2/appointments/")
                //roles based auth
                //authentication
                .antMatchers(HttpMethod.POST, "/api/v2/auth/**").permitAll()
                //appointments
                .antMatchers(HttpMethod.POST, "/api/v2/appointments/**").hasRole(String.valueOf(ERole.APPOINTMENT_MANAGER))
                .antMatchers(HttpMethod.PUT, "/api/v2/appointments/**").hasRole(String.valueOf(ERole.PATIENT))
                .antMatchers(HttpMethod.GET, "/api/v2/appointments/hospital/**").authenticated()
                .antMatchers(HttpMethod.PATCH, "/api/v2/appointments/change-appointment-schedule").hasRole(String.valueOf(ERole.APPOINTMENT_MANAGER))
                .antMatchers(HttpMethod.GET, "/api/v2/appointments/hospital/**/**").hasAnyRole(
                        String.valueOf(ERole.APPOINTMENT_MANAGER),
                        String.valueOf(ERole.SCHEDULE_MANAGER),
                        String.valueOf(ERole.HOSPITAL_ADMIN),
                        String.valueOf(ERole.DOCTOR),
                        String.valueOf(ERole.HOSPITAL_DIRECTOR),
                        String.valueOf(ERole.GROUP_ADMIN),
                        String.valueOf(ERole.SUPER_ADMIN)
                )
                .antMatchers(HttpMethod.GET, "/api/v2/appointments/schedule/**").hasAnyRole(
                        String.valueOf(ERole.APPOINTMENT_MANAGER),
                        String.valueOf(ERole.SCHEDULE_MANAGER)
                )
                .antMatchers(
                        HttpMethod.GET,
                        "/api/v2/appointments/hospital/**/doctor/**"
                )
                .hasAnyRole(
                        String.valueOf(ERole.APPOINTMENT_MANAGER),
                        String.valueOf(ERole.DOCTOR),
                        String.valueOf(ERole.HOSPITAL_DIRECTOR),
                        String.valueOf(ERole.HOSPITAL_ADMIN)
                )
                .antMatchers(
                        HttpMethod.POST,
                        "/api/v2/appointments/send-appointment"
                ).hasRole(String.valueOf(ERole.DOCTOR))
                .antMatchers(HttpMethod.PATCH, "/api/v2/appointments/schedule/**/edit/**")
                .hasRole(String.valueOf(ERole.APPOINTMENT_MANAGER))

                //appointment-manager
                .antMatchers(HttpMethod.POST, "/api/v2/appointment-manager")
                .hasAnyRole(
                        String.valueOf(ERole.HOSPITAL_ADMIN),
                        String.valueOf(ERole.GROUP_ADMIN)
                )
                //doctor
                .antMatchers(HttpMethod.POST, "/api/v2/doctors")
                .hasAnyRole(
                        String.valueOf(ERole.GROUP_ADMIN),
                        String.valueOf(ERole.HOSPITAL_ADMIN)
                )
                .antMatchers(HttpMethod.DELETE, "/api/v2/doctors/**")
                .hasAnyRole(
                        String.valueOf(ERole.GROUP_ADMIN),
                        String.valueOf(ERole.HOSPITAL_ADMIN)
                )
                //doctor-hospital-services
                .antMatchers(HttpMethod.GET, "/api/v2/doctor-hospital-services")
                .authenticated()

                //group-admin
                .antMatchers(HttpMethod.POST, "/api/v2/group-admin/**")
                .hasRole(String.valueOf(ERole.SUPER_ADMIN))

                //groups
//                .antMatchers(HttpMethod.GET, "/api/v2/groups")
//                .access("hasRole('SUPER_ADMIN')")
                .antMatchers(HttpMethod.POST, "/api/v2/groups")
                .hasRole(String.valueOf(ERole.SUPER_ADMIN))
                .antMatchers(HttpMethod.PUT, "/api/v2/groups/**")
                .hasRole(String.valueOf(ERole.SUPER_ADMIN))

                //hospital_category
                .antMatchers(HttpMethod.GET, "/api/v2/hospital_category")
                .authenticated()
                .antMatchers(HttpMethod.POST, "/api/v2/hospital_category")
                .hasAnyRole(String.valueOf(ERole.SUPER_ADMIN), String.valueOf(ERole.GROUP_ADMIN))

                //hospitals
                .antMatchers(HttpMethod.POST, "/api/v2/hospitals")
                .hasRole(String.valueOf(ERole.GROUP_ADMIN))
                .antMatchers(HttpMethod.GET, "/api/v2/hospitals").authenticated()
                .antMatchers(HttpMethod.GET, "/api/v2/hospitals/group/**").authenticated()

                //role
                .antMatchers(HttpMethod.POST, "/api/v2/roles")
                .hasRole(String.valueOf(ERole.SUPER_ADMIN))

                //schedules
                .antMatchers(HttpMethod.POST, "/api/v2/schedules")
                .hasRole(String.valueOf(ERole.SCHEDULE_MANAGER))
                .antMatchers(HttpMethod.GET, "/api/v2/schedules")
                .authenticated()
                .antMatchers(HttpMethod.GET, "/api/v2/schedules/doctor/**/hospital/**")
                .authenticated()
                .antMatchers(HttpMethod.PUT, "/api/v2/schedules/**")
                .hasRole(String.valueOf(ERole.SCHEDULE_MANAGER))
                .antMatchers(HttpMethod.DELETE, "/api/v2/schedules/**/**")
                .hasRole(String.valueOf(ERole.SCHEDULE_MANAGER))
                .antMatchers(HttpMethod.GET, "/api/v2/schedules/hospital/**")
                .authenticated()

                //schedule-manager
                .antMatchers(HttpMethod.POST, "/api/v2/schedule-manager")
                .hasRole(String.valueOf(ERole.HOSPITAL_ADMIN))

                //services
                .antMatchers(HttpMethod.GET, "/api/v2/services").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v2/services")
                .hasAnyRole(String.valueOf(ERole.SUPER_ADMIN), String.valueOf(ERole.GROUP_ADMIN), String.valueOf(ERole.HOSPITAL_ADMIN))
                .antMatchers(HttpMethod.GET, "/api/v2/services/hospital/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/v2/services/hospital/**").hasAnyRole(
                        String.valueOf(ERole.GROUP_ADMIN),
                        String.valueOf(ERole.HOSPITAL_ADMIN)
                )
                .antMatchers(
                        HttpMethod.POST,
                        "/api/v2/services/group/add-service"
                ).hasAnyRole(
                        String.valueOf(ERole.GROUP_ADMIN)
                )
                .antMatchers(
                        HttpMethod.POST,
                        "/api/v2/super-admin"
                ).permitAll()
                //current user
                .antMatchers(
                        HttpMethod.GET,
                        "/api/v2/users/current-user"
                ).authenticated()
                .antMatchers(HttpMethod.GET, "/hcm-apps-backend-service/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
