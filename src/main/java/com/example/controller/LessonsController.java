package com.example.controller;

import com.example.data.entity.Lesson;
import com.example.data.repo.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    @Autowired
    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable("id") Long id) {
        return this.repository.findById(id);
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @PatchMapping("/{id}")
    public Lesson updateLesson(@PathVariable("id") Long id, @RequestBody Lesson updatedLesson) {
        Lesson lesson = this.repository.findById(id);
        lesson.setTitle(updatedLesson.getTitle());
        return this.repository.save(lesson);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable("id") Long id) {
        Lesson lesson = this.repository.findById(id);
        this.repository.delete(lesson);
    }

}
