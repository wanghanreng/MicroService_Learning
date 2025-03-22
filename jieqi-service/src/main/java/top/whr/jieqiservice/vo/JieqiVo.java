package top.whr.jieqiservice.vo;

import lombok.Data;

@Data
public class JieqiVo {
    private Result result;

    @Data
    public static class Result {
        private String name;
        private String nameimg; // 图片名称
        private String day; // 日期
        private DateData date; // 日期数据
        private String yuanyin; // 主要节日
        private String shiju; // 诗句
        private String jieshao; // 详细介绍
        private String xishu; // 习俗
        private String meishi; // 美食
        private String yiji; // 相关注意事项

        @Data
        public static class DateData {
            private String gregdate; // 公历
            private String lunardate; // 农历
            private String cnyear; // 中国年
            private String cnmonth; // 中文月份
            private String cnday; // 中文日期
            private String cnzodiac; // 生肖
        }
    }
}