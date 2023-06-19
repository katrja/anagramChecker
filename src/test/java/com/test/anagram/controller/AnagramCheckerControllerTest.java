package com.test.anagram.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testng.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AnagramCheckerControllerTest {
    static final Logger logger = LoggerFactory.getLogger(AnagramCheckerControllerTest.class);

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
    }
  }
