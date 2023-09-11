package com.example.blogproject.api;

import com.example.blogproject.dto.UserTest;
import com.example.blogproject.model.RoleType;
import com.example.blogproject.model.User;
import com.example.blogproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DummyControllerTest {

    // DI - 필드 주입, 의존성주입
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(@RequestParam("username") String username, String password, String email){
        log.info(username);
        log.info(password);
        log.info(email);
        return "회원가입이 완료되었습니다.";
    }

    @PostMapping("/dummy/join2") // model에 User클래스로 테스트
    public String join(UserTest user){
        log.info(user.getUsername());
        log.info(user.getPassword());
        log.info(user.getEmail());
        return "회원가입이 완료.";
    }

    @PostMapping("/dummy/join3") // model에 User클래스로 테스트
    public String join(User user){
        log.info("username: {}", user.getUsername());
        log.info("password: {}", user.getPassword());
        log.info(user.getEmail());

        user.setRole(RoleType.USER);
       User saved= userRepository.save(user);
        return "데이터베이스에 저장하는 회원가입 완료.";
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable Long id){
        // naming convention
        // select * from user where id=1;
          User user=userRepository.findById(id).orElseThrow(()->{
              return  new IllegalArgumentException("해당유저가 없습니다. " +id);
          });
          //User user=userRepository.findById(id).orElse(null);
            // get, orElseGet

        return user;
    }
    // get매핑이면 Postman이나 talend로 테스트안해도 된다.

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        log.info("pageable : " + pageable);
         //Page<User> users=userRepository.findAll(pageable);

        Page<User> paginUser= userRepository.findAll(pageable);
        List<User> users=paginUser.getContent();
        return users;

    }
// http://localhost:8085/dummy/user?page=1

    @GetMapping("/dummy/user/page/{page}")
    public Page<User> pageList2(@PageableDefault(size=3,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> users=userRepository.findAll(pageable);
        return users;
    }

  //  http://localhost:8085/dummy/page/1

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User requestUser){
        System.out.println("id: "+id);
        System.out.println("password: "+requestUser.getPassword());
        System.out.println("email: "+requestUser.getEmail());

        User user=userRepository.findById(id).orElse(null);

        // 엔티티 변경
        user.setUsername(requestUser.getUsername());
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        user.setRole(RoleType.USER);
        //userRepository.save(user);  더티체킹
        return  user;
    }
    // http://localhost:8085/dummy/user/2

    // 삭제하기 테스트1
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable Long id){
      try {
          userRepository.deleteById(id);
      }catch (Exception e){
          return "해당 id는 DB에 없습니다";
      }
        return "삭제완료 : " + id ;
    }
    // http://localhost:8085/dummy/user/2

    // 삭제하기 테스트1
    @DeleteMapping("/dummy2/user/{id}")
    public String delete2(@PathVariable Long id){
       User user=userRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("삭제 실패..");
       });  // 람다식
        userRepository.delete(user);  // return 값이 void
        return "삭제완료2 : " + id ;
    }
    // http://localhost:8085/dummy2/user/2
}










