package com.example.blogproject.controller;

import com.example.blogproject.test.MyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DummyController {
    @GetMapping("/join")
    public String join(){
        return "join"; // views->join.jsp   viewresolver뷰리졸버
    }

    @PostMapping("/user/join")
   // @ResponseBody
    public ResponseEntity<MyData> join2(@RequestBody String user){
        log.info(user);
        MyData data=new MyData();
        data.setMsg("OK");
        return ResponseEntity.status(HttpStatus.OK).body(data);
        //return ResponseEntity.ok(data);
       // return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

