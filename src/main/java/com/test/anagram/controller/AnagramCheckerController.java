package com.test.anagram.controller;

import com.test.anagram.service.AnagramChecker;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Validated
public class AnagramCheckerController {

  @Autowired
  private AnagramChecker anagramChecker;

  @GetMapping("/anagram")
  public String showAnagramPage() {
    return "index";
  }

  @PostMapping("/anagram")
  public String checkAnagram(@RequestParam @Length(min = 1, max = 200) String subject,
      @RequestParam @Length(min = 1, max = 200) String anagram, Model model) {

    boolean result = anagramChecker.isAnagram(subject, anagram);
    model.addAttribute("isValidAnagram", result);
    model.addAttribute("subject", subject);
    model.addAttribute("anagram", anagram);
    return "index";
  }
}
