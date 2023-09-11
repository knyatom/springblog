package com.example.blogproject.controller;

import com.example.blogproject.model.User;
import com.example.blogproject.repository.UserRepository;
import com.example.blogproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }
    @GetMapping("/user/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm(){
        return "user/updateForm";
    }

    // logout
    @GetMapping("/user/logOut")
    public String logOut(HttpSession session){
        session.invalidate();
        return "/user/loginForm";
    }

    // 회원탈퇴
    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id){
        userService.회원탈퇴(id);
        return "/user/loginForm";
    }

//    @PostMapping("/dummy/join4")
//    public String join(User user){
//        User saved=userRepository.save(user);
//        return "redirect:/dummy/user/"+saved.getId() ;
//    }
//
//    @GetMapping("/dummy/user2/{id}")
//    public String detail(@PathVariable Long id, Model model){
//        User user=userRepository.findById(id).orElseThrow(()->{
//            return  new IllegalArgumentException("해당유저가 없습니다. " +id);
//        });
//        model.addAttribute("user",user);
//
//        return "users/ushow";
//    }


}
