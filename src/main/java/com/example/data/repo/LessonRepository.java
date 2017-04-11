package com.example.data.repo;

import com.example.data.entity.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Lesson findById(Long id);
    List<Lesson> findAll();
}