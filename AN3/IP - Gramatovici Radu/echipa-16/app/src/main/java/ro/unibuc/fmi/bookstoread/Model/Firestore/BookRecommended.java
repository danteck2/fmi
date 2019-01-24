package ro.unibuc.fmi.bookstoread.Model.Firestore;

import com.google.firebase.Timestamp;

import java.util.Date;
import java.util.List;

public class BookRecommended {
    private List<String> authors;
    private String cover;
    private Date timestamp;
    private String title;

    public BookRecommended() {
    }

    public BookRecommended(List<String> authors, String cover, Date timestamp, String title) {
        this.authors = authors;
        this.cover = cover;
        this.timestamp = timestamp;
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getCover() {
        return cover;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getTitle() {
        return title;
    }
}
