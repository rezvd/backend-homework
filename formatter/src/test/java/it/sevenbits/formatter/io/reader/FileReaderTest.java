package it.sevenbits.formatter.io.reader;

import it.sevenbits.formatter.io.ireader.FileReader;
import it.sevenbits.formatter.io.ireader.IReader;
import it.sevenbits.formatter.io.ireader.ReaderException;
import org.junit.Test;

import java.io.FileInputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileReaderTest {


    @Test
    public void testWithFiles () throws Exception {
        try (FileReader reader = new FileReader("in.txt", Charset.forName("UTF-8"))) {
            assertTrue(reader.hasNext());
            assertEquals('а', reader.read());
            assertTrue(reader.hasNext());
            assertEquals('б', reader.read());
            assertTrue(reader.hasNext());
            assertEquals('в', reader.read());
            assertFalse(reader.hasNext());
        }
    }

    @Test (expected = ReaderException.class)
    public void testWithReaderExeption() throws ReaderException {
        IReader reader= new FileReader("someFileForReading", Charset.forName("UTF-8"));
    }
}
