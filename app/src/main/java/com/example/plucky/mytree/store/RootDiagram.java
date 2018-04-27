package com.example.plucky.mytree.store;


import java.io.Serializable;
import java.util.Arrays;

public class RootDiagram implements Comparable, Serializable {
    private int pno;
    private String title ;
    private String content ;
    private String[]text;
    private String meaning ;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public void setContent(String content) {
        this.content = content;
        this.text = content.split("%");

    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public RootDiagram(int pno) {
        this.pno = pno;
    }

    public RootDiagram(int pno, String title, String content, String meaning) {
        this.pno = pno;
        this.title = title;
        this.content = content;
        this.meaning = meaning;
        this.text = content.split("%");
        if (text.length<3){
            for (int i=text.length-1;i<text.length;i++)
                text[i] = "";
        }

    }

    public int compareTo(Object o) {
        RootDiagram rootDiagram = (RootDiagram)o;
        if (this.pno>rootDiagram.pno)
            return 1;
        if (this.pno==rootDiagram.pno)
            return 0;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "RootDiagram{" +
                "pno=" + pno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", text=" + Arrays.toString(text) +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}
