package top.whr.jieqiservice.service;

import top.whr.jieqiservice.vo.JieqiVo;

public interface JieqiService {
    JieqiVo getJieqiInfo(String word, int year);
}
