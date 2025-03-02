package top.whr.aiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.whr.aiservice.service.AIService;

@RestController
@RequestMapping("/api")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("/ask")
    public String askAI(@RequestBody String question) {
        return aiService.getAIResponse(question);
    }
}