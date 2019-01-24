package ro.unibuc.fmi.bookstoread.Model;

public class MachineLearningEvent {
    private int book_id;
    private boolean favorited = false;
    private boolean toRead = false;
    private boolean reading = false;
    private boolean haveRead = false;
    private String user_doc_id;
    private int user_id;
    private boolean comment = false;
    private int score = 0;

    public MachineLearningEvent() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isToRead() {
        return toRead;
    }

    public void setToRead(boolean toRead) {
        this.toRead = toRead;
    }

    public boolean isReading() {
        return reading;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public boolean isHaveRead() {
        return haveRead;
    }

    public void setHaveRead(boolean haveRead) {
        this.haveRead = haveRead;
    }

    public String getUser_doc_id() {
        return user_doc_id;
    }

    public void setUser_doc_id(String user_doc_id) {
        this.user_doc_id = user_doc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
