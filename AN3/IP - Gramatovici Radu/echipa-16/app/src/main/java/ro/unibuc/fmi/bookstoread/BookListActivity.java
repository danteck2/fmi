package ro.unibuc.fmi.bookstoread;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import ro.unibuc.fmi.bookstoread.Adapter.BookListAdapter;
import ro.unibuc.fmi.bookstoread.Model.Book;
import ro.unibuc.fmi.bookstoread.Model.Firestore.BookRecommended;

public class BookListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private TextView mNoBooks;
    private BookListAdapter mAdapter;
    private FirebaseFirestore mDb;
    private final String USERS_COLLECTION = "users";
    private final String FAVORITE_BOOKS_COLLECTION = "favoriteBooks";
    private final String READ_STATUS = "readStatus";
    private final String REVIEWED_BY_USER = "reviewedByUser";
    private final String RECOMMENDATIONS_BOOKS_COLLECTION = "recommendations";
    private List<Book> mBookList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        if(getIntent().getExtras() == null){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.books_rv);
        mNoBooks = (TextView) findViewById(R.id.no_books);
        mDb = FirebaseFirestore.getInstance();
        ActionBar actionBar = getSupportActionBar();
        switch (getIntent().getExtras().getInt("activity")){
            case 0:
                actionBar.setTitle(getResources().getString(R.string.recommendations_button));
                instantiateRecommendations();
                break;

            case 1:
                actionBar.setTitle(getResources().getString(R.string.favorites_button));
                instantiateFavoriteList();
                break;

            case 2:
                actionBar.setTitle(getResources().getString(R.string.to_read_button));
                instantiateList(0);
                break;

            case 3:
                actionBar.setTitle(getResources().getString(R.string.reading_button));
                instantiateList(1);
                break;

            case 4:
                actionBar.setTitle(getResources().getString(R.string.have_read_button));
                instantiateList(2);
                break;

            case 5:
                actionBar.setTitle(getResources().getString(R.string.reviewed_button));
                instantiateList();
                break;
        }

    }
    private void instantiateFavoriteList(){
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(FAVORITE_BOOKS_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    mBookList.add(document.toObject(Book.class));
                                }
                            }
                            mAdapter = new BookListAdapter(mBookList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BookListActivity.this);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.d("BookListActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void instantiateRecommendations(){
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(RECOMMENDATIONS_BOOKS_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    Book book = new Book();
                                    final BookRecommended currentRecommendedBook = document.toObject(BookRecommended.class);
                                    book.setId(document.getId().toString());
                                    if(currentRecommendedBook.getCover()!=null){
                                        book.setCover(currentRecommendedBook.getCover());
                                    }
                                    if(currentRecommendedBook.getAuthors()!=null){
                                        book.setAuthor(currentRecommendedBook.getAuthors().get(0));
                                    }
                                    if(currentRecommendedBook.getTitle()!=null){
                                        book.setTitle(currentRecommendedBook.getTitle());
                                    }
                                    book.setDescription("Not available!");
                                    mBookList.add(book);
                                }
                            }
                            mAdapter = new BookListAdapter(mBookList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BookListActivity.this);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.d("BookListActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void instantiateList(int readStatus){
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(REVIEWED_BY_USER)
                .whereEqualTo(READ_STATUS, readStatus)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    mBookList.add(document.toObject(Book.class));
                                }
                            }
                            mAdapter = new BookListAdapter(mBookList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BookListActivity.this);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.d("BookListActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void instantiateList(){
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(REVIEWED_BY_USER)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    mBookList.add(document.toObject(Book.class));
                                }
                            }
                            mAdapter = new BookListAdapter(mBookList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BookListActivity.this);
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.d("BookListActivity", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
