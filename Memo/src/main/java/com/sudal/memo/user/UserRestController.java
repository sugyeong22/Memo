package com.sudal.memo.user;

import com.sudal.memo.user.domain.User;
import com.sudal.memo.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController // @Controller + @ResponseBody
public class UserRestController {
    // API는 여기서 구성

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입 API
    @PostMapping("/join-process")
    public Map<String, String> join(
            @RequestParam String loginId
            , @RequestParam String password
            , @RequestParam String name
            , @RequestParam String email
    ){

        Map<String, String> resultMap = new HashMap<>();

        if(userService.createUser(loginId,password,name,email)){
            resultMap.put("result", "success");
        }else{
            resultMap.put("result", "fail");
        }

        return resultMap;
    };

    // 로그인 API
    @PostMapping("/login-process")
    public Map<String, String> login(
            @RequestParam String loginId
            , @RequestParam String password
    ){

        User user = userService.getUser(loginId, password);

        Map<String, String> resultMap = new HashMap<>();

        if(user != null){
            resultMap.put("result","success");
        } else {
            resultMap.put("result","fail");
        }

        return resultMap;
    }










}
