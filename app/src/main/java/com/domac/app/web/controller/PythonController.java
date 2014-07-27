package com.domac.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
}
