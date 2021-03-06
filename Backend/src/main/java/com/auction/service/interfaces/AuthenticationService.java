package com.auction.service.interfaces;

import com.auction.web.dto.request.LoginRequest;
import com.auction.web.dto.request.SignupRequest;
import com.auction.web.dto.response.JwtResponse;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AuthenticationService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    void register(SignupRequest signUpRequest) throws MessagingException, UnsupportedEncodingException;

    JwtResponse refreshToken(String token);
}
