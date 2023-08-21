import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * The RectangleDB class reads commands from a file and processes them using a
 * CommandProcessor, Binary Search Tree (BST), and a World object.
 *
 * @author Akhil
 * @version 07-Aug-2023
 */
// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
@SuppressWarnings("rawtypes")
public class RectangleDB {

    private static CommandProcessor commandProcessor = new CommandProcessor();

    /**
     * The main method of the RectangleDB program.
     *
     * @param args
     *            Command-line arguments. Expects a single argument - the
     *            filepath of the input commands file.
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Filepath not specified");
            return;
        }

        String filePath = args[0];

        try {
            processFile(filePath);
        }
        catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

    }


    /**
     * Processes the commands from a file.
     *
     * @param filePath
     *            The filepath of the input commands file.
     * @throws IOException
     *             If an I/O error occurs while reading the file.
     */
    public static void processFile(String filePath) throws IOException {

        Scanner scanner = new Scanner(new File(filePath));
        BST bst = new BST();
        World world = new World();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            String[] commands = command.trim().split("\\s+");
            commandProcessor.processCommands(commands, bst, world);
        }

    }
}
