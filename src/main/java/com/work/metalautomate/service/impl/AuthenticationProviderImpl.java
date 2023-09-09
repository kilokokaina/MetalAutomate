package com.work.metalautomate.service.impl;

import com.work.metalautomate.api.dto.CredentialsDTO;
import com.work.metalautomate.service.impl.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final SecurityContextRepository contextRepository;
    private final UserServiceImpl userService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Autowired
    public AuthenticationProviderImpl(UserServiceImpl userService,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        this.contextRepository  = new HttpSessionSecurityContextRepository();
        this.userService = userService;
        this.response = response;
        this.request = request;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = userService.loadUserByUsername(username);

        if (userDetails == null) {
            log.error("User not found: " + username);
            return null;
        }

        if (!userDetails.getPassword().equals(password)) {
            log.error("Incorrect password! Username: " + username);
            return null;
        }

        return new UsernamePasswordAuthenticationToken(
                username, password,
                userDetails.getAuthorities()
        );
    }

    public String startSession(CredentialsDTO credentialsDTO) {
        String username = credentialsDTO.getUsername();
        String password = credentialsDTO.getPassword();

        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticate(authentication);

            if (authentication == null) return "false";

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

            contextRepository.saveContext(context, request, response);

            log.info("Authentication success: " + username);
        } catch (NullPointerException nullEx) {
            log.error(nullEx.getMessage());
            return "Incorrect username or password";
        } catch (AuthenticationException authEx) {
            log.error(authEx.getMessage());
            return "Somethings went wrong";
        }

        return "true";
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
