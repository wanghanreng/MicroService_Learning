package top.whr.aiservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Value;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class AIService {

    private static final String API_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1/chat/completions"; // 替换为实际的API URL
    private static final String API_KEY = "sk-9b8d024c87934feca1627a545700a2d9"; // 替换为实际的API密钥

    public String getAIResponse(String question) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String json = "{\"question\": \"" + question + "\"}";
        RequestBody body = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}