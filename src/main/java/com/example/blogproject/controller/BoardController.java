package com.example.blogproject.controller;

import com.example.blogproject.model.Board;
import com.example.blogproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping({"","/"})  // 아무것도 안적은 경우와 /슬래시를 붙인경우
    public String index(Model model,
             @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC) Pageable pageable){

       Page<Board> boards= boardService.글목록(pageable);

        model.addAttribute("boards",boards);
        return "index";
    }

    @GetMapping("/board/writeForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        Board board=boardService.글상세보기(id);
        model.addAttribute("board", board);
        return "board/detail";
    }
    // 수정폼열기
    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model){
        Board board=boardService.글상세보기(id);
        model.addAttribute("board", board);
        return "board/updateForm";
    }

}
