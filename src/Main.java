/* =============================================================================
MAIN:
    - Takes file location input from the user.
    - Print, add, and remove patrons using a menu.

    The functionality to print the patrons upon loading the file is
    annoying with large lists, so I have instead forced that
    functionality into the menu option [1].
============================================================================= */

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();

        // GET THE FILE LOCATION
        System.out.println("Enter the exact path to the patron file (with file extension): ");
        String file_path = scanner.nextLine();

        /* CHECK AND WORK WITH THE FILE
        ========================================================================= */
        ArrayList<Patron> patrons = database.read_patrons_file(file_path);

        // EMPTY FILE OR WRONG PATH
        if (patrons.isEmpty()) {
            System.out.println("No patrons were found!");
            System.out.println("Check file path again and make sure the file contains patrons.");
        }

        // GET PATRON COUNT & PRINT MENU
        else {
            System.out.println("Successfully found " + patrons.size() + " patrons!");
            System.out.println();

            boolean run = true;
            while (run) {
                System.out.println();
                System.out.println("LIBRARY MANAGEMENT SYSTEM");
                System.out.println("[1] List existing patrons");
                System.out.println("[2] Add a patron (by ID)");
                System.out.println("[3] Remove a patron (by ID)");
                System.out.println("[0] Exit");
                System.out.println();
                System.out.print("> ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // wtf java

                switch (choice) {
                    case 1:
                        list_patrons(patrons);
                        break;
                    case 2:
                        add_patron(patrons, scanner, database, file_path);
                        break;
                    case 3:
                        remove_patron(patrons, scanner, database, file_path);
                        break;
                    case 0:
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid option!");
                        System.out.println("Please enter a value between 0-3.");
                }
            }


        }
    }

    // OPTION [1]: LIST PATRONS
    public static void list_patrons(ArrayList<Patron> patrons) {
        for (Patron patron : patrons) {
            System.out.println(patron.toString());
        }
    }

    // OPTION [2]:
    public static void add_patron(ArrayList<Patron> patrons, Scanner scanner, Database database, String file_path) {
        System.out.print("Enter patron ID: ");
        String new_id = scanner.nextLine();

        for (Patron patron : patrons) {
            if (patron.get_id().equals(new_id)) {
                System.out.println("ERROR: Patron with that ID already exists!");
                return;
            }
        }

        System.out.print("Patron Name: ");
        String new_name = scanner.nextLine();

        System.out.print("Patron Address: ");
        String new_address = scanner.nextLine();

        System.out.print("Overdue Amount: ");
        double new_overdue_amount = scanner.nextDouble();
        scanner.nextLine(); // wtf java

        if (new_overdue_amount < 0 || new_overdue_amount > 250) {
            System.out.println("ERROR: Amount must be between 0.00 and 250.00");
            return;
        }

        Patron new_patron = new Patron();
        new_patron.set_id(new_id);
        new_patron.set_name(new_name);
        new_patron.set_address(new_address);
        new_patron.set_overdue_amount(new_overdue_amount);

        patrons.add(new_patron);
        database.write_patrons_to_file(file_path, patrons);
        System.out.println("Patron " + new_id + " has been added to the file.");


    }


    // OPTION [3]:
    public static void remove_patron(ArrayList<Patron> patrons, Scanner scanner, Database database, String file_path) {
        scanner.nextLine();
        System.out.println("Enter the ID of the patron you want to remove: ");
        String id_request = scanner.nextLine();

        boolean found = false;

        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).get_id().equals(id_request)) {
                patrons.remove(i);
                System.out.println("Patron " + id_request + " has been removed.");
                found = true;
                break;

            }
        }

        if (!found) {
            System.out.println("No patron with the ID " + id_request + " was found");
        } else {
            database.write_patrons_to_file(file_path, patrons);
            System.out.println("File have been updated!");
        }
    }

}
