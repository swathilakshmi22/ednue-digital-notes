package com.ednue.digital;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        NotesApp app = new NotesApp();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Note\n2. Edit Note\n3. Delete Note\n4. Show All Notes\n5. Sort Notes\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter note content: ");
                        String content = scanner.nextLine();
                        System.out.print("Enter tags (comma-separated): ");
                        Set<String> tags = new HashSet<>(Arrays.asList(scanner.nextLine().split(",")));
                        app.createNote(content, tags);
                        System.out.println("Note created successfully!");
                        break;

                    case 2:
                        System.out.print("Enter note ID to edit: ");
                        int editId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new content: ");
                        String newContent = scanner.nextLine();
                        app.editNote(editId, newContent);
                        System.out.println("Note updated!");
                        break;

                    case 3:
                        System.out.print("Enter note ID to delete: ");
                        int deleteId = scanner.nextInt();
                        app.deleteNote(deleteId);
                        System.out.println("Note deleted!");
                        break;

                    case 4:
                        app.displayAllNotes();
                        break;

                    case 5:
                        app.sortNotesByDate();
                        break;


                    case 6:
                        System.out.println("Exiting... Have a great day!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
