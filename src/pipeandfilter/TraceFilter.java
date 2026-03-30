package pipeandfilter;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Collectors;

public class TraceFilter extends Filter {
    private final String logFile = "trace.txt";

    public TraceFilter(Pipe in, Pipe out) {
        super(in, out);
    }

    @Override
    void execute() {
        while (true) {
            String data = getData();
            if (data.startsWith("GET_TRACE")) {
                try {
                    String content = Files.readAllLines(Paths.get(logFile)).stream().collect(Collectors.joining("\n"));
                    sendData("TRACE_DATA:" + (content.isEmpty() ? "Vide" : content));
                } catch (IOException e) {
                    sendData("TRACE_DATA:Erreur lecture fichier.");
                }
            } else {
                // Sauvegarde dans le fichier .txt
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                    writer.write(data);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sendData(data); // Renvoie à la GUI pour affichage
            }
        }
    }
}