/* =============================================================================
PATRON CLASS:
    - Handles getting and setting the id, name, address, and overdue
      amount for all library patrons.
    - Prints the full details of the patron with toString().
============================================================================= */

public class Patron {

    // ATTRIBUTES
    private String id;
    private String name;
    private String address;
    private double overdue_amount;

    // G/S FOR ID
    public String get_id() { return id; }
    public void set_id(String id) { this.id = id; }

    // G/S FOR NAME
    public String get_name() { return name; }
    public void set_name(String name) { this.name = name; }

    // G/S FOR ADDRESS
    public String get_address() { return address; }
    public void set_address(String address) { this.address = address; }

    // G/S FOR OVERDUE_AMOUNT
    public double get_overdue_amount() { return overdue_amount; }
    public void set_overdue_amount(double overdue_amount) { this.overdue_amount = overdue_amount; }

    // PRINT EVERYTHING
    public String toString() {
        return id + "-" + name + "-" + address + "-" + String.format("%.2f", overdue_amount);
    }

}




















