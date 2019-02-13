package employees.io;

import java.io.*;

public class FromFileReader implements Reader {
    private BufferedReader reader;

    public FromFileReader(File file) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(file));
    }

    @Override
    public String readLine() throws IOException {
        return this.reader.readLine();
    }
}