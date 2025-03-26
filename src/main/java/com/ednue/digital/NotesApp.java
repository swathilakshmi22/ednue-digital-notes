package com.ednue.digital;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;

public class NotesApp {
    private final Map<Integer, Note> notes = new HashMap<>();
    private final List<Note> noteList = new ArrayList<>();
    private int noteIdCounter = 1;

    public void createNote(String content, Set<String> tags) {
        Note note = new Note(noteIdCounter++, content, tags);
        notes.put(note.id, note);
        noteList.add(note);
    }

    public void editNote(int id, String newContent) throws Exception {
        if (!notes.containsKey(id)) throw new Exception("Note not found!");
        notes.get(id).edit(newContent);
    }

    public void deleteNote(int id) throws Exception {
        if (!notes.containsKey(id)) throw new Exception("Note not found!");
        noteList.remove(notes.get(id));
        notes.remove(id);
    }

    public void searchNotes(String keyword) {
        for (Note note : notes.values()) {
            if (note.content.contains(keyword)) {
                System.out.println(note);
            }
        }
    }

    public void displayAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes available.");
        } else {
            for (Note note : noteList) {
                System.out.println(note);
            }
        }
    }

    public void sortNotesByDate() {
        noteList.sort(Comparator.comparing(n -> n.lastModified));
        System.out.println("Notes sorted by last modified date.");
    }

    public class NotesManager {
        private Map<Integer, Note> notes = new HashMap<>();
        private List<Note> noteList = new ArrayList<>();
        private static final String DELIMITER = ";;"; // Separator for fields

        public void saveNotesToFile(String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                for (Note note : notes.values()) {
                    writer.write(note.id + DELIMITER + note.title + DELIMITER + note.content);
                    writer.newLine();
                }
                System.out.println("Notes saved successfully!");
            } catch (IOException e) {
                System.out.println("Error saving notes.");
            }
        }

        public void loadNotesFromFile(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                notes.clear();
                noteList.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(DELIMITER, 3);
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0]);
                        String title = parts[1];
                        String content = parts[2];
                        Note note = new Note(id, title, Collections.singleton(content));
                        notes.put(id, note);
                        noteList.add(note);
                    }
                }
                System.out.println("Notes loaded successfully!");
            } catch (IOException e) {
                System.out.println("No saved notes found.");
            }
        }
    }


}
