package com.domac.app.common.xml;

import com.domac.app.common.mapper.CDataAdapter;
import com.domac.app.common.mapper.JaxbMapper;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * created by lihaoquan
 */
@XmlRootElement(name = "xml")
@XmlType(propOrder = {"toUserName", "fromUserName", "createTime", "msgType","content"})
public class ResponseResult {

    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String msgType;

    private String content;

    @XmlElement(name = "ToUserName")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlElement(name = "FromUserName")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @XmlElement(name = "CreateTime")
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @XmlElement(name = "MsgType")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getMsgType() {
        if(msgType == null || StringUtils.isBlank(msgType)) {
            msgType = "text";
        }
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @XmlElement(name = "Content")
    @XmlJavaTypeAdapter(CDataAdapter.class)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    /**
     * 转化为XML格式
     * @return
     */
    public String toXML() {
        String xml = JaxbMapper.toXml(this, "UTF-8");
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>","").trim();
        return xml;
    }

    public static ResponseResult from(String xml) {
        ResponseResult responseResult = JaxbMapper.fromXml(xml,ResponseResult.class);
        return responseResult;
    }

}
