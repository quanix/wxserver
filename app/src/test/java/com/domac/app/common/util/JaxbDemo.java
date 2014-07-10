package com.domac.app.common.util;

import com.domac.app.common.xml.ResponseResult;
import com.domac.app.common.xml.Result;
import org.junit.Test;

/**
 * created by lihaoquan
 */
public class JaxbDemo {

    @Test
    public void objectToXML() {
        Result result = new Result();
        result.setToUserName("admin");
        result.setFromUserName("lihaoquan");
        result.setCreateTime("1223454");
        result.setMsgId("2222222");
        result.setMsgType(null);
        result.setContent("sdjasjdakshdjashdjahsdj三大sdasdahsd搜索j");
        System.out.println(result.toXML());
    }

    @Test
    public void xmlToObject() {
        String xml = "<xml>\n" +
                "    <ToUserName><![CDATA[idomac]]></ToUserName>\n" +
                "    <FromUserName><![CDATA[lihaoquan]]></FromUserName>\n" +
                "    <CreateTime>1223454</CreateTime>\n" +
                "    <MsgType><![CDATA[xxx]]></MsgType>\n" +
                "    <Content><![CDATA[sdjasjdakshdjashdjahsdj三大sdasdahsd搜索j]]></Content>\n" +
                "    <MsgId>2222222</MsgId>\n" +
                "</xml>";
        Result result = Result.from(xml);
        System.out.println(result.getContent()+","+result.getToUserName()+"|||"+result.getMsgType());


        ResponseResult responseResult = new ResponseResult();

        System.out.print(responseResult.toXML());

    }
}
