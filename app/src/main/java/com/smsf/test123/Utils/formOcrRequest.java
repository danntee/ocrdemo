package com.smsf.test123.Utils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/4/30 13:39
 */

public class formOcrRequest {
    /**
     * result : [{"request_id":"1234_6789"}]
     * log_id : 149689853984104
     */

    private long log_id;
    private List<ResultBean> result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * request_id : 1234_6789
         */

        private String request_id;

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }
    }
}