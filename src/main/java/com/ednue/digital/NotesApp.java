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
}
