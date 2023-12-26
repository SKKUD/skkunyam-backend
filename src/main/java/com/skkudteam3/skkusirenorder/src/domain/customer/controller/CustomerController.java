package com.skkudteam3.skkusirenorder.src.domain.customer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    // 고객 회원가입
    @PostMapping("/create")
    public String create(Model model){
        return "hello"; // api 이므로 수정
    }

}
