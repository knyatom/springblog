package com.example.blogproject.repository;

import com.example.blogproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


// JSP ->DAO에 해당
@Repository // 생략가능하다. 자동으로 bean으로 등록이 된다.
public interface UserRepository extends JpaRepository<User,Long> {

 // SELECT * FROM user WHERE username=? AND password=? ;
 // JPA Naming 쿼리
 User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

 User findByUsername(@Param("username") String username);


 //@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery=true)
 // User login(String username, String password);
}
