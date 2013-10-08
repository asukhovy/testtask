package com.codenvy.testtask.qname;

import org.junit.Test;

import static org.junit.Assert.fail;

public class ParserTest {

    public void validTestTemplate(String name) {

        try {
            QName qName = Parser.parse(name);
        } catch (IllegalNameException e) {
            fail();
        }
    }

    public void notValidTestTemplate(String name) {

        try {
            QName qName = Parser.parse(name);
            fail();
        } catch (IllegalNameException e) {

        }
    }

    @Test
    public void testParse() throws Exception {

        validTestTemplate("name");
        validTestTemplate("prefix:name");
        validTestTemplate("prefix:na me");

        notValidTestTemplate("");
        notValidTestTemplate(":name");
        notValidTestTemplate(".");
        notValidTestTemplate("..");
        notValidTestTemplate("prefix:");
        notValidTestTemplate(" name");
        notValidTestTemplate(" prefix:name");
        notValidTestTemplate("prefix: name");
        notValidTestTemplate("prefix:name ");
        notValidTestTemplate("pre fix:name");
        notValidTestTemplate("name/name");
        notValidTestTemplate("name[name");
        notValidTestTemplate("prefix:name:name");
    }
}
