package com.example.democore;

public class XMLConfigBean {

    private final MyRepository myRepository;
    private final MyService myService;

    private String value;

    private XmlBeanTwo xmlBeanTwo;

    public void setXmlBeanTwo(XmlBeanTwo xmlBeanTwo) {
        this.xmlBeanTwo = xmlBeanTwo;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public XMLConfigBean(MyRepository myRepository, MyService myService) {
        this.myRepository = myRepository;
        this.myService = myService;
    }

    void init() {
        System.out.println("xml init called");
        System.out.println("xml value: " + xmlBeanTwo.getText());
    }

    void destroy() {
        System.out.println("xml destroy called");
    }
}

class XmlBeanTwo {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
