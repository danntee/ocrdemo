package com.smsf.test123.Utils;

import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/5/6 9:31
 */

public class Exceldata {
    /**
     * form_num : 1
     * forms : [{"footer":[],"header":[{"rect":{"top":36,"left":26,"width":71,"height":27},"column":[1],"row":[1],"word":"剪切"},{"rect":{"top":169,"left":44,"width":42,"height":22},"column":[1],"row":[2],"word":"等线"},{"rect":{"top":380,"left":48,"width":46,"height":22},"column":[1],"row":[3],"word":"√1"},{"rect":{"top":35,"left":65,"width":98,"height":29},"column":[1],"row":[4],"word":"复制"},{"rect":{"top":0,"left":85,"width":17,"height":23},"column":[1],"row":[5],"word":"贴"},{"rect":{"top":176,"left":96,"width":115,"height":22},"column":[1],"row":[6],"word":"B"},{"rect":{"top":8,"left":146,"width":60,"height":23},"column":[1],"row":[7],"word":"剪贴板"},{"rect":{"top":326,"left":150,"width":42,"height":22},"column":[1],"row":[8],"word":"字体"},{"rect":{"top":42,"left":253,"width":15,"height":21},"column":[1],"row":[9],"word":"A"},{"rect":{"top":146,"left":254,"width":13,"height":21},"column":[1],"row":[10],"word":"B"},{"rect":{"top":349,"left":256,"width":16,"height":22},"column":[1],"row":[11],"word":"D"},{"rect":{"top":74,"left":282,"width":22,"height":21},"column":[1],"row":[12],"word":"11"},{"rect":{"top":175,"left":280,"width":66,"height":26},"column":[1],"row":[13],"word":"22333"}],"body":[{"rect":{"top":448,"left":4,"width":100,"height":26},"column":[1],"row":[1],"word":""},{"rect":{"top":474,"left":4,"width":100,"height":26},"column":[1],"row":[2],"word":""},{"rect":{"top":500,"left":4,"width":100,"height":24},"column":[1],"row":[3],"word":""}]}]
     */

    private int form_num;
    private List<FormsBean> forms;

    public int getForm_num() {
        return form_num;
    }

    public void setForm_num(int form_num) {
        this.form_num = form_num;
    }

    public List<FormsBean> getForms() {
        return forms;
    }

    public void setForms(List<FormsBean> forms) {
        this.forms = forms;
    }

    public  class FormsBean {
        private List<?> footer;
        private List<HeaderBean> header;
        private List<BodyBean> body;

        public List<?> getFooter() {
            return footer;
        }

        public void setFooter(List<?> footer) {
            this.footer = footer;
        }

        public List<HeaderBean> getHeader() {
            return header;
        }

        public void setHeader(List<HeaderBean> header) {
            this.header = header;
        }

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public  class HeaderBean {
            /**
             * rect : {"top":36,"left":26,"width":71,"height":27}
             * column : [1]
             * row : [1]
             * word : 剪切
             */

            private RectBean rect;
            private String word;
            private List<Integer> column;
            private List<Integer> row;

            public RectBean getRect() {
                return rect;
            }

            public void setRect(RectBean rect) {
                this.rect = rect;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
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

            public  class RectBean {
                /**
                 * top : 36
                 * left : 26
                 * width : 71
                 * height : 27
                 */

                private int top;
                private int left;
                private int width;
                private int height;

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
            }
        }

        public  class BodyBean {
            /**
             * rect : {"top":448,"left":4,"width":100,"height":26}
             * column : [1]
             * row : [1]
             * word :
             */

            private RectBeanX rect;
            private String word;
            private List<Integer> column;
            private List<Integer> row;

            public RectBeanX getRect() {
                return rect;
            }

            public void setRect(RectBeanX rect) {
                this.rect = rect;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
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

            public class RectBeanX {
                /**
                 * top : 448
                 * left : 4
                 * width : 100
                 * height : 26
                 */

                private int top;
                private int left;
                private int width;
                private int height;

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
            }
        }
    }
}
