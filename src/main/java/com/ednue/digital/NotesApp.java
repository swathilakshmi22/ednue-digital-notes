package com.ednue.digital;

import java.io.*;
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

    public void saveNotesToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(new ArrayList<>(notes.values()));
            System.out.println("Notes saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving notes.");
        }
    }

    public void loadNotesFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Note> loadedNotes = (List<Note>) ois.readObject();
            notes.clear();
            noteList.clear();
            for (Note note : loadedNotes) {
                notes.put(note.id, note);
                noteList.add(note);
            }
            System.out.println("Notes loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved notes found.");
        }
    }
}
