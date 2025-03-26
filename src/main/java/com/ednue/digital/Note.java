package com.ednue.digital;

import java.io.Serializable;
//import java.util;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Note implements Serializable {
   // public int title;
    int id;
    String content;
    Set<String> tags;
    Date lastModified;
    LinkedList<String> history;

    public Note(int id, String content, Set<String> tags) {
        this.id = id;
        this.content = content;
        this.tags = new HashSet<>(tags);
        this.lastModified = new Date();
        this.history = new LinkedList<>();
        history.add(content);
    }

    public void edit(String newContent) {
        history.add(newContent);
        this.content = newContent;
        this.lastModified = new Date();
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + content + " | Tags: " + tags + " | Last Modified: " + lastModified;
    }
}
