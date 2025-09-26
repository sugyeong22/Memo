package com.sudal.memo.user;

import com.sudal.memo.user.domain.User;
import com.sudal.memo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
            , HttpServletRequest request
    ){

        User user = userService.getUser(loginId, password);

        Map<String, String> resultMap = new HashMap<>();

        if(user != null){

            HttpSession session = request.getSession();
            // session을 통한 로그인 과정
            // 로그인 성공시 해당 사용자 정보를 session에 저장한다.
            // Session은 특정 클라이언트의 정보를 저장한다.
            // 다른 요청시에도 Session 정보를 활용할 수 있다.

            session.setAttribute("userId", user.getId());

            // 빈번이 사용되는 정보는 session에 저장하면 좋음
            session.setAttribute("userName", user.getName());

            resultMap.put("result","success");
        } else {
            resultMap.put("result","fail");
        }

        return resultMap;
    }










}
