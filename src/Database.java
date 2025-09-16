/* =============================================================================
DATABASE CLASS:
    - Handles the patron file operations.
    - Prioritizes an existing file with exact or relative path.
============================================================================= */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Database {

    /* GET THE PATRONS FROM FILE
    ========================================================================= */
    public ArrayList<Patron> read_patrons_file(String filename) {
        ArrayList<Patron> patrons = new ArrayList<>();

        /* TRY
        ===================================================================== */
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            // ITERATE AND SPLIT OVER EACH LINE
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    String[] attributes = line.split("-");

                    // VERIFY ALL 4 ATTRIBUTES
                    if (attributes.length == 4) {

                        // ORGANIZE EACH ATTRIBUTE
                        String id = attributes[0];
                        String name = attributes[1];
                        String address = attributes[2];
                        double overdue_amount = Double.parseDouble(attributes[3]);

                        // CREATE PATRON OBJECT
                        Patron patron = new Patron();

                        // SET EACH ATTRIBUTE
                        patron.set_id(id);
                        patron.set_name(name);
                        patron.set_address(address);
                        patron.set_overdue_amount(overdue_amount);

                        // ADD THEM TO THE LIST
                        patrons.add(patron);
                    }

                }

            }
        }

        /* CATCH
        ===================================================================== */
        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
            return patrons;
        }

        return patrons;
    }

    // METHOD TO REWRITE FILE WITH UPDATED LIST
    public void write_patrons_to_file(String filename, ArrayList<Patron> patrons) {
        try {
            List<String> lines = new ArrayList<>();

            for (Patron patron : patrons) {
                lines.add(patron.toString());
            }

            Files.write(Paths.get(filename), lines);
        }

        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}

















