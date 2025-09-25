package com.sudal.memo.user.repository;

import com.sudal.memo.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface UserRepository {

    public int insertUser(
            @Param("loginId") String loginId
            , @Param("password") String password
            , @Param("name") String name
            , @Param("email") String email
    );

    public User selectUset(
            @Param("loginId") String loginId
            , @Param("password") String password
    );
}
