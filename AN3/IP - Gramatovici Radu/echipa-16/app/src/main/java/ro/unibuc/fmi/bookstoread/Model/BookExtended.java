package ro.unibuc.fmi.bookstoread.Model;

public class BookExtended extends Book {
    private int readStatus;

    public BookExtended(String id, String title, String author, String description, String url, String cover, int readStatus) {
        super(id, title, author, description, url, cover);
        this.readStatus = readStatus;
    }

    public int getReadStatus() {
        return readStatus;
    }
}
