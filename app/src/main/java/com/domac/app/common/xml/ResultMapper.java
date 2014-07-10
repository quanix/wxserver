package com.domac.app.common.xml;

import com.domac.app.common.mapper.JaxbMapper;

/**
 * created by lihaoquan
 */
public class ResultMapper {
    /**
     * 转化为XML格式
     * @return
     */
    public static String toXML(Result result) {
        String xml = JaxbMapper.toXml(result, "UTF-8");
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>","").trim();
        return xml;
    }

    public static Result from(String xml) {
        Result result = JaxbMapper.fromXml(xml,Result.class);
        return result;
    }
}
