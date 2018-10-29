package it.sevenbits.formatter.formatter;

import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.IReaderException;
import it.sevenbits.formatter.io.iwriter.IWriter;

/**
 * Formates code with right indents and spaces
 */
public class Formatter {

    /**
     * Puts right indents and spaces in the code
     * @param in Contains input code, which will be format
     * @param out Gives the result - formatted code
     */
    public void format(final IReader in, final IWriter out) {
        final int INTENT_LENGTH = 4;
        int intentCount = 0;
        char currentChar;
        char previousChar; //last significant symbol
        boolean newLine = false;
        boolean wasSpace = false;
        try {
            previousChar = (char) in.read();
            out.write(previousChar);
            if (previousChar == '{' || previousChar == ';') {
                intentCount++;
                newLine = true;
            }
            while (in.hasNext()) {
                currentChar = (char) in.read();
                if (currentChar != '}' && newLine && currentChar != ' ' && currentChar != '\n') {
                    out.write('\n');
                    for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                        out.write(' ');
                    }
                    newLine = false;
                }
                switch (currentChar) {
                    case '{':
                        intentCount++;
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case '}':
                        intentCount--;
                        out.write('\n');
                        for (int j = 0; j < INTENT_LENGTH * intentCount; j++) {
                            out.write(' ');
                        }
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case ';':
                        out.write(currentChar);
                        newLine = true;
                        wasSpace = false;
                        break;
                    case '\n':
                        currentChar = previousChar;
                        if (!wasSpace) {
                            out.write(' ');
                        }
                        wasSpace = true;
                        break;
                    case ' ':
                        if (!wasSpace) {
                            out.write(currentChar);
                        }
                        wasSpace = true;
                        currentChar = previousChar;
                        break;
                    default:
                        out.write(currentChar);
                        wasSpace = false;
                }
                previousChar = currentChar;
            }

        } catch (IReaderException e) {
            e.printStackTrace();
        }
    }
}