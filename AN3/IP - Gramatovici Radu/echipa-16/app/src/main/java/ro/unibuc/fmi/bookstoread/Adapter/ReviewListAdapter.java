package ro.unibuc.fmi.bookstoread.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ro.unibuc.fmi.bookstoread.Model.Review;
import ro.unibuc.fmi.bookstoread.R;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ReviewHolder> {

    private List<Review> reviewsList;

    public class ReviewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView rating;
        private TextView comment;

        public ReviewHolder(View view){
            super(view);
            name = view.findViewById(R.id.username);
            rating =  view.findViewById(R.id.rating);
            comment =  view.findViewById(R.id.comment);
        }

        public TextView getName() {
            return name;
        }

        public TextView getRating() {
            return rating;
        }

        public TextView getComment() {
            return comment;
        }
    }


    public ReviewListAdapter(List<Review> reviewsList) {
        this.reviewsList = reviewsList;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item_layout, parent, false);

        return new ReviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        Review review = reviewsList.get(position);
        holder.getName().setText(review.getUsername());
        holder.getRating().setText(String.valueOf(review.getRating()));
        holder.getComment().setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    public void clear() {
        final int size = reviewsList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                reviewsList.remove(0);
                notifyItemRemoved(0);
            }

            notifyItemRangeRemoved(0, size);
        }
        notifyDataSetChanged();
    }

}