import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber;
    }
}

public class ContactManager {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static final String FILE_NAME = "contacts.txt";

    public static void main(String[] args) {
        loadContactsFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Contact Manager ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewAllContacts();
                    break;
                case 3:
                    searchContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    saveContactsToFile();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        contacts.add(new Contact(name, phoneNumber));
        System.out.println("Contact added successfully!");
    }

    private static void viewAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\n--- All Contacts ---");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter name or phone number to search: ");
        String keyword = scanner.nextLine();
        boolean found = false;

        for (Contact contact : contacts) {
            if (contact.getName().contains(keyword) || contact.getPhoneNumber().contains(keyword)) {
                System.out.println(contact);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contacts found.");
        }
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter name or phone number to delete: ");
        String keyword = scanner.nextLine();
        boolean removed = contacts.removeIf(contact ->
                contact.getName().equalsIgnoreCase(keyword) || contact.getPhoneNumber().equals(keyword));

        if (removed) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("No matching contacts found.");
        }
    }

    private static void loadContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0], parts[1]));
                }
            }
            System.out.println("Contacts loaded from file.");
        } catch (IOException e) {
            System.out.println("No existing contacts file found. Starting with an empty list.");
        }
    }

    private static void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "\n");
            }
            System.out.println("Contacts saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving contacts to file.");
        }
    }
}
