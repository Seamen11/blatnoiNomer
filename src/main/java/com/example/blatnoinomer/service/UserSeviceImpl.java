package com.example.blatnoinomer.service;

import com.example.blatnoinomer.dao.UserRepository;
import com.example.blatnoinomer.domain.Role;
import com.example.blatnoinomer.domain.User;
import com.example.blatnoinomer.dto.UserDTO;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserSeviceImpl implements UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserSeviceImpl() {
    }

    @Override
    public boolean save(UserDTO userDTO)
    {
        if(!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword()))
        {
            throw new RuntimeException("Пароли не совпадают! Пожалуйста поменяйте!");
        }
        User user = User.builder()
                .name(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findFirstByName(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                roles
        );
    }
}
