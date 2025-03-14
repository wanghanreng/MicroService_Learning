package top.whr.userservice.result;

import lombok.Data;

@Data
public class Result<T> {
    private int code;          // 状态码
    private String message;    // 消息
    private T data;           // 数据

    // 成功时的响应
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);  // 成功状态码
        result.setMessage("成功");  // 成功消息
        result.setData(data); // 返回的数据
        return result;
    }

    // 失败时的响应
    public static <T> Result<T> failure(String message, int code) {
        Result<T> result = new Result<>();
        result.setCode(code);   // 失败状态码
        result.setMessage(message); // 失败消息
        result.setData(null);    // 数据为 null
        return result;
    }
}
