package com.pojo;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yantao.chen
 * @date 2017/11/7.
 */
public class pojoTest {
    public static void main(String[] args) {
//        String jsonStr = "{\"status\":1,\"msg\":\"哈哈\",\"data\":null,\"ages\":4,\"price\":1.234}";
        String jsonStr = "{\"data\":{\"feedbacks\":{\"feedbacklist\":[{\"comment\":\"5分\",\"createtime\":\"2016.09.07 12:38\",\"score\":5,\"username\":\"1331##11\"}],\"totalcount\":1,\"totalscore\":5},\"liketeamlist\":[{\"limage\":\"http://baidu.com.465.jpg\",\"lmarketprice\":199,\"lteamId\":386,\"lteamprice\":38,\"ltitle\":\"我才是测试文本哦,用于测试此次验证。\"},{\"limage\":\"http://baidu.com/37.jpg\",\"lmarketprice\":3380,\"lteamId\":57133,\"lteamprice\":580,\"ltitle\":\"测试文本,15级软件开发！\"}],\"partnerteamlist\":[{\"pteamId\":35,\"pteamprice\":228,\"ptitle\":\"计算机应用专业。\"},{\"pteamId\":72598,\"pteamprice\":2888,\"ptitle\":\"潍坊职业学院。\"},{\"pteamId\":3613,\"pteamprice\":499,\"ptitle\":\"2015级！\"},{\"pteamId\":72638,\"pteamprice\":4299,\"ptitle\":\"本次测试于16年9月7日。\"},{\"pteamId\":716,\"pteamprice\":38,\"ptitle\":\"后期持续更新！\"}]},\"state\":1,\"err\":null}";
        JSONObject json = jsonStrToJsonObj(jsonStr);
        List<String> list = new ArrayList<>();
        list.add("data");
        jsonTest jsonTest = new jsonTest(json,list);
        System.out.println(jsonTest);
    }

    public static String UpperFirstLetter(String str){
        return str.substring(0,1).toUpperCase().concat(str.substring(1).toLowerCase());
    }

    /**
     * json字符串转成JSONObject对象
     *
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static JSONObject jsonStrToJsonObj(String str) {
        JSONObject obj = null;
        if (isJson(str)) {
            obj = JSONObject.fromObject(str);
        }
        return obj;
    }

    public static boolean isJson(String s) {
        if (s != null && s.startsWith("{") && s.endsWith("}")) {
            return true;
        }
        return false;
    }
}
