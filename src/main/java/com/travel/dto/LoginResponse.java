package com.travel.dto;

import java.util.List;

public class LoginResponse {

    private String accessToken;
    private String tokenType = "Bearer";
    private Long userId;
    private String username;
    private String fullName;
    private List<String> roles;

    public LoginResponse() {
    }

    public LoginResponse(
            String accessToken,
            Long userId,
            String username,
            String fullName,
            List<String> roles
    ) {
        this.accessToken = accessToken;
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}