import com.opencsv.CSVWriter;
import java.io.IOException;


public class FileWriter {


    /**
     * Writes base data and results to a csv file and the terminal
     * @param team - team
     * @param graphType - MULTI or SIMPLE
     * @param edgeCount - number of edges
     * @param pc - type of pc
     * @param tool - edi
     * @param fileName - file
     * @throws IOException - ouch
     */
    public static void saveGraph(String team, String graphType, String edgeCount, String pc,  String tool, String fileName) throws IOException {

        CSVWriter csvWriter = new CSVWriter(new java.io.FileWriter(fileName+".csv"));
        csvWriter.writeNext(new String[] {"Team", "GraphType", "Edge Count", "PC", "Tool"}, false);
        csvWriter.writeNext(new String[]{team, graphType, edgeCount, pc, tool}, false);
        csvWriter.writeNext(new String[] {"Run Nr", "Joule", "Run Time"}, false);

        System.out.print("\n--------------Saved the following----------------");
        System.out.print("\nTeam - GraphType - Edge Count - PC - Tool");
        System.out.printf("%s, %s, %s, %s, %s", team, graphType, edgeCount, pc, tool);
        System.out.print("\nRun Nr - Joule - Run Time");

        csvWriter.close();
    }

    /**
     * Writes base data and results to a csv file and the terminal
     * @param runNr - run number
     * @param joule - energy consumption
     * @param time - run time
     * @param fileName - file
     * @throws IOException - ouch
     */
    public static void appendGraph(String runNr, String joule, String time, String fileName) throws IOException {

        CSVWriter csvWriter = new CSVWriter(new java.io.FileWriter(fileName+".csv", true));
        csvWriter.writeNext(new String[]{runNr, joule, time}, false);

        System.out.printf("\n%s, %s, %s", runNr, joule, time);

        csvWriter.close();
    }

}
