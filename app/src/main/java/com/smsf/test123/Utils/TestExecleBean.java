package com.smsf.test123.Utils;

import java.io.Serializable;
import java.util.List;

public class TestExecleBean implements Serializable {

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public TestExecle getResult() {
        return result;
    }

    public void setResult(TestExecle result) {
        this.result = result;
    }

    private String log_id;
    private TestExecle result;

    private class TestExecle {

        public String getRequest_id() {
            return request_id;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public String getRet_msg() {
            return ret_msg;
        }

        public void setRet_msg(String ret_msg) {
            this.ret_msg = ret_msg;
        }

        public float getRet_code() {
            return ret_code;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public ExecleBean getResult_data() {
            return result_data;
        }

        public void setResult_data(ExecleBean result_data) {
            this.result_data = result_data;
        }

        private String request_id;
        private String ret_msg;
        private float ret_code;
        private int percent;
        private ExecleBean result_data;

    }

    private class ExecleBean {
        public int getForm_num() {
            return form_num;
        }

        public void setForm_num(int form_num) {
            this.form_num = form_num;
        }

        public List<ExecleFooter> getForms() {
            return forms;
        }

        public void setForms(List<ExecleFooter> forms) {
            this.forms = forms;
        }

        private int form_num;
        private List<ExecleFooter> forms;
    }

    public class ExecleFooter {

        public List<Footer> getFooter() {
            return footer;
        }

        public void setFooter(List<Footer> footer) {
            this.footer = footer;
        }

        public List<Header> getHeader() {
            return header;
        }

        public void setHeader(List<Header> header) {
            this.header = header;
        }

        public List<Body> getBody() {
            return body;
        }

        public void setBody(List<Body> body) {
            this.body = body;
        }

        private List<Footer> footer;
        private List<Header> header;
        private List<Body> body;

    }

    public class Footer {
        public List<Rect> getRect() {
            return rect;
        }

        public void setRect(List<Rect> rect) {
            this.rect = rect;
        }

        public List<Integer> getColumn() {
            return column;
        }

        public void setColumn(List<Integer> column) {
            this.column = column;
        }

        public List<Integer> getRow() {
            return row;
        }

        public void setRow(List<Integer> row) {
            this.row = row;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        private List<Rect> rect;
        private List<Integer> column;
        private List<Integer> row;
        private String word;
    }

    public class Header {
        public List<Integer> getColumn() {
            return column;
        }

        public void setColumn(List<Integer> column) {
            this.column = column;
        }

        public float getProbability() {
            return probability;
        }

        public void setProbability(float probability) {
            this.probability = probability;
        }

        public List<Rect> getRect() {
            return rect;
        }

        public void setRect(List<Rect> rect) {
            this.rect = rect;
        }

        public List<Integer> getRow() {
            return row;
        }

        public void setRow(List<Integer> row) {
            this.row = row;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        private List<Integer> column;
        private float probability;
        private List<Rect> rect;
        private List<Integer> row;
        private String word;
    }

    public class Body {
        public List<Rect> getRect() {
            return rect;
        }

        public void setRect(List<Rect> rect) {
            this.rect = rect;
        }

        public List<Integer> getColumn() {
            return column;
        }

        public void setColumn(List<Integer> column) {
            this.column = column;
        }

        public List<Integer> getRow() {
            return row;
        }

        public void setRow(List<Integer> row) {
            this.row = row;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        private List<Rect> rect;
        private List<Integer> column;
        private List<Integer> row;
        private String word;
    }

    public class Rect {
        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        private int top;
        private int left;
        private int width;
        private int height;

    }

}
