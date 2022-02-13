package io.output;

import io.enums.ColorConsole;

public abstract interface ConsoleOutput{
    void output(String message);
    void outputWithColor(String message, ColorConsole color);
}
