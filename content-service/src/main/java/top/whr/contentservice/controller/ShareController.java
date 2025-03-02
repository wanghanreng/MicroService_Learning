package top.whr.contentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.whr.contentservice.client.UserClient;
import top.whr.contentservice.entity.Share;
import top.whr.contentservice.service.ShareService;
import top.whr.contentservice.vo.UserVO;

@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public ShareResponse getShare(@PathVariable Integer id) {
        Share share = shareService.getShareById(id);
        UserVO userVo = userClient.getUser(share.getUserId());

        return new ShareResponse(share, userVo);
    }
}