package ro.unibuc.fmi.bookstoread.Loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import ro.unibuc.fmi.bookstoread.Model.Book;
import ro.unibuc.fmi.bookstoread.Utils.QueryUtils;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String mUrl;

    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}