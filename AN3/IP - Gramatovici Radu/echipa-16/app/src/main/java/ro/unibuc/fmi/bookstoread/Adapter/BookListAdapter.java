package ro.unibuc.fmi.bookstoread.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ro.unibuc.fmi.bookstoread.BookActivity;
import ro.unibuc.fmi.bookstoread.Model.Book;
import ro.unibuc.fmi.bookstoread.R;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.MyViewHolder> {

    private List<Book> bookList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, authors;
        public ImageView cover;

        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title_show);
            authors =  view.findViewById(R.id.authors_show);
            cover =  view.findViewById(R.id.cover);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            onItemClick(view, getAdapterPosition());
        }
    }
    private void onItemClick(View view, int position) {
        Intent i = new Intent(view.getContext(), BookActivity.class);
        i.putExtra("book",bookList.get(position));
        view.getContext().startActivity(i);
    }


    public BookListAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.authors.setText( book.getAuthor());
        Uri uri = Uri.parse(book.getCover());
        Context context = holder.cover.getContext();
        Picasso.with(context).load(uri).into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public void clear() {
        final int size = bookList.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                bookList.remove(0);
                notifyItemRemoved(0);
            }

            notifyItemRangeRemoved(0, size);
        }
        notifyDataSetChanged();
    }

    public void addList(List<Book> books){
        bookList = books;
        this.notifyDataSetChanged();
    }
}