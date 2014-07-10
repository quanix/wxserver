package com.domac.app.web.controller;

import com.domac.app.common.util.Encodes;
import com.domac.app.common.util.WxUtils;
import com.domac.app.common.xml.ResponseResult;
import com.domac.app.common.xml.Result;
import com.domac.app.service.TranslateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by lihaoquan
 *
 * BAE微信接口控制器
 */
@Controller
public class WeixinController {

    private static final String WX_TOKEN="domacheck";


    @Autowired
    private TranslateService translateService;


    /**
     * 微信服务器信息接收
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @RequestMapping(value ="/webserver", method = RequestMethod.GET)
    @ResponseBody
    private String getMessage(HttpServletRequest request, HttpServletResponse response,
            String signature,String timestamp,String nonce,String echostr) throws Exception {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

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
    @ResponseBody
    private String postMessage(HttpServletRequest request) throws Exception{
        String postStr=null;
        request.setCharacterEncoding("UTF-8");
        try {
            postStr= WxUtils.readStreamParameter(request.getInputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("postStr="+postStr);

        /**
         * 回复消息
         */
        ResponseResult responseResult =  null;
        if(null != postStr && StringUtils.isNotBlank(postStr)) {
            responseResult = new ResponseResult();
            Result result = Result.from(postStr);
            responseResult.setToUserName(result.getFromUserName());
            responseResult.setFromUserName(result.getToUserName());
            responseResult.setCreateTime(result.getCreateTime());
            responseResult.setMsgType("text");
            responseResult.setContent(translateService.translates(result.getContent()));
        }
        if(null != responseResult) {
            String resultXML = responseResult.toXML();
            System.out.println("回复用户报文:"+resultXML);
            return resultXML;
        }
        return "error";
    }
}
