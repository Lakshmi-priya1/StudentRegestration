package com.exampleStudent.StudentRegistration.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exampleStudent.StudentRegistration.domain.AppUser;
import com.exampleStudent.StudentRegistration.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    // Cache to avoid repeated database calls
    private final Map<String, UserDetails> userCache = new ConcurrentHashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the user is already cached
        if (userCache.containsKey(username)) {
            return userCache.get(username);
        }

        // Fetch user from database
        AppUser user = userRepo.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Normalize the role to ensure Spring Security accepts it
        String normalizedRole = "ROLE_" + user.getRole().replace("ROLE_", "");

        // Create UserDetails object
        UserDetails userDetails = new User(
            user.getUsername(),
            user.getPassword(),
            List.of(new SimpleGrantedAuthority(normalizedRole))
        );

        // Cache the user details for future lookups
        userCache.put(username, userDetails);

        return userDetails;
    }
}



