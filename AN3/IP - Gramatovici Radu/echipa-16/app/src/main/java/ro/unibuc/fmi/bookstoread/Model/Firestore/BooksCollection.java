package ro.unibuc.fmi.bookstoread.Model.Firestore;

import java.util.List;

public class BooksCollection {
    private String api_id;
    private List<String> authors;
    private int averageRating;
    private int book_id;
    private List<String> categories;
    private String cover;
    private String description;
    private int pageCount;
    private String publishedDate;
    private int ratingsCount;
    private String title;

    public BooksCollection() {
    }

    public BooksCollection(String api_id, List<String> authors, int averageRating, int book_id, List<String> categories, String cover, String description, int pageCount, String publishedDate, int ratingsCount, String title) {
        this.api_id = api_id;
        this.authors = authors;
        this.averageRating = averageRating;
        this.book_id = book_id;
        this.categories = categories;
        this.cover = cover;
        this.description = description;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.ratingsCount = ratingsCount;
        this.title = title;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public int getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(int averageRating) {
        this.averageRating = averageRating;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
