package com.jakub;

import javax.swing.plaf.ButtonUI;

public class CodeGenerator {

    private static final String CLASS_HEADER="class ";
    private static final String DEFINITION_OPEN ="{";
    private static final String DEFINITION_CLOSE ="}";
    private static final String PRIVATE_MODIFIER="private";
    private static final String PUBLIC_MODIFIER="public";
    private static final String SPACE=" ";
    private static final String EQUALS_SIGN="=";
    private static final String SEMICOLON=";";
    private static final String NEWLINE="\n";
    private static final String SETTER_PREFIX="set";
    private static final String GETTER_PREFIX="get";
    private static final String OPEN_BRACKET="(";
    private static final String CLOSE_BRACKET=")";
    private static final String VOID="void";
    private static final String THIS_DOT="this.";
    private static final String RETURN="return";
    private static final String TAB="\t";






    public String createClassString(ClassDefinition classDefinition){
        if(classDefinition == null)
            throw new IllegalArgumentException();

        var stringBuilder = new StringBuilder();
        stringBuilder.append(CLASS_HEADER);
        stringBuilder.append(classDefinition.getName());
        stringBuilder.append(DEFINITION_OPEN);
        stringBuilder.append(NEWLINE);


        var attributes = classDefinition.getAttributes();
        attributes.forEach(attribute ->
                {
                    var attributeString = PRIVATE_MODIFIER + SPACE + attribute.getType() + SPACE +attribute.getName();
                    if(attribute.getDefaultValue()!=null)
                        attributeString += EQUALS_SIGN + attribute.getDefaultValue();
                    attributeString +=SEMICOLON;

                    stringBuilder.append(TAB + attributeString + NEWLINE);

                }
                );

        attributes.forEach(attribute ->
                {
                    if(attribute.isHasSetter())
                        stringBuilder.append(TAB + createSetterString(attribute.getName(),attribute.getType()));

                    if(attribute.isHasGetter())
                        stringBuilder.append(TAB + createGetterString(attribute.getName(),attribute.getType()));

                }
        );

        stringBuilder.append(DEFINITION_CLOSE);

        return stringBuilder.toString();
    }

    private String createSetterString(String name, String type){
        if(name == null || type == null)
            throw new IllegalArgumentException();

        var builder = new StringBuilder();
        builder.append(PUBLIC_MODIFIER);
        builder.append(SPACE);
        builder.append(VOID);
        builder.append(SPACE);
        builder.append(SETTER_PREFIX);
        builder.append(name.substring(0,1).toUpperCase() + name.substring(1));
        builder.append(OPEN_BRACKET);
        builder.append(type);
        builder.append(SPACE);
        builder.append(name);
        builder.append(CLOSE_BRACKET);
        builder.append(DEFINITION_OPEN);
        builder.append(NEWLINE);
        builder.append(TAB + TAB);
        builder.append(THIS_DOT);
        builder.append(name);
        builder.append(EQUALS_SIGN);
        builder.append(name);
        builder.append(SEMICOLON);
        builder.append(NEWLINE);
        builder.append(TAB);
        builder.append(DEFINITION_CLOSE);
        builder.append(NEWLINE);
        return builder.toString();
    }
    private String createGetterString(String name, String type){
        if(name == null || type == null)
            throw new IllegalArgumentException();

        var builder = new StringBuilder();
        builder.append(PUBLIC_MODIFIER);
        builder.append(SPACE);
        builder.append(type);
        builder.append(SPACE);
        builder.append(GETTER_PREFIX);
        builder.append(name.substring(0,1).toUpperCase() + name.substring(1));
        builder.append(OPEN_BRACKET);
        builder.append(CLOSE_BRACKET);
        builder.append(DEFINITION_OPEN);
        builder.append(NEWLINE);
        builder.append(TAB + TAB);
        builder.append(RETURN);
        builder.append(SPACE);
        builder.append(name);
        builder.append(SEMICOLON);
        builder.append(NEWLINE);
        builder.append(TAB);
        builder.append(DEFINITION_CLOSE);
        builder.append(NEWLINE);
        return builder.toString();
    }

}
