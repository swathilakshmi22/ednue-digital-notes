package com.ednue.digital;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        NotesApp app = new NotesApp();
        //AutoSaveThread autoSave = new AutoSaveThread(app, "notes.txt");
        //autoSave.setDaemon(true);
        //autoSave.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Note\n2. Edit Note\n3. Delete Note\n4. Show All Notes\n5. Sort Notes\n6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

//                    case 4:
//                        System.out.print("Enter keyword to search: ");
//                        String keyword = scanner.nextLine();
//                        app.searchNotes(keyword);
//                        break;

                    case 4:
                        app.displayAllNotes();
                        break;

                    case 5:
                        app.sortNotesByDate();
                        break;

//                    case 7:
//                        app.saveNotesToFile("notes.txt");
//                        break;

//                    case 8:
//                        app.loadNotesFromFile("notes.txt");
//                        break;

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
