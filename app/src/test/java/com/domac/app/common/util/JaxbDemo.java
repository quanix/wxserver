package com.domac.app.common.util;

import com.domac.app.common.mapper.JaxbMapper;
import com.domac.app.common.xml.Result;
import com.domac.app.common.xml.ResultMapper;
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
        result.setMsgType("xxx");
        result.setMsgId("2222222");
        result.setContent("sdjasjdakshdjashdjahsdj三大sdasdahsd搜索j");
        System.out.println(ResultMapper.toXML(result));
    }

    @Test
    public void xmlToObject() {
        String xml = "<xml>\n" +
                "    <ToUserName><![CDATA[admin]]></ToUserName>\n" +
                "    <FromUserName><![CDATA[lihaoquan]]></FromUserName>\n" +
                "    <CreateTime>1223454</CreateTime>\n" +
                "    <MsgType><![CDATA[xxx]]></MsgType>\n" +
                "    <Content><![CDATA[sdjasjdakshdjashdjahsdj三大sdasdahsd搜索j]]></Content>\n" +
                "    <MsgId>2222222</MsgId>\n" +
                "</xml>";
        Result result = ResultMapper.from(xml);
        System.out.println(result.getContent()+","+result.getToUserName());
    }
}
