package top.whr.jieqiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.whr.jieqiservice.service.JieqiService;
import top.whr.jieqiservice.vo.JieqiVo;

@RestController
public class JieqiController {

    @Autowired
    private JieqiService jieqiService;

    @GetMapping("/jieqi")
    public JieqiVo getJieqi(@RequestParam String word, @RequestParam int year) {
        return jieqiService.getJieqiInfo(word, year);
    }
}
