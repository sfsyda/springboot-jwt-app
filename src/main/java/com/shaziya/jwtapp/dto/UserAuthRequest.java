package com.shaziya.jwtapp.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserAuthRequest {
    
    private final @NonNull String username;
    
    private final @NonNull String password;
}
