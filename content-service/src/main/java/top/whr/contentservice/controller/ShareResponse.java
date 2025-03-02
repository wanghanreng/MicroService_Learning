package top.whr.contentservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.whr.contentservice.entity.Share;
import top.whr.contentservice.vo.UserVO;

@Data
@AllArgsConstructor
public class ShareResponse {
    private Share share;
    private UserVO author;
}