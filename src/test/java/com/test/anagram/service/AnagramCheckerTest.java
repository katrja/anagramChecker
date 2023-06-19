package com.test.anagram.service;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class AnagramCheckerTest {

  private AnagramChecker underTest;

  @BeforeTest
  public void setup() {
    underTest = new AnagramChecker();
  }

  @DataProvider(name = "textPairs")
  public static Object[][] dataProvider() {
    return new Object[][]{
        {"teacher", "cheater"},
        {"evil", "vile"},
        {"New York Times1", "monkeys write"},
        {"Church of Scientology", "rich-chosen goofy cult"},
        {"a gentleman!", "elegant man"}
        //  {"woW1", "wowl"}
    };
  }

  @Test(dataProvider = "textPairs")
  public void isAnagram(String subject, String anagram) {
    Assert.assertTrue(underTest.isAnagram(subject, anagram));
  }
}
