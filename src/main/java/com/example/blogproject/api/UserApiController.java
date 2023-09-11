package com.example.blogproject.api;

import com.example.blogproject.dto.ResponseDto;
import com.example.blogproject.model.RoleType;
import com.example.blogproject.model.User;
import com.example.blogproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

//    @Autowired
//    ResponseDto dto;

    // 회원가입
    @PostMapping("api/user")
    public ResponseDto<Integer> save(@RequestBody User user){

        user.setRole(RoleType.ADMIN);
        Integer result=userService.회원가입(user);

        if(result==0){
            return new ResponseDto<Integer>(HttpStatus.BAD_REQUEST.value(),0);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // DTO를 DI한 경우와 bulid 패턴을 사용한 경우의 코드예
    // 로그인
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user){

          User principal=userService.로그인(user);
        System.out.println("principal: " + principal);
            if(principal !=null) {
                session.setAttribute("principal", principal);
            }else{
                return new ResponseDto<Integer>(HttpStatus.FORBIDDEN.value(),0);
            }
         return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        //dto.setData(1);
        //dto.setStatus(HttpStatus.OK.value());
       // dto.builder().data(1).status(HttpStatus.OK.value()).build();
        //return dto;
    }

    // 아이디중복검사
    @GetMapping("/api/user/{username}")
    public ResponseEntity<String> check(@PathVariable String username){
        int result= userService.중복확인(username);
        if(result==1){
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("NO");
        }
    }

    // 회원정보수정
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.회원수정(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
