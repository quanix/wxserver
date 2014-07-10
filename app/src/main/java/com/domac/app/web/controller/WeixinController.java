package com.domac.app.web.controller;

import com.domac.app.common.util.Digests;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Arrays;

/**
 * created by lihaoquan
 *
 * 微信接口控制器
 */
@Controller
public class WeixinController {

    private static final String WX_TOKEN="domacheck";

    @RequestMapping(value ="/webserver", method = RequestMethod.GET)
    @ResponseBody
    private String getMessage(HttpServletResponse response,String signature,String timestamp,String nonce,String echostr) {

        System.out.println("signature>>>>>>>>>>>>"+signature);
        System.out.println("timestamp>>>>>>>>>>>>"+timestamp);
        System.out.println("nonce>>>>>>>>>>>>"+nonce);
        System.out.println("echostr>>>>>>>>>>>>"+echostr);

        String[] arrays = new String[]{WX_TOKEN,timestamp,nonce};
        Arrays.sort(arrays);

        String bigStr = arrays[0] + arrays[1] + arrays[2];        // SHA1加密
        String digest = Digests.sha1(bigStr.getBytes()).toString();

        System.out.println("[digest]>>>>>>>>>>>>"+digest);

        return echostr;
    }


    @RequestMapping(value ="/webserver", method = RequestMethod.POST)
    private String postMessage(InputStream inputStream) {
        if(inputStream!=null) {
            System.out.println("inputStream is not null");
        }else {
            System.out.println("inputStream is null");
        }
        return "xxxxx";
    }
}
