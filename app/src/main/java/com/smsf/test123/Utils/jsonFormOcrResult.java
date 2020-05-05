package com.smsf.test123.Utils;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/4/30 15:55
 */

public class jsonFormOcrResult {

    /**
     * result : {"result_data":"","ret_msg":"未开始","request_id":"19571077_1723131","percent":0,"ret_code":1}
     * log_id : 1588233075019384
     */

    private ResultBean result;
    private long log_id;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public static class ResultBean {
        /**
         * result_data :
         * ret_msg : 未开始
         * request_id : 19571077_1723131
         * percent : 0
         * ret_code : 1
         */

        private String result_data;
        private String ret_msg;
        private String request_id;
        private int percent;
        private int ret_code;

        public String getResult_data() {
            return result_data;
        }

        public void setResult_data(String result_data) {
            this.result_data = result_data;
        }

        public String getRet_msg() {
            return ret_msg;
        }

        public void setRet_msg(String ret_msg) {
            this.ret_msg = ret_msg;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }
    }
}
