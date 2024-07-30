package com.openclassrooms.projet9microserviceassessment;

import com.openclassrooms.projet9microserviceassessment.service.AssessmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AssessmentControllerTests {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private AssessmentService assessmentService;

    @Test
    public void testNONE() throws Exception {


        mvc.perform(MockMvcRequestBuilders
                        .get("/assessment/{id}", 0))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("NONE"));

    }


    @Test
    public void testBORDERLINE() throws Exception {


        mvc.perform(MockMvcRequestBuilders
                        .get("/assessment/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("BORDERLINE"));

    }

    @Test
    public void testINDANGER() throws Exception {


        mvc.perform(MockMvcRequestBuilders
                        .get("/assessment/{id}", 3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("IN DANGER"));

    }

    @Test
    public void testEARLYONSET() throws Exception {


        mvc.perform(MockMvcRequestBuilders
                        .get("/assessment/{id}", 4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("EARLY ONSET"));

    }





}
