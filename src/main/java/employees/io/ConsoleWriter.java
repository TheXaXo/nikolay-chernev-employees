package employees.io;

public class ConsoleWriter implements Writer {
    @Override
    public void writeLine(String string) {
        System.out.println(string);
    }
}