package com.example.controller;

import com.example.data.entity.Lesson;
import com.example.data.repo.LessonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
public class LessonsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LessonRepository lessonRepository;

    @Test
    @Transactional
    @Rollback
    public void testPatchEndpoint() throws Exception {
        Lesson mockLesson = new Lesson("Integration tests!");
        Lesson lesson = lessonRepository.save(mockLesson);
        String requestString = String.format("{\"id\": %s, \"title\": \"Spring Security\"}", lesson.getId());

        MockHttpServletRequestBuilder request = patch(String.format("/lessons/%s", lesson.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestString);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Spring Security")));

        List<Lesson> all = lessonRepository.findAll();
        assertThat(all.size(), equalTo(1));
        assertThat(all.get(0).getId(), equalTo(lesson.getId()));
        assertThat(all.get(0).getTitle(), equalTo("Spring Security"));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetEndpoint() throws Exception {
        Lesson mockLesson = new Lesson("Transactional tests probably should have been covered before this exercise");

        Lesson lesson = lessonRepository.save(mockLesson);

        mockMvc.perform(get(String.format("/lessons/%s", lesson.getId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo("Transactional tests probably should have been covered before this exercise")));
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteEndpoint() throws Exception {
        Lesson mockLesson = new Lesson("Transactional tests probably should have been covered before this exercise");

        Lesson lesson = lessonRepository.save(mockLesson);

        mockMvc.perform(delete(String.format("/lessons/%s", lesson.getId())))
                .andExpect(status().isOk());

        List<Lesson> all = lessonRepository.findAll();
        assertThat(all.size(), equalTo(0));
    }

    @Test
    @Transactional
    @Rollback
    public void testGetAllLessonsEndpoint() throws Exception {
        Lesson lesson1 = lessonRepository.save(new Lesson("lesson 1"));
        Lesson lesson2 = lessonRepository.save(new Lesson("lesson 2"));

        mockMvc.perform(get("/lessons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", is("lesson 1")))
                .andExpect(jsonPath("$.[1].title", is("lesson 2")))
                .andExpect(jsonPath("$.[2]").doesNotExist());
    }

    @Test
    @Transactional
    @Rollback
    public void testPostLessonsEndpoint() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Lesson mockLesson = new Lesson("New Lesson");
        String json = mapper.writeValueAsString(mockLesson);


        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(request)
                .andExpect(status().isOk());

        List<Lesson> all = lessonRepository.findAll();

        assertThat(all.size(), equalTo(1));

        Lesson newLesson = all.get(0);
        assertThat(newLesson.getTitle(), equalTo("New Lesson"));
    }
}
