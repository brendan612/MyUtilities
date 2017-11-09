package com.brendanjbishop.datetime;

/**
 * An enumeration to store the different variants of date separators
 * @author Brendan Bishop | brendan@brendanjbishop.com
 * @version 1.0
 * @since 1.8
 */
public enum Separator {
    FORWARD_SLASH('/'),
    DASH('-'),
    PERIOD('.'),
    SPACE(' '),
    COMMA(',');

    private final char symbol;

    /**
     * 
     * @param c Assigns the correlating symbol to the enum
     */
    Separator(char symbol) {
        this.symbol = symbol;
    }

    char getSymbol() {
        return symbol;
    }
}
