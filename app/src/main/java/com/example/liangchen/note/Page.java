package com.example.liangchen.note;

public class Page {
    private int id;
    private String title;
    private String date;
    private String content;

    public Page() {
        id = 0;
        title = "";
        date = "";
        content = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Page toPage(String content) {
        StringBuilder title = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '\n') {
                break;
            }
            title.append(content.charAt(i));
        }
        if (title.length() > 10) {
            title.delete(9, title.length());
            title.append("…");
        }
        Page page = new Page();
        page.setTitle(title.toString());
        Date date = new Date();
        page.setDate(date.getDate());
        page.setContent(content);
        return page;
    }

}
