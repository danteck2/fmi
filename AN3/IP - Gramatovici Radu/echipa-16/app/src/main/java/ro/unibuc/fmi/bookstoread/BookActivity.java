package ro.unibuc.fmi.bookstoread;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import ro.unibuc.fmi.bookstoread.Adapter.ReviewListAdapter;
import ro.unibuc.fmi.bookstoread.Model.Book;
import ro.unibuc.fmi.bookstoread.Model.BookExtended;
import ro.unibuc.fmi.bookstoread.Model.Firestore.BooksCollection;
import ro.unibuc.fmi.bookstoread.Model.MachineLearningEvent;
import ro.unibuc.fmi.bookstoread.Model.Review;

public class BookActivity extends AppCompatActivity {
    private Book mBook;
    private ImageView mCover;
    private TextView mTitle;
    private TextView mAuthor;
    private TextView mAverageVote;
    private RatingBar mRatingBar;
    private TextView mDescription;
    private ImageButton mFavorite;
    private EditText mComment;
    private RadioGroup mReadStatus;
    private Button mSave;
    private FirebaseFirestore mDb;
    private final String USERS_COLLECTION = "users";
    private final String FAVORITE_BOOKS_COLLECTION = "favoriteBooks";
    private final String REVIEWED_BY_USER = "reviewedByUser";
    private final String REVIEWES_COLLECTION = "reviews";
    private final String BOOK_ID = "bookId";
    private ArrayList<Review> mReviewsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReviewListAdapter mAdapter;
    private MachineLearningEvent mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().setTitle(getResources().getString(R.string.book_summary));
        if (getIntent().getExtras() != null) {
            mBook = getIntent().getExtras().getParcelable("book");
        } else {
            finish();
        }
        mDb = FirebaseFirestore.getInstance();
        instantiateViews();
        getCurrentFavorite();
        populateViews();
        populateReviews();
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int readStatus = mReadStatus.indexOfChild(findViewById(mReadStatus.getCheckedRadioButtonId())); // 0 - to read; 1 - reading; 2 - read
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                addReview(new Review(mBook.getID(),FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),mRatingBar.getRating(),mComment.getText().toString(), dateFormat.format(date)),readStatus);
            }
        });
    }

    private void addReview(Review review, final int readStatus){
        // Store the reviewed book for each user
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(REVIEWED_BY_USER)
                .document(mBook.getID())
                .set(new BookExtended(mBook.getID(),mBook.getTitle(),mBook.getAuthor(),mBook.getDescription(),mBook.getURL(),mBook.getCover(),readStatus))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        addMachineLearningEvent(readStatus);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("UserReviewedBookInsert", "Error writing document", e);
                    }
                });
        // Store the review
        mDb.collection(REVIEWES_COLLECTION)
                .add(review)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(BookActivity.this, "Inserted!", Toast.LENGTH_SHORT).show();
                        if(mAdapter!=null){
                            mAdapter.clear();
                        }
                        mComment.setText("");
                        populateReviews();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("ReviewInsert", "Error adding document", e);
                    }
                });
    }

    private void addFavoriteBook() {
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(FAVORITE_BOOKS_COLLECTION)
                .document(mBook.getID())
                .set(mBook)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        addMachineLearningEvent(3);
                        instantiateFavoriteButton(true);
                        Toast.makeText(BookActivity.this, "Book saved!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FavoriteInsert", "Error writing document", e);
                    }
                });
    }

    private void removeFavoriteBook() {
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(FAVORITE_BOOKS_COLLECTION)
                .document(mBook.getID())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(BookActivity.this, "Book unsaved!", Toast.LENGTH_SHORT).show();
                        instantiateFavoriteButton(false);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FavoriteDeleted", "Error deleting document", e);
                    }
                });
    }

    private void getCurrentFavorite() {
        DocumentReference docRef = mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(FAVORITE_BOOKS_COLLECTION)
                .document(mBook.getID());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    instantiateFavoriteButton(true);
                } else {
                    instantiateFavoriteButton(false);
                }
            }

        });
    }

    private void instantiateFavoriteButton(boolean isFavorite) {
        if (isFavorite) {
            mFavorite.setImageResource(R.drawable.ic_favorite_true);
            mFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeFavoriteBook();
                }
            });
        } else {
            mFavorite.setImageResource(R.drawable.ic_favorite_false);
            mFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addFavoriteBook();
                }
            });
        }
    }

    private void addMachineLearningEvent(final int event){// 0 toread, 1 reading, 2 haveread, 3 favorite
        DocumentReference docRef = mDb.collection("books").document(mBook.getID());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        BooksCollection currentBook = document.toObject(BooksCollection.class);
                        mEvent = new MachineLearningEvent();
                        mEvent.setBook_id(currentBook.getBook_id());
                        switch (event){
                            case 0:
                                mEvent.setToRead(true);
                                mEvent.setComment(true);
                                mEvent.setScore(mRatingBar.getNumStars());
                                break;
                            case 1:
                                mEvent.setReading(true);
                                mEvent.setComment(true);
                                mEvent.setScore(mRatingBar.getNumStars());
                                break;
                            case 2:
                                mEvent.setHaveRead(true);
                                mEvent.setComment(true);
                                mEvent.setScore(mRatingBar.getNumStars());
                                break;
                            case 3:
                                mEvent.setFavorited(true);
                                break;
                        }
                        mEvent.setUser_doc_id(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        //Get current user ml id
                        DocumentReference docRef = mDb.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        mEvent.setUser_id(document.getLong("user_id_ml").intValue());
                                        mDb.collection("ml_events").add(mEvent);
                                    } else {
                                        Log.d("Get user id ml", "No such document");
                                    }
                                } else {
                                    Log.d("Get user id ml", "get failed with ", task.getException());
                                }
                            }
                        });
                    } else {
                        //TODO: Add book to books collection incrementing book_id = books.size + 1
                    }
                } else {
                    Log.d("BookActivity", "get failed with ", task.getException());
                }
            }
        });
    }

    private void instantiateViews() {
        mCover = findViewById(R.id.book_cover);
        mTitle = findViewById(R.id.book_title);
        mAuthor = findViewById(R.id.book_author);
        mAverageVote = findViewById(R.id.book_vote);
        mRatingBar = findViewById(R.id.book_rating_bar);
        mDescription = findViewById(R.id.book_description);
        mFavorite = findViewById(R.id.book_favorite);
        mComment = findViewById(R.id.book_comment);
        mReadStatus = findViewById(R.id.book_read_status);
        mSave = findViewById(R.id.book_send_comment);
        mRecyclerView = (RecyclerView) findViewById(R.id.reviews_rv);
    }

    private void populateViews() {
        if (!mBook.getCover().equals("")) {
            Picasso.with(this)
                    .load(mBook.getCover())
                    .placeholder(android.R.drawable.ic_menu_myplaces)
                    .error(android.R.drawable.ic_delete)
                    .resize(300, 300)
                    .into(mCover);
        }
        mTitle.setText(mBook.getTitle());
        mAuthor.setText(mBook.getAuthor());
        if(!mBook.getDescription().equals("Not available!")){
            mDescription.setText(mBook.getDescription());
        }else{
            DocumentReference docRef = mDb.collection("books").document(mBook.getID());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            mBook.setDescription(document.getString("description"));
                            mDescription.setText(mBook.getDescription());
                        } else {
                            Log.d("Get book desc", "No such document");
                        }
                    } else {
                        Log.d("Get book desc", "get failed with ", task.getException());
                    }
                }
            });
        }
    }

    private void populateReviews(){
        mDb.collection(REVIEWES_COLLECTION)
                .whereEqualTo(BOOK_ID,mBook.getID())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            float rating = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.exists()){
                                    mReviewsList.add(document.toObject(Review.class));
                                    rating+=mReviewsList.get(mReviewsList.size()-1).getRating();
                                }
                                mAverageVote.setText(String.valueOf(rating/mReviewsList.size()));
                            }
                            Collections.sort(mReviewsList);
                            mAdapter = new ReviewListAdapter(mReviewsList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(BookActivity.this);
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
