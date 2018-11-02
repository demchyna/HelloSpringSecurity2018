package com.softserve.academy.controller;

import com.softserve.academy.model.Group;
import com.softserve.academy.model.Student;
import com.softserve.academy.repository.GroupRepository;
import com.softserve.academy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

    private GroupRepository groupRepository;
    private StudentRepository studentRepository;

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String create(ModelMap model) {
         List<Group> groups = groupRepository.getAll();
         model.addAttribute("student", new Student());
         model.addAttribute("groups", groups);
         return "add-student";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String create(@RequestAttribute("student") Student student) {
        studentRepository.create(student);
        return "redirect:/list";
    }

    @RequestMapping(path = "/list")
    public String list(ModelMap model) {
        List<Student> students = studentRepository.getAll();
        model.addAttribute("students", students);
        return "students";
    }
}
