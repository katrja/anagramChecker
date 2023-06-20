package com.test.anagram.service;

import com.test.anagram.exception.ValidationMessageException;
import java.util.Arrays;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AnagramChecker {

  static final Logger logger = LoggerFactory.getLogger(AnagramChecker.class);

  public boolean isAnagram(String subject, String anagram) {

    subject = normalizeText(subject);
    anagram = normalizeText(anagram);

    if (Strings.isBlank(subject) || Strings.isBlank(anagram)) {
      throw new ValidationMessageException();
    }

    if (subject.length() != anagram.length()) {
      logger.info(
          String.format("Different length after normalization of strings: %s and %s", subject,
              anagram));
      return false;
    }
    char[] subjectArray = toSortedArray(subject);
    char[] anagramArray = toSortedArray(anagram);

    //O(nlogn)
    return Arrays.equals(subjectArray, anagramArray);
  }

  private String normalizeText(String text) {
    String normalizedText = text.trim().replaceAll("[^a-zA-Z]", "").toLowerCase();
    logger.info("normalizedText=" + normalizedText);
    return normalizedText;
  }

  private char[] toSortedArray(String text) {
    //Quicksort algorithm for sorting. Its complexity is O(n log(n)).
    char[] textArray = text.toCharArray();
    Arrays.sort(textArray);
    return textArray;
  }
}
