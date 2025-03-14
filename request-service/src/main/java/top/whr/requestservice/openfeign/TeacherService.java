package top.whr.requestservice.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.whr.requestservice.client.TeacherClient;
import top.whr.requestservice.entity.Teacher;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherService {

    @Autowired
    private TeacherClient teacherClient;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherClient.getTeachers();
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherClient.addTeacher(teacher);
    }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        return teacherClient.updateTeacher(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherClient.deleteTeacher(id);
    }
}