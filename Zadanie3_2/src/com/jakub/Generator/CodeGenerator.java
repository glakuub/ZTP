package com.jakub.Generator;

import com.jakub.Generator.Model.ClassDefinition;
import com.jakub.Generator.Model.DesignPattern;
import com.jakub.Util.StringMethods;

public class CodeGenerator {

    private static final String CLASS_HEADER="class ";
    private static final String DEFINITION_OPEN ="{";
    private static final String DEFINITION_CLOSE ="}";
    private static final String PRIVATE_MODIFIER="private";
    private static final String PUBLIC_MODIFIER="public";
    private static final String SPACE=" ";
    private static final String EQUALS_SIGN="=";
    private static final String SEMICOLON=";";
    private static final String DOT=".";
    private static final String NEWLINE="\n";
    private static final String SETTER_PREFIX="set";
    private static final String GETTER_PREFIX="get";
    private static final String OPEN_BRACKET="(";
    private static final String CLOSE_BRACKET=")";
    private static final String VOID="void";
    private static final String THIS_DOT="this.";
    private static final String THIS="this";
    private static final String RETURN="return";
    private static final String TAB="\t";
    private static final String FINAL_MODIFIER="final";
    private static final String STATIC_MODIFIER="static";
    private static final String INSTANCE="INSTANCE";
    private static final String NEW="new";
    private static final String BUILDER="Builder";
    private static final String BUILDER_ARG="builder";
    private static final String BUILD="Build";
    private static final String BUILDER_CONSTRUCTOR="public Builder(){}";
    private static final String VAL="val";






    public String createClassString(ClassDefinition classDefinition){
        if(classDefinition == null)
            throw new IllegalArgumentException();

        var stringBuilder = new StringBuilder();
        stringBuilder.append(CLASS_HEADER);
        stringBuilder.append(classDefinition.getName());
        stringBuilder.append(DEFINITION_OPEN);
        stringBuilder.append(NEWLINE);

        var designPatterns = classDefinition.getDesignPatterns();
        if(designPatterns.contains(DesignPattern.singleton)){
            var instanceLine = createStaticInstanceLine(classDefinition);
            stringBuilder.append(TAB + instanceLine);
            stringBuilder.append(NEWLINE);

            var privateConstructorLine = createPrivateConstructorLine(classDefinition);
            stringBuilder.append(TAB + privateConstructorLine);
            stringBuilder.append(NEWLINE);
        }

        if(designPatterns.contains(DesignPattern.builder)){
            var builderConstructorLine = createBuilderConstructorLine(classDefinition);
            stringBuilder.append(builderConstructorLine);
            stringBuilder.append(NEWLINE);
        }

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


        if(designPatterns.contains(DesignPattern.builder)){
            var builderString = createBuilderString(classDefinition);
            stringBuilder.append(builderString);
            stringBuilder.append(NEWLINE);
        }

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
    private String createStaticInstanceLine(ClassDefinition classDefinition){
        return PUBLIC_MODIFIER + SPACE + FINAL_MODIFIER + SPACE + STATIC_MODIFIER
                + SPACE + classDefinition.getName() + SPACE + INSTANCE + SPACE + EQUALS_SIGN
                + SPACE + NEW + SPACE + classDefinition.getName()+OPEN_BRACKET+CLOSE_BRACKET+SEMICOLON;
    }
    private String createPrivateConstructorLine(ClassDefinition classDefinition){
        return PRIVATE_MODIFIER + SPACE + classDefinition.getName() + SPACE
                + OPEN_BRACKET + CLOSE_BRACKET + DEFINITION_OPEN + DEFINITION_CLOSE;
    }
    private String createBuilderString(ClassDefinition classDefinition){
        var stringBuilder = new StringBuilder();
        var classAttributes = classDefinition.getAttributes();

        stringBuilder.append(StringMethods.concat(TAB,PUBLIC_MODIFIER,SPACE,CLASS_HEADER,BUILDER));
        stringBuilder.append(DEFINITION_OPEN);
        stringBuilder.append(NEWLINE);

        classAttributes.forEach(attribute -> {
            stringBuilder.append(StringMethods.concat(TAB,TAB,PRIVATE_MODIFIER,SPACE,attribute.getType(),SPACE,attribute.getName(),SEMICOLON,NEWLINE));
        });

        stringBuilder.append(StringMethods.concat(TAB,TAB,BUILDER_CONSTRUCTOR));
        stringBuilder.append(NEWLINE);

        classAttributes.forEach(attribute -> {
            stringBuilder.append(TAB + TAB + PUBLIC_MODIFIER + SPACE + BUILDER + SPACE + attribute.getName() + OPEN_BRACKET
                                    + attribute.getType() + SPACE + VAL + CLOSE_BRACKET + DEFINITION_OPEN + NEWLINE
                                    + TAB + TAB + TAB + attribute.getName() + EQUALS_SIGN + VAL + SEMICOLON + SPACE
                                    + RETURN + SPACE + THIS + SEMICOLON + NEWLINE + TAB + TAB + DEFINITION_CLOSE + NEWLINE);
        });
        stringBuilder.append(createBuilderBuildMethodString(classDefinition));
        stringBuilder.append(StringMethods.concat(NEWLINE,TAB,DEFINITION_CLOSE));

        return stringBuilder.toString();
    }
    private String createBuilderConstructorLine(ClassDefinition classDefinition){
        var stringBuilder = new StringBuilder();

        stringBuilder.append(StringMethods.concat(TAB,PUBLIC_MODIFIER,SPACE,classDefinition.getName(),OPEN_BRACKET));
        stringBuilder.append(StringMethods.concat(BUILDER, SPACE,BUILDER_ARG,CLOSE_BRACKET,DEFINITION_OPEN,NEWLINE));


        var attributes= classDefinition.getAttributes();
        attributes.forEach(attribute -> {
            stringBuilder.append(StringMethods.concat(TAB,TAB,THIS_DOT,attribute.getName(),EQUALS_SIGN));
            stringBuilder.append(StringMethods.concat(BUILDER_ARG,DOT,attribute.getName(),SEMICOLON,NEWLINE));
        });

        stringBuilder.append(StringMethods.concat(TAB, DEFINITION_CLOSE));

        return stringBuilder.toString();

    }

    private String createBuilderBuildMethodString(ClassDefinition classDefinition){
        var builder = new StringBuilder();
        builder.append(StringMethods.concat(TAB,TAB,PUBLIC_MODIFIER,SPACE, classDefinition.getName(),SPACE,BUILD,OPEN_BRACKET,CLOSE_BRACKET));
        builder.append(StringMethods.concat(DEFINITION_OPEN,NEWLINE,TAB,TAB,TAB,RETURN,SPACE,NEW,SPACE,classDefinition.getName()));
        builder.append(StringMethods.concat(OPEN_BRACKET,THIS,CLOSE_BRACKET,SEMICOLON,NEWLINE,TAB,TAB,DEFINITION_CLOSE));

        return builder.toString();
    }

}
