package com.example.blogproject.service;

import com.example.blogproject.model.Board;
import com.example.blogproject.model.Reply;
import com.example.blogproject.model.User;
import com.example.blogproject.repository.BoardRepository;
import com.example.blogproject.repository.ReplyRepository;
import com.example.blogproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private HttpSession session;

    @Transactional
    public Boolean 글쓰기(Board board) {
        if(session.getAttribute("principal") != null){
            User user= (User) session.getAttribute("principal");
            board.setCount(0);
            board.setUser(user);
            boardRepository.save(board);
            return true;
        }else{
            log.info("session2 : " + session.getAttribute("principal"));
            return false;
        }

//        User user=(User) session.getAttribute("principal");
//        board.setCount(0);
//        board.setUser(user);
//        boardRepository.save(board);

    }

    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id) {
        Board board=boardRepository.findById(id).orElse(null);
      board.setCount(board.getCount()+1);
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public void 글삭제(int id) {
        boardRepository.deleteById(id);
        // void형이고, optional이 아니다.
    }

    @Transactional
    public void 글수정(int id, Board board) {
        Board findBoard= boardRepository.findById(id).orElse(null);
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        // 영속성컨텍스타 더티체킹을 해서 영속화한다.
        //boardRepository.save(findBoard);
    }

    @Transactional
    public void 댓글쓰기(User user, int boardId, Reply requestReply) {
        Board board=boardRepository.findById(boardId).orElseThrow(()->{
            return new IllegalArgumentException("댓글쓰기 실패: 게시글아이디를 찾을 수 없습니다.");
        });  // 영속화 완료

        board.setCount(board.getCount()-1);
        boardRepository.save(board);

        requestReply.setUser(user);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }

    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }

//    public void 조회수(int id) {
//    }


//   @Transactional
//    public void 조회수(int id) {
//       Board board =boardRepository.findById(id).orElse(null);
//       board.setCount(board.getCount()+1);
//       log.info("조회수  : " + id);
//       boardRepository.save(board);
//    }
}
