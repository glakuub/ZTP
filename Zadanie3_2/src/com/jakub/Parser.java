package com.jakub;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private static final String DEFINITION_SEPARAOTR=",";
    private static final String ATRIBUTE_SEPARATOR=";";
    private static final String CLASS_NAME_PREFIX="class";
    private static final String SPACE=" ";
    private static final String COLON=":";
    private static final String EQUALS_SIGN="=";
    private static final String QUOTE="\'";
    private static final String DOUBLE_QUOTE="\"";
    private static final String SETTER="setter";
    private static final String GETTER="getter";
    private static final String STRING="String";
    private static final String CHAR="char";


    public ClassDefinition createClassDefinition(String classDefinitionString) throws InvalidClassDefinitionStringException{
        if(classDefinitionString == null)
            throw new IllegalArgumentException();

        var definitionParts = classDefinitionString.split(DEFINITION_SEPARAOTR);

        var classDefinition = new ClassDefinition();

        try {
            classDefinition.setName(extractClassName(definitionParts[0]));
        }catch (IllegalArgumentException iae){
            throw new InvalidClassDefinitionStringException();
        }

        try {
            classDefinition.setAttributes(extractAttributes(definitionParts[1]));
        }catch (IllegalArgumentException iae){
            throw new InvalidClassDefinitionStringException();
        }

        return classDefinition;


    }

    private String extractClassName(String classDefinitionNamePart){
        if(classDefinitionNamePart == null)
            throw new IllegalArgumentException();
        if(!classDefinitionNamePart.startsWith(CLASS_NAME_PREFIX))
            throw new IllegalArgumentException();

        var classAndName = classDefinitionNamePart.split(SPACE);

        if(classAndName.length != 2)
            throw new IllegalArgumentException();

        return classAndName[1];


    }

    private List<Attribute> extractAttributes(String classDefinitionAttributesPart){
        if(classDefinitionAttributesPart == null)
            throw new IllegalArgumentException();

        if(isStringWhiteSpaces(classDefinitionAttributesPart))
            return Collections.EMPTY_LIST;

        var attributeStrings = classDefinitionAttributesPart.split(ATRIBUTE_SEPARATOR);
        var attributes = Arrays.stream(attributeStrings).map(attributeString -> extractAttribute(attributeString)).collect(Collectors.toList());
        return attributes;

    }

    private boolean isStringWhiteSpaces(String string){
        return string.chars().allMatch(i->i==32);
    }

    private Attribute extractAttribute(String attributeString){
        if(attributeString == null)
            throw new IllegalArgumentException();

        var attributeDefinitionParts = attributeString.split(COLON);

        if(attributeDefinitionParts.length != 2)
            throw new IllegalArgumentException();

        var attribute = new Attribute();
        attribute.setName(attributeDefinitionParts[0].trim());

        var attributeDetails = attributeDefinitionParts[1];

        if(attributeDetails.contains(EQUALS_SIGN)){
            var typeAndRest = attributeDetails.split(EQUALS_SIGN);
            if(typeAndRest.length != 2)
                throw new IllegalArgumentException();

            attribute.setType(typeAndRest[0]);

            var defaultAndMethods = typeAndRest[1].split(SPACE);
            if(!defaultAndMethods[0].contains(QUOTE))
                throw new IllegalArgumentException();

            var defaultValue = StringUtils.strip(defaultAndMethods[0],QUOTE);

            if(attribute.getType().equals(STRING))
                defaultValue = DOUBLE_QUOTE + defaultValue + DOUBLE_QUOTE;

            if(attribute.getType().equals(CHAR))
                defaultValue = QUOTE + defaultValue + QUOTE;

            attribute.setDefaultValue(defaultValue);

            if(defaultAndMethods.length > 1){

                var methodsPart = Arrays.copyOfRange(defaultAndMethods,1, defaultAndMethods.length);
                if(Arrays.stream(methodsPart).anyMatch(method->method.equals(SETTER))){
                    attribute.setHasSetter(true);
                }
                if(Arrays.stream(methodsPart).anyMatch(method->method.equals(GETTER))){
                    attribute.setHasGetter(true);
                }

            }
        }
        else{
            var typeAndMethods = attributeDetails.split(SPACE);
            attribute.setType(typeAndMethods[0]);

            if(typeAndMethods.length > 1){
                var methodsPart = Arrays.copyOfRange(typeAndMethods,1, typeAndMethods.length);
                if(Arrays.stream(methodsPart).anyMatch(method->method.equals(SETTER))){
                    attribute.setHasSetter(true);
                }
                if(Arrays.stream(methodsPart).anyMatch(method->method.equals(GETTER))){
                    attribute.setHasGetter(true);
                }
            }
        }

        return attribute;

    }


}
