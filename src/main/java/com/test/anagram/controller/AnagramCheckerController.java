package com.test.anagram.controller;


import com.test.anagram.service.AnagramChecker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnagramCheckerController {

  @Autowired
  private AnagramChecker anagramChecker;

  @GetMapping("/anagram")
  public String showAnagramPage() {
    return "index";
  }

  @PostMapping("/anagram")
  public String checkAnagram(@RequestParam @NotBlank @Size(max = 200) String subject,
      @RequestParam String anagram,Model model) {

    boolean result = anagramChecker.isAnagram(subject, anagram);

/*    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("isValidAnagram", result);*/
    model.addAttribute("isValidAnagram", result);

    //return modelAndView;
    return "index";
  }
}
