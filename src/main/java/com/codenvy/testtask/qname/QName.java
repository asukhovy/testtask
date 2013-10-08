package com.codenvy.testtask.qname;

public class QName {
    String prefix = null;
    String localName = null;

    String getPrefix() {
        return prefix;
    }

    String getLocalName() {
        return localName;
    }

    String getAsString() {
        return ((( prefix != null && prefix.equals("") )?( prefix + ":" ):( "" )) + localName);
    }
}
