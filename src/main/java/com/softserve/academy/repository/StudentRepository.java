package com.softserve.academy.repository;

import com.softserve.academy.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public Student getById(final int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findAny().orElse(null);
    }

    public void create(Student student) {
        if (student != null)
            students.add(student);
    }

    public void update(Student student) {
        if (student != null) {
            Student oldStudent = students.stream()
                    .filter(s -> s.getId() == student.getId())
                    .findAny().orElse(null);
            if (oldStudent != null) {
                int index = students.indexOf(oldStudent);
                students.set(index, student);
            }
        }
    }

    public void delete(int id) {
        students.stream()
                .filter(s -> s.getId() == id).findAny()
                .ifPresent(s -> students.remove(s));
    }

    public List<Student> getAll() {
        return students;
    }
}
