package kroryi.spring.controller;

import kroryi.spring.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

//    /todo/list
    @RequestMapping("/list")
    public void list(){
        log.info("list..............");
    }

//    @RequestMapping(value="/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void register(){
        log.info("Get register............");

    }

//    @PostMapping("/register")
//    public void registerPost(TodoDTO todoDTO){
//        log.info("Register post...........");
//        log.info(todoDTO);
//
//    }

    @PostMapping("/register-view")
    public void ex4One(@ModelAttribute("dto") TodoDTO todoDTO, Model model){
        log.info("ex4One............");
        log.info(todoDTO);
        // todoDTO 주로 Service에 넘겨줘가지고 DAO, DB 관련 데이터 가져오는 역할


        //JSP던질때는 Model 추가 해서 던진다.

        model.addAttribute("todo", todoDTO);
    }




}
