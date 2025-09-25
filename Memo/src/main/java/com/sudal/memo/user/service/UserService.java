package com.sudal.memo.user.service;

import com.sudal.memo.common.MD5HashingEncoder;
import com.sudal.memo.user.domain.User;
import com.sudal.memo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    private final UserRepository userRepository;

    // 권장하는 방식은 생성자를 통해 멤버변수에 주입할 객체를 전달받아서 사용
    // @Autowired
    // 다른 생성자가 없이 Autowired를 위한 생성자만 있는 경우 @Autowired 생략 가능
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(
            String loginId
            , String password
            , String name
            , String email
    ){

        // 비밀번호 해싱
        String encodedPassword = MD5HashingEncoder.encode(password);

        int count = userRepository.insertUser(loginId, encodedPassword, name, email);

        if(count == 1){
            return true;
        }else{
            return false;
        }
    }

    //
    public User getUser(String loginId, String password){

        String encodedpassword = MD5HashingEncoder.encode(password);

        return userRepository.selectUset(loginId,encodedpassword);
    }







    
}
