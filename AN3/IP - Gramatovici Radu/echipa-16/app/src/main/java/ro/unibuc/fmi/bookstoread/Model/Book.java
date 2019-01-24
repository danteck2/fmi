package ro.unibuc.fmi.bookstoread.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String id;
    private String title;
    private String author;
    private String description;
    private String url;
    private String cover;
    public Book(String id, String title, String author, String description, String url, String cover) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.url = url;
        this.cover = cover;
    }

    public Book() {
    }

    public String getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getURL() {
        return url;
    }

    public String getCover() {
        return cover;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    // Parcelling part
    public Book(Parcel in){
        String[] data = new String[6];

        in.readStringArray(data);
        this.id = data[0];
        this.title = data[1];
        this.author = data[2];
        this.description = data[3];
        this.url = data[4];
        this.cover = data[5];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.id,
                this.title,
                this.author,this.description,this.url,this.cover});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

}
