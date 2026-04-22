import java.util.*;

public class Pass2 {
    public static void main(String[] args) {
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
            "COMPUTE VAL1,VAL2",
            "PRINT TOTAL",
            "END"
        };

        // Embedded Pass 1 data structures
        List<String> mnt = new ArrayList<>();
        List<String> mdt = new ArrayList<>();
        List<String> intermediate = new ArrayList<>();
        Map<String, Integer> mntMap = new HashMap<>();

        boolean inMacro = false;
        String[] formalParams = new String[0];

        // Pass 1 inside Pass2: create MNT, MDT and intermediate code
        for (int i = 0; i < input.length; i++) {
            String line = input[i].trim();
            if (line.isEmpty()) {
                continue;
            }

            if (line.equals("MACRO")) {
                inMacro = true;
                String prototype = input[++i].trim();
                String[] parts = prototype.split("\\s+", 2);
                String macroName = parts[0];
                formalParams = (parts.length > 1) ? parts[1].split(",") : new String[0];

                mntMap.put(macroName, mdt.size());
                mnt.add(macroName + " " + mdt.size());
                mdt.add(prototype);
                continue;
            }

            if (inMacro) {
                for (int j = 0; j < formalParams.length; j++) {
                    line = line.replace(formalParams[j].trim(), "?" + (j + 1));
                }
                mdt.add(line);
                if (line.equals("MEND")) {
                    inMacro = false;
                    formalParams = new String[0];
                }
            } else {
                intermediate.add(line);
            }
        }

        // Pass 2: expand macro calls from intermediate code
        System.out.println("Final Expanded Source Code:");
        for (String stmt : intermediate) {
            String[] parts = stmt.split("\\s+", 2);
            String op = parts[0];

            if (!mntMap.containsKey(op)) {
                System.out.println(stmt);
                continue;
            }

            String[] actualArgs = new String[0];
            if (parts.length > 1) {
                actualArgs = parts[1].split(",");
                for (int i = 0; i < actualArgs.length; i++) {
                    actualArgs[i] = actualArgs[i].trim();
                }
            }

            int ptr = mntMap.get(op) + 1; // skip prototype
            while (ptr < mdt.size() && !mdt.get(ptr).equals("MEND")) {
                String expanded = mdt.get(ptr);
                for (int i = 0; i < actualArgs.length; i++) {
                    expanded = expanded.replace("?" + (i + 1), actualArgs[i]);
                }
                System.out.println(expanded);
                ptr++;
            }
        }
    }
}
