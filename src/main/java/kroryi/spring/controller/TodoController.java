package kroryi.spring.controller;

import jakarta.validation.Valid;
import kroryi.spring.dto.TodoDTO;
import kroryi.spring.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;


    @GetMapping("/list")
    public String list() {
        log.info("todo 목록보기");

        return "/todo/todoList";
    }

    @GetMapping("/register")
    public String registerGet() {
        log.info("GET todo 등록 페이지 보여주기....");
        return "/todo/todoRegister";
    }


    @PostMapping("/register")
    public String registerPost(
            @Valid TodoDTO todoDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        log.info("POST todo 등록....");
        log.info(todoDTO.toString());
        if(bindingResult.hasErrors()) {
            log.error("binding 에러 발생.. ");
            String fieldName = Objects.requireNonNull(bindingResult.getFieldError()).getField();
            redirectAttributes.addFlashAttribute("errors", fieldName + "의 빈칸을 채우시오...");
            return "redirect:/todo/register";
        }
        log.info("todoDTO -<{}", todoDTO);
        service.register(todoDTO);

        return "redirect:/todo/list";
    }
}
