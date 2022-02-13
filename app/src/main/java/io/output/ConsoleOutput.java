package io.output;

import io.enums.ColorConsole;

public interface ConsoleOutput{
    void output(String message);
    void outputWithColor(String message, ColorConsole color);
}
