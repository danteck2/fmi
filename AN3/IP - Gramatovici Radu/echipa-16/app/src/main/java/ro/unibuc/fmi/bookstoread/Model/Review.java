package ro.unibuc.fmi.bookstoread.Model;

import android.support.annotation.NonNull;

public class Review implements Comparable {
    private String bookId;
    private String username;
    private float rating;
    private String comment;
    private String publicationDate;

    public Review() {
    }

    public Review(String bookId, String username, float rating, String comment, String publicationDate) {
        this.bookId = bookId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
        this.publicationDate = publicationDate;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUsername() {
        return username;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return this.publicationDate.compareTo(((Review)o).getPublicationDate());
    }
}
