package com.domac.app.common.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by lihaoquan
 */
public class JsonMapperTest {

    private static JsonMapper binder = JsonMapper.nonDefaultMapper();

    /**
     * 从Json字符串反序列化对象/集合.
     */
    @Test
    public void fromJson() throws Exception {
        // Bean
        String beanString = "{\"name\":\"A\"}";
        TestBean bean = binder.fromJson(beanString, TestBean.class);
        System.out.println("Bean:" + bean);

        // Map
        String mapString = "{\"name\":\"A\",\"age\":2}";
        Map<String, Object> map = binder.fromJson(mapString, HashMap.class);
        System.out.println("Map:");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        // List<String>
        String listString = "[\"A\",\"B\",\"C\"]";
        List<String> stringList = binder.getMapper().readValue(listString, List.class);
        System.out.println("String List:");
        for (String element : stringList) {
            System.out.println(element);
        }

        // List<Bean>
        String beanListString = "[{\"name\":\"A\"},{\"name\":\"B\"}]";
        List<TestBean> beanList = binder.getMapper().readValue(beanListString, new TypeReference<List<TestBean>>() {
        });
        System.out.println("Bean List:");
        for (TestBean element : beanList) {
            System.out.println(element);
        }
    }

    public static class TestBean {

        private String name;
        private String defaultValue = "hello";
        private String nullValue = null;

        public TestBean() {
        }

        public TestBean(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getNullValue() {
            return nullValue;
        }

        public void setNullValue(String nullValue) {
            this.nullValue = nullValue;
        }

        @Override
        public String toString() {
            return "TestBean [defaultValue=" + defaultValue + ", name=" + name + ", nullValue=" + nullValue + "]";
        }
    }
}
