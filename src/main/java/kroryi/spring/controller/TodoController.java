package kroryi.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
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

import javax.management.Query;
import java.util.Objects;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;


    @GetMapping("/list")
    public String list(Model model) {
        log.info("todo 목록보기");
        model.addAttribute("dtoList", service.getAll());

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
        if (bindingResult.hasErrors()) {
            log.error("binding 에러 발생.. ");
//            String fieldName = bindingResult.getFieldError().getField();
//            redirectAttributes.addFlashAttribute("errors", fieldName + "의 빈칸을 채우시오...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info("todoDTO -<{}", todoDTO);
        service.register(todoDTO);

        return "redirect:/todo/list";
    }

//    @GetMapping("/read")
//    public String read(@RequestParam Long tno, Model model) {
//        TodoDTO dto = service.getOne(tno);
//        log.info(dto.toString());
//        model.addAttribute("dto", dto);
//
//        return "/todo/todoRead";
//    }

    /*
    @GetMapping("/read/{tno}")
    tno은 @PathVariable 로 전달 받고

    ?name=홍길동   Search Query는
    @RequestParam 로 전달 받는다.
    */
    @GetMapping("/read/{tno}")
    public String read(@PathVariable Long tno,
                       Model model) {
        log.info("read------> {}", tno);
        TodoDTO dto = service.getOne(tno);
        log.info(dto.toString());
        model.addAttribute("dto", dto);

        return "/todo/todoRead";
    }

    @GetMapping("/modify/{tno}")
    public String modify(@PathVariable Long tno, Model model) {
        log.info("modify------> {}", tno);
        TodoDTO dto = service.getOne(tno);
        log.info(dto.toString());
        model.addAttribute("dto", dto);

        return "/todo/todoModify";
    }


//    @GetMapping({"/read/{tno}", "/modify/${tno}"})
//    public String read(@PathVariable Long tno,
//                       HttpServletRequest request,
//                       Model model) {
//        String requestURI = request.getRequestURI();
//        log.info("requestURI -<{}", requestURI);
//        String action = requestURI.contains("/modify/") ? "modify" : "read";
//        log.info("read------> {}, {}", tno, action);
//        TodoDTO dto = service.getOne(tno);
//        log.info(dto.toString());
//        model.addAttribute("dto", dto);
//
//        return action.equals("modify") ? "/todo/todoModify" : "/todo/todoRead";
//    }

//    @GetMapping({"/read/{tno}", "/modify/${tno}"})
//    public String read(@PathVariable Long tno,
//                       @RequestParam(defaultValue = "read") String mode,
//                       Model model) {
//        log.info("read------> {}, {}", tno, mode);
//        TodoDTO dto = service.getOne(tno);
//        log.info(dto.toString());
//        model.addAttribute("dto", dto);
//        if(mode.equals("modify")) {
//            return "/todo/todoModify";
//        }
//        return "/todo/todoRead";
//
//    }

    @PostMapping("/remove/{tno}")
    public String remove(@PathVariable Long tno, RedirectAttributes redirectAttributes) {
        log.info("----------- remove -----------");
        log.info("remove------> {}", tno);

        service.remove(tno);

        return "redirect:/todo/list";

    }


    @PostMapping("/modify/{tno}")
    public String modify(@Valid TodoDTO dto,
                         BindingResult bindingResult,
                         @PathVariable Long tno,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("수정 시 에러발생...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // redirect는 GET 방식
            return "redirect:/todo/modify/" + tno;
        }

        log.info("modify post------> {}", dto.toString());
        service.modify(dto);

        return "redirect:/todo/list";

    }

}
