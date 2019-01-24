package ro.unibuc.fmi.bookstoread.Fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.unibuc.fmi.bookstoread.Adapter.BookListAdapter;
import ro.unibuc.fmi.bookstoread.Loader.BookLoader;
import ro.unibuc.fmi.bookstoread.Model.Book;
import ro.unibuc.fmi.bookstoread.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class DiscoverFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Book>> {
    private static final String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final int BOOK_LOADER_ID = 1;
    private TextView mEmptyStateTextView;
    private RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;
    private List<Book> bookList = new ArrayList<>();

    private EditText mTitle;
    private EditText mAuthor;
    private EditText mSubject;
    private EditText mIsbn;
    private String searchQuery;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        mEmptyStateTextView = view.findViewById(R.id.no_books);
        mRecyclerView = view.findViewById(R.id.books_search);

        mAdapter = new BookListAdapter(bookList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        mTitle = view.findViewById(R.id.title_search);
        mSubject = view.findViewById(R.id.subject_search);
        mAuthor = view.findViewById(R.id.author_search);
        mIsbn = view.findViewById(R.id.isbn_search);
        view.findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidSearch()) {
                    startRequest();
                }else{
                    Toast.makeText(getActivity(), getResources().getString(R.string.search_error), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean isValidSearch() {
        searchQuery = BOOK_REQUEST_URL;
        if (!mTitle.getText().toString().equals("")) {
            searchQuery += "+intitle:" + String.valueOf(mTitle.getText());
        }
        if (!mIsbn.getText().toString().equals("")) {
            searchQuery += "+isbn:" + String.valueOf(mIsbn.getText());
        }
        if (!mAuthor.getText().toString().equals("")) {
            searchQuery += "+inauthor:" + String.valueOf(mAuthor.getText());
    }
        if (!mSubject.getText().toString().equals("")) {
            searchQuery += "+subject:" + String.valueOf(mSubject.getText());
        }
        return !searchQuery.equals(BOOK_REQUEST_URL);
    }

    private void startRequest() {
        //Initialize connection
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyStateTextView.setVisibility(View.GONE);
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.restartLoader(BOOK_LOADER_ID, null, DiscoverFragment.this).forceLoad();
        } else {
            mEmptyStateTextView.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public android.support.v4.content.Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        return new BookLoader(getActivity(), searchQuery);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Book>> loader, List<Book> book) {
        mAdapter.clear();
        mAdapter.addList(book);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Book>> loader) {
        mAdapter.clear();
    }

}
