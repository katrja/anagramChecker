package com.test.anagram.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.test.anagram.exception.ValidationMessage;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.Assert;

@SpringBootTest
@AutoConfigureMockMvc
public class AnagramCheckerControllerTest {

  @Autowired
  private AnagramCheckerController controller;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void contextLoads() throws Exception {
    assertThat(controller).isNotNull();
  }

  @Test
  public void testGetRequestToShowCheckAnagramPage() throws Exception {
    this.mockMvc.perform(
            get("/anagram"))
        .andDo(print())
        .andExpect(view().name("index"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp")).andReturn();
  }

  @Test
  public void testPostRequestToCheckAnagram_valid() throws Exception {
    String testSubjectText = "teac1her";
    String testAnagramText = "chea_ter?_";

    MvcResult result = this.mockMvc.perform(
            post("/anagram")
                .param("subject", testSubjectText)
                .param("anagram", testAnagramText)
        )
        .andDo(print())
        .andExpect(view().name("index"))
        .andExpect(status().isOk())
        .andReturn();

    Assert.assertTrue((Boolean) result.getModelAndView().getModel().get("isValidAnagram"));
    Assert.assertNull(result.getModelAndView().getModel().get("blankFieldValue"));
    Assert.assertNull(result.getModelAndView().getModel().get("exceptions"));
  }

  @Test
  public void testPostRequestToCheckAnagram_notValid() throws Exception {
    String testSubjectText = "ssilence";
    String testAnagramText = "blindnes";

    MvcResult result = this.mockMvc.perform(
            post("/anagram")
                .param("subject", testSubjectText)
                .param("anagram", testAnagramText)
        )
        .andDo(print())
        .andExpect(view().name("index"))
        .andExpect(status().isOk())
        .andReturn();

    Assert.assertFalse((Boolean) result.getModelAndView().getModel().get("isValidAnagram"));
    Assert.assertNull(result.getModelAndView().getModel().get("blankFieldValue"));
    Assert.assertNull(result.getModelAndView().getModel().get("exceptions"));
  }

  @Test
  public void testPostRequestToCheckAnagram_numbers() throws Exception {
    String testSubjectText = "12";
    String testAnagramText = "blindnes";

    MvcResult result = this.mockMvc.perform(
            post("/anagram")
                .param("subject", testSubjectText)
                .param("anagram", testAnagramText)
        )
        .andDo(print())
        .andExpect(view().name("index"))
        .andExpect(status().isOk())
        .andReturn();

    Assert.assertNull(result.getModelAndView().getModel().get("isValidAnagram"));
    Assert.assertNull(result.getModelAndView().getModel().get("exceptions"));
    Assert.assertTrue((Boolean) result.getModelAndView().getModel().get("blankFieldValue"));
  }

  @Test
  public void testPostRequestToCheckAnagram_blank() throws Exception {
    String testSubjectText = "";
    String testAnagramText = "abc";

    MvcResult result = this.mockMvc.perform(
            post("/anagram")
                .param("subject", testSubjectText)
                .param("anagram", testAnagramText)
        )
        .andDo(print())
        .andExpect(view().name("index"))
        .andExpect(status().isOk())
        .andReturn();

    Assert.assertNull(result.getModelAndView().getModel().get("isValidAnagram"));
    Assert.assertNull(result.getModelAndView().getModel().get("blankFieldValue"));

    Assert.assertNotNull(result.getModelAndView().getModel().get("exceptions"));
    List<ValidationMessage> validationMessages = (List<ValidationMessage>) result.getModelAndView()
        .getModel().get("exceptions");
    Assert.assertEquals(validationMessages.size(), 1);

    ValidationMessage validationMessage = validationMessages.get(0);
    Assert.assertEquals(validationMessage.getFieldName(), "subject");
    Assert.assertEquals(validationMessage.getInvalidValue(), "");
    Assert.assertNotNull(validationMessage.getValidationMessage());
  }
}
