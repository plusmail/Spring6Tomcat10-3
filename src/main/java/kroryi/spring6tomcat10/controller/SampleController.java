package kroryi.spring6tomcat10.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello............");

    }

    @GetMapping("/ex1")
    public void ex1(@RequestParam("name") String name, @RequestParam("age") int age){
        // @RequestParam Spring 6, Spring boot 3.2 부터 사용해야함.
        //http://localhost:8080/ex1?name=홍길동&age=40
        log.info("ex1.............");
        log.info("name: {}", name);
        log.info("age: {}", age);

    }

    @GetMapping("/ex3")
    public void ex3(@RequestParam("dueDate") LocalDate dueDate){
        log.info("ex3.............");
        log.info("dueDate : {}", dueDate);
    }


    @GetMapping("/ex5")
    public String ex5(RedirectAttributes ra){
        log.info("ex5.............");
        ra.addAttribute("name", "ABC");
        ra.addFlashAttribute("result", "성공..");
        //이 메서드는 jsp페이기 없음.
        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6(@RequestParam("name") String name, Model model){
        log.info("ex6............");
        log.info("name: {}", name);
        // Service로직 DAO연되어서 일을 하고.
        // 결과를 model로 jsp페이지에 넘기면 됨.

        model.addAttribute("name", name);

    }
}
