package com.example.blatnoinomer.service;

import com.example.blatnoinomer.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    boolean save(UserDTO userDTO);
}
