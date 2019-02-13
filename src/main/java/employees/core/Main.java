package employees.core;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Engine engine = new Engine();
        engine.run();
    }
}