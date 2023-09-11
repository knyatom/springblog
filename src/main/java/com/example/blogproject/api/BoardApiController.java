package com.example.blogproject.api;

import com.example.blogproject.dto.ResponseDto;
import com.example.blogproject.model.Board;
import com.example.blogproject.model.Reply;
import com.example.blogproject.model.User;
import com.example.blogproject.repository.BoardRepository;
import com.example.blogproject.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    HttpSession session;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board){

        Boolean isSession= boardService.글쓰기(board);
        if(isSession==true){
            return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        }else{
            return new ResponseDto<Integer>(HttpStatus.OK.value(),0);
        }
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){

        boardService.글삭제(id);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
    // 글수정
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){

        boardService.글수정(id,board);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // 댓글등록
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply){

        log.info("boardId : " + boardId);
        User user=(User) session.getAttribute("principal");

        log.info("user : " + user);

        boardService.댓글쓰기(user,boardId,reply);

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    // 댓글삭제
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId){
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

//    @PatchMapping("/api/count/{id}")
//    public void count(@PathVariable int id){
//         boardService.조회수(id);
//         log.info("조회수 : " + id);
//        //boardRepository.incrementViews(id);
//    }
}
