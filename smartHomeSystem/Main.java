import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class Main {
    /**
     * Reads all non-empty lines from a text file and returns them as a String array.
     * @param filePath the path of the file to be read
     * @return a String array containing all non-empty lines of the file, or null if the file cannot be read
     */
    public static String[] readLinesFromFile(String filePath){
        //this part for read the file from terminal
        try {
            int i = 0;
            int length= Files.readAllLines(Paths.get(filePath)).size();
            String[] lines = new String[length];
            for (String line : Files.readAllLines(Paths.get(filePath))){
                lines[i++] = line;
            }
            List<String> nonEmptyLines = new ArrayList<String>();
            for (String str : lines) {
                if (!str.trim().isEmpty()) {
                    nonEmptyLines.add(str);
                }
            }
            lines = nonEmptyLines.toArray(new String[0]);
            return lines;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Writes a line of text to a file. If the file already exists and {@code appendToFile} is true,
     * the line is appended to the end of the file; otherwise, the existing file is overwritten.
     * @param lineToWrite the line of text to be written
     * @param appendToFile true to append the line to an existing file, false to overwrite the existing file
     */
    public static void writer(String lineToWrite,boolean appendToFile,String outputFile){
        try {
            File outputfile = new File(outputFile);
            FileWriter outputFileWriter = new FileWriter(outputfile,appendToFile); //appendable part for override
            outputFileWriter.write(lineToWrite);
            outputFileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * The main method of the program. Reads input from a file specified in the command line arguments,
     * initializes the output file, and creates an instance of the smartSystem class to process the input.
     * @param args an array of command line arguments; the first argument should be the path to the input file
     * @throws IOException if an I/O error occurs while reading from the input file
     */
    public static void main(String[] args) throws IOException{
        String[] lines = readLinesFromFile(args[0]); //reading input file given in terminal
        String outputLocation = args[1];//writing input file given in terminal
        writer("",false,outputLocation);//this is for escape from overwriting
        new smartSystem(lines,outputLocation);  //read every line in file and do necessary operations and call all the class
    }
}