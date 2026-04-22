import java.util.*;
import java.io.*;

public class Pass1 {
    // Simple in-memory tables for Pass 1
    static List<String> MNT = new ArrayList<>();
    static List<String> MDT = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // Sample assembly-like input
        String[] input = {
            "START 1000",
            "MACRO",
            "COMPUTE &X,&Y",
            "LOAD R1,&X",
            "ADD R2,&Y",
            "STORE R1,TOTAL",
            "MEND",
            "READ VAL1",
            "READ VAL2",
            "COMPUTE VAL1,VAL2", // Macro Call
            "PRINT TOTAL",
            "END"
        };

        List<String> intermediateCode = new ArrayList<>();
        boolean inMacro = false;
        String macroName = "";
        String[] formalParams = new String[0];

        // PASS 1: build MNT, MDT, and intermediate code
        for (int i = 0; i < input.length; i++) {
            String line = input[i].trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.equals("MACRO")) {
                inMacro = true;
                String prototype = input[++i].trim(); // e.g., COMPUTE &X,&Y
                String[] parts = prototype.split("\\s+", 2);
                macroName = parts[0];
                formalParams = (parts.length > 1) ? parts[1].split(",") : new String[0];

                // Store macro name and MDT start index
                MNT.add(macroName + " " + MDT.size());
                // Store prototype in MDT (optional but useful)
                MDT.add(prototype);
                continue;
            }

            if (inMacro) {
                // Replace formal parameters with positional notation ?1, ?2...
                for (int j = 0; j < formalParams.length; j++) {
                    String p = formalParams[j].trim();
                    line = line.replace(p, "?" + (j + 1));
                }
                MDT.add(line);

                if (line.equals("MEND")) {
                    inMacro = false;
                    macroName = "";
                    formalParams = new String[0];
                }
            } else {
                intermediateCode.add(line);
            }
        }

        writeLines("mnt.txt", MNT);
        writeLines("mdt.txt", MDT);
        writeLines("intermediate.txt", intermediateCode);

        System.out.println("Pass 1 completed.");
        System.out.println("Created mnt.txt, mdt.txt, intermediate.txt");
        System.out.println("\nMNT:");
        for (String s : MNT) System.out.println(s);
        System.out.println("\nMDT:");
        for (String s : MDT) System.out.println(s);
        System.out.println("\nIntermediate Code:");
        for (String s : intermediateCode) System.out.println(s);
    }

    private static void writeLines(String fileName, List<String> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }
}