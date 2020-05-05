package com.smsf.test123;

/**
 * @Description: java类作用描述
 * @Author: liys
 * @CreateDate: 2020/4/30 10:27
 */

import com.baidu.aip.util.Base64Util;


import com.smsf.test123.Jutils.FileUtil;
import com.smsf.test123.Jutils.HttpUtil;


import java.net.URLEncoder;

/**
 * 表格文字识别(异步接口)
 */
public class FormOcrResult {

    /**
     * 重要提示代码中所需工具类
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * 下载
     */
    public static String formOcrResult(String token,String id) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/solution/v1/form_ocr/get_request_result";
        try {

            String resid = id;

            String param = "request_id=" +"19571077_1723166&result_type=json";

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = token;

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}