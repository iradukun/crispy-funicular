package com.hcm.backend_service.v2.config;

import com.hcm.backend_service.v2.exceptions.ResourceNotFoundException;
import com.hcm.backend_service.v2.models.User;
import com.hcm.backend_service.v2.repositories.IUserRepository;
import com.hcm.backend_service.v2.serviceImpls.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class AuditConfig {
    @Bean
    public AuditorAware<UUID> auditProvider() {
        return new SpringSecurityAuditAwareImpl();
    }
}

class SpringSecurityAuditAwareImpl implements AuditorAware<UUID> {

    @Qualifier(
            value = "IUserRepository"
    )
    @Autowired
    IUserRepository userRepository;
    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();

        String email;
        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        }else{
            email = principal.toString();
        }
        User loggedInUser = userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", email));

        return Optional.ofNullable(loggedInUser.getId());

    }
}