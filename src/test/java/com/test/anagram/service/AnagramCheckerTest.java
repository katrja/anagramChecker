package com.test.anagram.service;

import com.test.anagram.exception.ValidationMessageException;
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

  @DataProvider(name = "inputValues")
  public static Object[][] dataProvider() {
    return new Object[][]{
        {"teacher", "cheater", true},
        {"evil", "vile", true},
        {"New York Times1", "monkeys write", true},
        {"Church of Scientology", "rich-chosen goofy cult", true},
        {"a gentleman!", "elegant man", true},
        {"abc", "cde", false},
        {"woW1", "wowl", false}
    };
  }

  @Test(dataProvider = "inputValues")
  public void isAnagram(String subject, String anagram, Boolean expectedResult) {
    Assert.assertEquals(underTest.isAnagram(subject, anagram), expectedResult);
  }

  @DataProvider(name = "blankInputValues")
  public static Object[][] dataProvider_blankValues() {
    return new Object[][]{
        {"", ""},
        {" ,", " ,"},
        {"123", "321"}
    };
  }

  @Test(dataProvider = "blankInputValues", expectedExceptions = {ValidationMessageException.class})
  public void isAnagram(String subject, String anagram) {
    underTest.isAnagram(subject, anagram);
  }
}
