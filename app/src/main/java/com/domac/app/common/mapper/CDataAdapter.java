package com.domac.app.common.mapper;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * created by lihaoquan
 */
public class CDataAdapter extends XmlAdapter<String,String> {

    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }

    @Override
    public String marshal(String v) throws Exception {
        return "<![CDATA[" + v+ "]]>";
    }
}
