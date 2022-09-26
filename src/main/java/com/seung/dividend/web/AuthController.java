package com.seung.dividend.web;

import com.seung.dividend.model.Auth;
import com.seung.dividend.security.TokenProvider;
import com.seung.dividend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SignUp request) {
        //회원가입
        var result = this.memberService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SignIn request) {
        //로그인
       var member = this.memberService.authenticate(request);
       var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());
        return ResponseEntity.ok(token);
    }
}
