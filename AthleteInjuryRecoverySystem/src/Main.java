import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.connect();
        dbManager.createTable();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Injury Record");
            System.out.println("2. View All Records");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Enter Athlete Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Injury Type: ");
                String injuryType = scanner.nextLine();
                System.out.print("Enter Recovery Time (days): ");
                int recoveryTime = scanner.nextInt();

                InjuryRecord record = new InjuryRecord(name, injuryType, recoveryTime);
                dbManager.insertRecord(record);
            } else if (choice == 2) {
                dbManager.viewAllRecords();
            } else if (choice == 3) {
                break;
            }
        }
        dbManager.disconnect();
        scanner.close();
    }
}
