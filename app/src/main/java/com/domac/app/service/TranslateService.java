package com.domac.app.service;


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


    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}
