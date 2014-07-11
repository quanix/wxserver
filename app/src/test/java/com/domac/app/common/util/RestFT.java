package com.domac.app.common.util;

import com.domac.app.common.json.DictResult;
import com.domac.app.common.json.Web;
import com.domac.app.common.mapper.JsonMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.client.RestTemplate;

import java.util.List;


/**
 * @author : lihaoquan
 */
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class RestFT  extends AbstractJUnit4SpringContextTests  {

    @Autowired
    private RestTemplate restTemplate;

    private static JsonMapper mapper = JsonMapper.nonDefaultMapper();

    @Test
    public void testFT() {

        String world = "Sorry,我只懂翻译英文";

        String query = "cup";

        String result = restTemplate
                .getForObject("http://fanyi.youdao.com/openapi.do?" +
                                "keyfrom=idomac&key=1193139685&type=data&doctype=json&version=1.1&q="+query,
                        String.class
                );

        System.out.println(result);

        DictResult dictResult = mapper.fromJson(result,DictResult.class);

        List<Web> webs = dictResult.getWeb();

        if(null!=webs) {
            Web web = webs.get(0);
            List<String> values = web.getValue();

            if(null != values) {
                world = values.get(0);
            }
        }
        System.out.println(world);
    }
}
