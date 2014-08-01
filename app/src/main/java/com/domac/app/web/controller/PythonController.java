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

        Iterator iterator = dataMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
        }


        String process_list = dataMap.get("process_list").toString();
        if (process_list != null) {
            String[] ps = process_list.split("\n");
            for (int i = 1; i < ps.length; i++) {
                System.out.println(ps[i]);
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
}
