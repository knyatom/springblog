package com.example.blogproject.service;

import com.example.blogproject.model.User;
import com.example.blogproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    HttpSession session;

    @Transactional
    public int 회원가입(User user){
       User username= userRepository.findByUsername(user.getUsername());
       if(username != null){
           return 0;
       }
       userRepository.save(user);
       return 1;
    }

    @Transactional(readOnly = true)  // 정합성을 유지
    public User 로그인(User user) {
        String userName=user.getUsername();
        String userPassword=user.getPassword();
        return userRepository.findByUsernameAndPassword(userName,userPassword);
    }

    public int 중복확인(String username) {
        User user=userRepository.findByUsername(username);
        if(user==null) return 1;
        else return 0;
    }

    @Transactional
    public void 회원수정(User user) {
        User persistance=userRepository.findById(user.getId()).orElse(null);
        persistance.setPassword(user.getPassword());
        persistance.setEmail(user.getEmail());
        session.setAttribute("principal",persistance);
    }

    public void 회원탈퇴(Long id) {
        User user=userRepository.findById(id).orElse(null);
        if(user != null) userRepository.deleteById(id);
        session.invalidate();
    }
}
