package com.skkudteam3.skkusirenorder.src.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    @ResponseBody
    @GetMapping("/customer/oauth/login")
    public String oauthLogin(
            Authentication authentication,
            @AuthenticationPrincipal OAuth2User oauth
    ) { //세션 정보 받아오기 (DI 의존성 주입)

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication: " + oAuth2User.getAttributes());
        //System.out.println("OAuth2User:" + oauth.getAttributes());

        return "OAuth 세션 정보 확인";
    }
/*
    @ResponseBody
    @GetMapping("/user")
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("GetMapping(/user) ==========================");
        System.out.println("principalDetails: " + principalDetails );

        return "user";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

*/
    //스프링 시큐리티가 낚아 챈다(post로 오는것!!)!! -> config 를 통해 해결
    @ResponseBody
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
