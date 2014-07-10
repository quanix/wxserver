package com.domac.app.web.controller;

import com.domac.app.common.util.Digests;
import com.domac.app.common.util.WxUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     * 微信服务器信息接收
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value ="/webserver", method = RequestMethod.GET)
    @ResponseBody
    private String getMessage(HttpServletResponse response,
        String signature,String timestamp,String nonce,String echostr) {

        System.out.println("signature>>>>>>>>>>>>"+signature);
        System.out.println("timestamp>>>>>>>>>>>>"+timestamp);
        System.out.println("nonce>>>>>>>>>>>>"+nonce);
        System.out.println("echostr>>>>>>>>>>>>"+echostr);

        if(WxUtils.checkSignature(WX_TOKEN,signature,timestamp,nonce)) {
            return echostr;
        }else {
            return "error";
        }
    }

    /**
     * 文本信息发送
     * @param request
     * @return
     */
    @RequestMapping(value ="/webserver", method = RequestMethod.POST)
    private String postMessage(HttpServletRequest request){
        String postStr=null;
        try {
            postStr= WxUtils.readStreamParameter(request.getInputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("postStr="+postStr);
        return "xxxxx";
    }
}
