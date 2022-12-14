package com.shaziya.jwtapp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
public class JwtResponse {
    
    private final @NonNull String token;
}
