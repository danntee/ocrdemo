package com.smsf.allroundscan.Utils;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/5/6 9:15
 */

public class Result_data {
    private Long log_id;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public Excelbean getResult() {
        return result;
    }
    public int getRecstat()
    {
        return this.result.ret_code;
    }
    public void setResult(Excelbean result) {
        this.result = result;
    }
    public String getData()
    {
        return this.result.getResult_data();
    }

    private Excelbean result;
    private class Excelbean{
        private String result_data;
        private int percent;
        private String request_id;
        private int ret_code;
        private String ret_msg;

        public String getResult_data() {
            return result_data;
        }

        public void setResult_data(String result_data) {
            this.result_data = result_data;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public int getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public String getRet_msg() {
            return ret_msg;
        }

        public void setRet_msg(String ret_msg) {
            this.ret_msg = ret_msg;
        }
    }
}
