import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import static org.junit.Assert.*;

// -------------------------------------------------------------------------
/**
 * Test class for RectangleDB
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
public class RectangleDBTest {

    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // ----------------------------------------------------------
    /**
     * test method for main
     */
    @Test
    public void testMainWithInvalidArgs() {

        System.setOut(new PrintStream(outContent));

        RectangleDB.main(new String[] {});

        assertEquals("Filepath not specified", outContent.toString().trim());

        System.setOut(originalOut);
    }


    // ----------------------------------------------------------
    /**
     * test method for processFile
     *
     * @throws IOException
     */
    @Test
    public void testProcessFile() throws IOException {

        File tempFile = File.createTempFile("tempCommands", ".txt");
        tempFile.deleteOnExit();
        try {
            String commands = "command1\ncommand2\ncommand3";
            Files.write(tempFile.toPath(), commands.getBytes());

            RectangleDB.processFile(tempFile.getPath());

        }
        catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

}
