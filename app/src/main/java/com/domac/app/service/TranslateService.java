package com.domac.app.service;


import com.domac.app.common.json.Basic;
import com.domac.app.common.json.DictResult;
import com.domac.app.common.json.Web;
import com.domac.app.common.mapper.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author : lihaoquan
 *
 * 翻译服务类
 */
public class TranslateService {

    //数据获取连接
    private String dataUrl;

    @Autowired
    private RestTemplate restTemplate;

    private static JsonMapper mapper = JsonMapper.nonDefaultMapper();


    /**
     * 翻译函数
     * @param text
     * @return
     */
    public String translates(String text) {
        String world = "Sorry,我只懂翻译英文";
        String result = restTemplate.getForObject(dataUrl + text, String.class);
        DictResult dictResult = mapper.fromJson(result,DictResult.class);
        List<Web> webs = dictResult.getWeb();
        if(null!=webs) {
            Web web = webs.get(0);
            List<String> values = web.getValue();
            if(null != values) {
                world = values.get(0);
            }
        }
        return world;
    }


    /**
     * 页面翻译
     * @param word
     * @return
     */
    public String pageTranslate(String word) {
        String data = "没有查询结果";
        String result = restTemplate.getForObject(dataUrl + word, String.class);
        DictResult dictResult = mapper.fromJson(result,DictResult.class);

        Basic basic = dictResult.getBasic();
        if(null != basic) {
            data = "";
            List<String> explains = basic.getExplains();
            for(String explain : explains) {
                data+=explain+"<br/>";
            }
        }
        return data;
    }


    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}
