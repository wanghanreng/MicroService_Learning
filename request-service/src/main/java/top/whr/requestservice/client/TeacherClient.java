package top.whr.requestservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import top.whr.requestservice.entity.Teacher;

import java.util.List;

@FeignClient(name = "python-service", url = "http://127.0.0.1:5050") // Flask 服务的地址
public interface TeacherClient {

    @GetMapping("/teacher")
    List<Teacher> getTeachers();

    @PostMapping("/teacher")
    Teacher addTeacher(@RequestBody Teacher teacher);

    @PutMapping("/teacher/{id}")
    Teacher updateTeacher(@PathVariable("id") int teacherId, @RequestBody Teacher teacher);

    @DeleteMapping("/teacher/{id}")
    void deleteTeacher(@PathVariable("id") int teacherId);
}
