package com.zhangpengblog.mybaselibrary.bean;

public class DateBean {

    /**
     * code : 0
     * type : {"type":0,"name":"周六","week":1}
     * holiday : {"holiday":false,"after":false,"target":"国庆节"}
     */

    private int code;  // 0服务正常。-1服务出错
    private TypeBean type;
    private HolidayBean holiday;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public TypeBean getType() {
        return type;
    }

    public void setType(TypeBean type) {
        this.type = type;
    }

    public HolidayBean getHoliday() {
        return holiday;
    }

    public void setHoliday(HolidayBean holiday) {
        this.holiday = holiday;
    }

    public static class TypeBean {
        /**
         * type : 0
         * name : 周六
         * week : 1
         */

        private int type;   // 节假日类型，分别表示 工作日、周末、节日、调休。
        private String name;  // 节假日类型中文名，可能值为 周一 至 周日、假期的名字、某某调休。
        private int week;   // 一周中的第几天。值为 1 - 7，分别表示 周一 至 周日。

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }
    }

    public static class HolidayBean {
        /**
         * holiday : false
         * after : false
         * target : 国庆节
         */

        private boolean holiday;  // true表示是节假日，false表示是调休。
        private boolean after;    // 只在调休下有该字段。true表示放完假后调休，false表示先调休再放假。
        private String target;    // 只在调休下有该字段。表示调休的节假日。

        public boolean isHoliday() {
            return holiday;
        }

        public void setHoliday(boolean holiday) {
            this.holiday = holiday;
        }

        public boolean isAfter() {
            return after;
        }

        public void setAfter(boolean after) {
            this.after = after;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}
