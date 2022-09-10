package com.axonactive.roomLeaseManagement.authenticate.impl;

import com.axonactive.roomLeaseManagement.authenticate.AuthController;
import com.axonactive.roomLeaseManagement.security.jwt.JwtRequest;
import com.axonactive.roomLeaseManagement.security.jwt.JwtResponse;
import com.axonactive.roomLeaseManagement.security.jwt.JwtUtils;
import com.axonactive.roomLeaseManagement.security.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;



    public ResponseEntity<?> authenticateUser(JwtRequest loginRequest) {
        log.trace("Log at trace level");
        log.debug("Log debug write here");
        log.info("Log info write here");
        log.warn("log warning here");
        log.error("log error here");



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }

}

