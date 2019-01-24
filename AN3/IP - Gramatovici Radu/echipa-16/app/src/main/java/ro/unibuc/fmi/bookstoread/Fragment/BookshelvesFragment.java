package ro.unibuc.fmi.bookstoread.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ro.unibuc.fmi.bookstoread.BookListActivity;
import ro.unibuc.fmi.bookstoread.R;

public class BookshelvesFragment extends Fragment {

    private Button mButtonRecommendations;
    private Button mButtonFavorites;
    private Button mButtonToRead;
    private Button mButtonReading;
    private Button mButtonHaveRead;
    private Button mButtonReviewed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookshelves, container, false);

        mButtonRecommendations = (Button) view.findViewById(R.id.recommendations_button);
        mButtonFavorites = (Button) view.findViewById(R.id.favorites_button);
        mButtonToRead = (Button) view.findViewById(R.id.to_read_button);
        mButtonReading = (Button) view.findViewById(R.id.reading_button);
        mButtonHaveRead = (Button) view.findViewById(R.id.have_read_button);
        mButtonReviewed = (Button) view.findViewById(R.id.reviewed_button);

        mButtonRecommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BookListActivity.class);
                i.putExtra("activity",0);
                startActivity(i);
            }
        });

        mButtonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BookListActivity.class);
                i.putExtra("activity",1);
                startActivity(i);
            }
        });

        mButtonToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BookListActivity.class);
                i.putExtra("activity",2);
                startActivity(i);
            }
        });

        mButtonReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BookListActivity.class);
                i.putExtra("activity",3);
                startActivity(i);
            }
        });

        mButtonHaveRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BookListActivity.class);
                i.putExtra("activity",4);
                startActivity(i);
            }
        });

        mButtonReviewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), BookListActivity.class);
                i.putExtra("activity",5);
                startActivity(i);
            }
        });

        return view;
    }
}
