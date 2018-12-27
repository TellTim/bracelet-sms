package com.bracelet.sms.ui.adapter;

/**
 * Created by Tim.WJ on 2018/12/26.
 */
public class Note {

    private String note_title;
    private String note_content;
    private String note_content_abs;
    private String note_time;

    public Note(String note_title, String note_content, String note_content_abs, String note_time) {
        this.note_title = note_title;
        this.note_content = note_content;
        this.note_content_abs = note_content_abs;
        this.note_time = note_time;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getNote_content_abs() {
        return note_content_abs;
    }

    public void setNote_content_abs(String note_content_abs) {
        this.note_content_abs = note_content_abs;
    }

    public String getNote_time() {
        return note_time;
    }

    public void setNote_time(String note_time) {
        this.note_time = note_time;
    }

}
