package ru.example.mynotes;

public class Note {
    public String name;
    public String desc;
    public String date;
    public Note(String name,String desc, String date){
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
