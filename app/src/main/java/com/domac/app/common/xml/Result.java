package com.domac.app.common.xml;

import com.domac.app.common.mapper.CDataAdapter;
import com.domac.app.common.mapper.JaxbMapper;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * created by lihaoquan
 *
 * 文本信息结果
 */
@XmlRootElement(name = "xml")
@XmlType(propOrder = { "toUserName", "fromUserName", "createTime", "msgType","content","msgId" })
public class Result {

    private String toUserName;

    private String fromUserName;

    private String createTime;

    private String msgType;

    private String content;

    private String msgId;

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

    @XmlElement(name = "MsgId")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }


    /**
     * 转化为XML格式
     * @return
     */
    public String toXML() {
        String xml = JaxbMapper.toXml(this, "UTF-8");
        xml = xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>","").trim();
        xml = xml.replace("&lt;","<");
        xml = xml.replace("&gt;",">");
        return xml;
    }

    public static Result from(String xml) {
        Result result = JaxbMapper.fromXml(xml,Result.class);
        return result;
    }
}
