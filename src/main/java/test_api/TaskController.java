package test_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskController {

    @Value("${app.message}")
    private String appMessage;
    private final List<String> tasks = new ArrayList<>();
    public TaskController() {
        tasks.add("Learn Docker");
        tasks.add("Complete CENG413 homework");
    }
    @GetMapping("/health")
    public String health() {
        return appMessage;
    }
    @GetMapping("/tasks")
    public List<String> getTasks() {
        return tasks;
    }
    @PostMapping("/tasks")
    public List<String> addTask(@RequestBody String task) {
        tasks.add(task);
        return tasks;
    }

}