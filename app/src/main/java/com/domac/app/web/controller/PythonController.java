package com.domac.app.web.controller;

import com.domac.app.common.json.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : lihaoquan
 */
@Controller
public class PythonController {

    @RequestMapping(value ="/update_script.do", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,String> update_script() {
        Map<String,String> map = new HashMap<String, String>();
        map.put("conf","Jackskdsd111111111111");
        map.put("script","sds666666666666666");
        return map;
    }

    /**
     * 数据上报
     * @param data
     */
    @RequestMapping(value = "/dataReport.do")
    @ResponseBody
    private void dataReport(String data) {
        System.out.println(data);
        Map<String,Object> dataMap = JsonUtil.toBean(data,HashMap.class);

        String sn_1 = getValue(dataMap, "sn_1", true);

        //Hi
        System.out.println("sn_1===>"+sn_1);

        String density_name = getValue(dataMap, "density_name", false);

        System.out.println("density_name====>"+density_name);


        String process_list = dataMap.get("process_list").toString();
        if (process_list != null) {
            String[] ps = process_list.split("\n");
            for (int i = 1; i < ps.length; i++) {
                String[] items = ps[i].split("\t");
                Map<String, String> itemMap = new HashMap<String, String>();
                itemMap.put("uid", items[1]);
                itemMap.put("pid", items[2]);
                itemMap.put("cmd", items[3]);
            }
        }

        String ip = dataMap.get("ip").toString();
        if (!StringUtils.isEmpty(ip)) {
            String[] _ips = ip.split("\n");
            for (String _ip : _ips) {
                if (_ip.indexOf("127.0.0.1") >= 0) {
                    continue;
                }
                _ip = _ip.trim();
                System.out.println("_ip=="+_ip);
            }
        }
    }




    private static String getValue(Map<String, Object> dataMap, String key,
                                   boolean colon) {
        Object value = dataMap.get(key);
        if (value == null) {
            return null;
        }
        String _value = value.toString();
        if (StringUtils.isEmpty(_value)) {
            return "";
        }
        try {
            // String _value = new
            // String(Base64.decodeBase64(value.toString())).trim();
            if (colon && _value != null) {
                int pos = _value.indexOf(":");
                if (pos > 0) {
                    _value = _value.substring(pos + 1).trim();
                    dataMap.put(key, _value);
                }
            }
            return _value;
        } catch (Exception e) {
            return null;
        }
    }
}
