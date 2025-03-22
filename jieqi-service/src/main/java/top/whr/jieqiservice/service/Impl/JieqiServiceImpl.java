package top.whr.jieqiservice.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.whr.jieqiservice.service.JieqiService;
import top.whr.jieqiservice.vo.JieqiVo;

@Service
public class JieqiServiceImpl implements JieqiService {

    private static final String API_KEY = "d3175447f8a94cfb526d25e2cbdb0601";
    private static final String API_URL_FORMAT = "https://apis.tianapi.com/jieqi/index?key=%s&word=%s&year=%d";

    @Override
    public JieqiVo getJieqiInfo(String word, int year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(API_URL_FORMAT, API_KEY, word, year);
        return restTemplate.getForObject(url, JieqiVo.class);
    }
}