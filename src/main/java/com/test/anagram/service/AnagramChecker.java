package com.test.anagram.service;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnagramChecker {
  static final Logger logger = LoggerFactory.getLogger(AnagramChecker.class);

  public boolean isAnagram(String subject, String anagram) {

    subject = normalizeText(subject);
    anagram = normalizeText(anagram);

    if (subject.length() != anagram.length()) {
      logger.info("Different length after normalization");
      return false;
    }
    //2 * (n log n)
    char[] subjectArray = toSortedArray(subject);
    char[] anagramArray = toSortedArray(anagram);

    // n
    for (int i = 0; i < subjectArray.length; i++) {
      if (subjectArray[i] != anagramArray[i]) {
        logger.info("Anagram is not valid");
        return false;
      }
    }
    //n(log n)
    logger.info("Anagram is valid");
    return true;
  }

  private String normalizeText(String text){
    String normalizedText = text.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
    logger.info("normalizedText="  + normalizedText);
    return normalizedText;
  }

  private char[] toSortedArray(String text) {
    //Quicksort algorithm for sorting. Its complexity is O(n log(n)).
    char[] textArray = text.toCharArray();
    Arrays.sort(textArray);
    return textArray;
  }
}
