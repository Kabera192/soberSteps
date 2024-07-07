package com.kabera.sobersteps.service;

import com.kabera.sobersteps.dto.RegisterUserDto;
import com.kabera.sobersteps.dto.UserProfileDto;
import com.kabera.sobersteps.model.RoleName;
import com.kabera.sobersteps.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    public User findByUsername(String username);
    public boolean registerUser(RegisterUserDto userDto, RoleName roleName);
    public String getUsername();
    public UserProfileDto getUserProfile();
    public boolean findUserByEmail(String email);
}
