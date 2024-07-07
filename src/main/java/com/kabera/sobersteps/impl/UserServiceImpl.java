package com.kabera.sobersteps.impl;

import com.kabera.sobersteps.dto.RegisterUserDto;
import com.kabera.sobersteps.dto.UserProfileDto;
import com.kabera.sobersteps.model.Role;
import com.kabera.sobersteps.model.RoleName;
import com.kabera.sobersteps.model.User;
import com.kabera.sobersteps.repository.RoleRepository;
import com.kabera.sobersteps.repository.UserRepository;
import com.kabera.sobersteps.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userDao;
    private final RoleRepository roleDao;
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean registerUser(RegisterUserDto userDto, RoleName roleName) {
        Role userRole = roleDao.findByName(roleName);
        if(userRole == null) throw new RuntimeException("Role not found!");

        System.out.println(userRole.toString());

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User user = User.builder()
                        .email(userDto.getEmail())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .username(userDto.getUsername())
                        .gender(userDto.getGender())
                        .dateOfBirth(userDto.getDateOfBirth())
                        .roles(roles)
                        .build();
        userDao.save(user);
        return true;
    }

    @Override
    public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        return userDetails.getUsername();
    }

    @Override
    public UserProfileDto getUserProfile() {
        User user = userDao.findByEmail(getUsername()).get();
        return UserProfileDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    @Override
    public boolean findUserByEmail(String email) {
        return userDao.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> role) {
        return role.stream().map(
                r -> new SimpleGrantedAuthority(r.getName().toString())).collect(Collectors.toSet());
    }
}
