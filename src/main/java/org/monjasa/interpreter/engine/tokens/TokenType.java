package org.monjasa.interpreter.engine.tokens;

import org.monjasa.interpreter.engine.exceptions.MissingTokenTypeException;

import java.util.*;
import java.util.stream.Collectors;

public enum TokenType {

    PROGRAM("program", true),
    VARIABLE_DECLARATION_BLOCK("let", true),
    PROCEDURE("procedure", true),
    EOF('\0'),

    BEGIN('{'),
    END('}'),
    ASSIGNMENT('='),

    MORE('>'),
    LESS('<'),
    AND('&'),
    OR('|'),

    IF("if", true),
    ELSE("else", true),
    WHILE("while", true),

    COLON(':'),
    SEMICOLON(';'),
    COMMA(','),
    DOT('.'),

    INTEGER_TYPE("int", true),
    FLOAT_TYPE("float", true),
    BOOLEAN_TYPE("boolean", true),
    STRING_TYPE("string", true),

    NUMBER_CONST("0"),
    INTEGER_CONST("d"),
    FLOAT_CONST("f"),
    TRUE_CONST("true", true),
    FALSE_CONST("false", true),
    STRING_CONST("s"),

    ID("id"),

    ADDITION('+'),
    SUBTRACTION('-'),
    MULTIPLICATION('*'),
    DIVISION('/'),

    LEFT_PARENTHESIS('('),
    RIGHT_PARENTHESIS(')'),
    STRING_BRACKET('"'),
    WHITESPACE(" "),
    EMPTY_TOKEN("");


    private static final Map<String, TokenType> TOKEN_CONTRACTIONS;
    private static final List<TokenType> KEYWORDS;

    static {

        TOKEN_CONTRACTIONS = Collections.unmodifiableMap(Arrays.stream(values())
                .collect(Collectors.toMap(TokenType::getContraction, tokenType -> tokenType)));

        KEYWORDS = Collections.unmodifiableList(Arrays.stream(values())
                .filter(TokenType::isKeyword)
                .collect(Collectors.toList()));
    }

    public static TokenType getTypeByContraction(String contraction) throws MissingTokenTypeException {

        if (TOKEN_CONTRACTIONS.containsKey(contraction))
            return TOKEN_CONTRACTIONS.get(contraction);
        else
            throw new MissingTokenTypeException(contraction);
    }

    public static List<TokenType> getKeywords() {
        return KEYWORDS;
    }

    public static Optional<?> getDefaultValue(TokenType type) {

        switch (type) {
            case INTEGER_TYPE:
                return Optional.of(0);
            case FLOAT_TYPE:
                return Optional.of(0.0f);
            case BOOLEAN_TYPE:
                return Optional.of(false);
            case STRING_TYPE:
                return Optional.of("");
            default:
                throw new RuntimeException();
        }
    }

    private String contraction;
    private boolean keyword;

    TokenType(String contraction) {
        this.contraction = contraction;
        this.keyword = false;
    }

    TokenType(char contraction) {
        this.contraction = Character.toString(contraction);
        this.keyword = false;
    }

    TokenType(String contraction, boolean keyword) {
        this.contraction = contraction;
        this.keyword = keyword;
    }

    public String getContraction() {
        return contraction;
    }

    public boolean isKeyword() {
        return keyword;
    }
}
