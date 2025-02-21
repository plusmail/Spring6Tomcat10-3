package kroryi.spring.controller;

import jakarta.validation.Valid;
import kroryi.spring.dto.PageRequestDTO;
import kroryi.spring.dto.PageResponseDTO;
import kroryi.spring.dto.TodoDTO;
import kroryi.spring.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService service;


    @GetMapping("/list")
    public String list(
            @Valid PageRequestDTO pageReqDTO,
            BindingResult bindingResult,
            Model model) {
        log.info(pageReqDTO);
        if (bindingResult.hasErrors()) {
            pageReqDTO = PageRequestDTO.builder().build();
        }

        PageResponseDTO<TodoDTO> pageResDTO = service.getList(pageReqDTO);
        model.addAttribute("resDTO", pageResDTO);
        model.addAttribute("reqDTO", pageReqDTO);

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
                       PageRequestDTO pageReqDTO,
                       Model model) {
        log.info("read------> {}", tno);
        TodoDTO dto = service.getOne(tno);
        log.info(dto.toString());
        model.addAttribute("dto", dto);
        model.addAttribute("reqDTO", pageReqDTO);

        return "/todo/todoRead";
    }

    @GetMapping("/modify/{tno}")
    public String modify(@PathVariable Long tno,
                         PageRequestDTO pageReqDTO,
                         Model model) {
        log.info("modify------> {}", tno);
        TodoDTO dto = service.getOne(tno);
        log.info(dto.toString());
        model.addAttribute("dto", dto);
        model.addAttribute("reqDTO", pageReqDTO);

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
    public String remove(@PathVariable Long tno,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("----------- remove -----------");
        log.info("remove------> {}", tno);

        service.remove(tno);

        // redirectAttributes.addAttribute("page",1) 는 /todo/list?page=1
        redirectAttributes.addAttribute("page", 1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/todo/list";

    }


    @PostMapping("/modify/{tno}")
    public String modify(
            PageRequestDTO pageReqDTO,
            @Valid TodoDTO dto,
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

        redirectAttributes.addAttribute("page", pageReqDTO.getPage());
        redirectAttributes.addAttribute("size", pageReqDTO.getSize());
        redirectAttributes.addAttribute("pageListSize", pageReqDTO.getPageListSize());

        return "redirect:/todo/list";

    }

}
