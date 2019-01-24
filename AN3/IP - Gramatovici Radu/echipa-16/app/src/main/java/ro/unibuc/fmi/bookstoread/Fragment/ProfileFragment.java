package ro.unibuc.fmi.bookstoread.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ro.unibuc.fmi.bookstoread.Adapter.BookListAdapter;
import ro.unibuc.fmi.bookstoread.BookListActivity;
import ro.unibuc.fmi.bookstoread.LoginActivity;
import ro.unibuc.fmi.bookstoread.Model.Book;
import ro.unibuc.fmi.bookstoread.R;

public class ProfileFragment extends Fragment {
    private ImageView mUserPic;
    private TextView mUserName;
    private Button mButton;
    private RecyclerView mRecyclerView;
    private TextView mNoBooks;
    private BookListAdapter mAdapter;
    private FirebaseFirestore mDb;
    private final String USERS_COLLECTION = "users";
    private final String FAVORITE_BOOKS_COLLECTION = "favoriteBooks";
    private List<Book> mBookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        mButton = (Button) view.findViewById(R.id.log_out_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.books_rv);
        mNoBooks = (TextView) view.findViewById(R.id.no_books);
        mDb = FirebaseFirestore.getInstance();
        mUserName = (TextView) view.findViewById(R.id.user_name);
        mUserPic = (ImageView) view.findViewById(R.id.user_pic);
        mUserName.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Picasso.with(getContext())
                .load("https://graph.facebook.com/" + Profile.getCurrentProfile().getId() + "/picture")
                .placeholder(android.R.drawable.ic_menu_myplaces)
                .error(android.R.drawable.ic_delete)
                .resize(300, 300)
                .into(mUserPic);
        instantiateFavoriteList();
        return view;
    }

    private void instantiateFavoriteList() {
        mDb.collection(USERS_COLLECTION)
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection(FAVORITE_BOOKS_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            mBookList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.exists()) {
                                    mBookList.add(document.toObject(Book.class));
                                }
                            }
                            if (mAdapter != null) {
                                mAdapter.clear();
                            }
                            mAdapter = new BookListAdapter(mBookList);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            Log.d("@@@@@@@@@@", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
