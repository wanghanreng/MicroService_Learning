package top.whr.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import top.whr.userservice.config.DeepSeekConfig;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class DeepSeekController {

    private final RestTemplate restTemplate2;

    @Autowired
    private DeepSeekConfig deepSeekConfig;

    public DeepSeekController(RestTemplate restTemplate2) {
        this.restTemplate2 = restTemplate2;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askDeepSeek(@RequestBody String question) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + deepSeekConfig.getKey()); // 确保使用正确的认证方式

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", deepSeekConfig.getModelName()); // 从配置读取模型名称
        requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", question);
                }}
        });

        try {
            String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
            HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

            ResponseEntity<String> response = restTemplate2.postForEntity(
                    deepSeekConfig.getUrl() + "/v1/chat/completions",
                    entity,
                    String.class
            );

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            log.error("API调用失败: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("{\"error\": \"请求参数格式错误: " + e.getMessage() + "\"}");
        }
    }
}