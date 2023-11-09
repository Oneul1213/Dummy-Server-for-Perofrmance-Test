package com.example.dummy.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    
    private String loginId;
    private String password;
}
