package com.ednue.digital;

import java.io.Serializable;
import java.util.Set;
import java.util.*;

 class Notes implements Serializable{
    int ID;
    String content;
    Set<String> tags;
    Date lastmodified;
    LinkedList<String> history;

     public Notes(int ID,String content,Set<String> tags ){
        this.ID=ID;
        this.content=content;
        this.tags=new HashSet<>(tags);
        this.lastmodified=new Date();
        this.history=new LinkedList<>();
     }

     public void edit(String newcontent){
         history.add(newcontent);
         content=newcontent;
         lastmodified=new Date();
     }
 }
