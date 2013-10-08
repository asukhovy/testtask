package com.codenvy.testtask.qname;

import java.util.regex.*;

public class Parser {

    private static String schemaNonSpace = "[^/:\\[\\]\\*'\"\\|\\s]";
    private static String schemaChar = "(" + schemaNonSpace + "| )";
    private static String schemaOneCharSimpleName = "[^/:\\.\\[\\]\\*'\"\\|\\s]";
    private static String schemaOneCharLocalName = schemaNonSpace;
    private static String schemaTwoCharLocalName = String.format("(%s %s)", schemaNonSpace, schemaNonSpace);
    private static String schemaTwoCharSimpleName = String.format("(\\.%s)|(%s\\.)|(%s %s)", schemaOneCharSimpleName, schemaOneCharSimpleName, schemaOneCharSimpleName, schemaOneCharSimpleName);
    private static String schemaString = String.format("(%s)+", schemaChar);
    private static String schemaThreeOrMoreCharName = String.format("(%s%s%s)", schemaNonSpace, schemaString, schemaNonSpace);
    private static String schemaSimpleName = String.format("(%s|%s|%s)", schemaOneCharSimpleName, schemaTwoCharSimpleName, schemaThreeOrMoreCharName);
    private static String schemaLocalName = String.format("(%s|%s|%s)", schemaOneCharLocalName, schemaTwoCharLocalName, schemaThreeOrMoreCharName);
    private static String schemaPrefix = "([A-Za-z]|_)([\\w\\.\\-]*)";
    private static String schemaPrefixedName = String.format("(%s:%s)", schemaPrefix, schemaLocalName);

    private static Pattern simpleName = Pattern.compile(schemaSimpleName);
    private static Pattern prefixedName = Pattern.compile(schemaPrefixedName);

    public static QName parse(String name) throws IllegalNameException {
        QName toReturn = new QName();
        if (simpleName.matcher(name).matches()) {
            toReturn.localName = name;
            toReturn.prefix = "";
            return toReturn;
        }
        if (prefixedName.matcher(name).matches()) {
            int index = name.indexOf(":");
            toReturn.prefix = name.substring(0,index);
            toReturn.localName = name.substring(index);
            return toReturn;
        }
        throw new IllegalNameException();
    }
}
