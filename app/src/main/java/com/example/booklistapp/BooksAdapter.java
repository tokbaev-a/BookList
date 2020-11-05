package com.example.booklistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private List<Items> mItems;
    private Context mContext;
    RecyclerViewClickListener mListener;
    public BooksAdapter(Context context, List<Items> items, RecyclerViewClickListener listener){
        this.mItems = items;
        this.mContext = context;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list, parent, false);
        return new BooksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksAdapter.ViewHolder holder, int position) {
        final Items currentBook = mItems.get(position);
        holder.bookTitle.setText(currentBook.getVolumeInfo().getTitle());
        if (currentBook.getVolumeInfo().getAuthors() == null) {
            holder.bookAuthor.setText("Authors: N/A");
        } else {
            String bookAuthors = currentBook.getVolumeInfo().getAuthors().toString();
            holder.bookAuthor.setText("Authors: " + bookAuthors);
        }
        if (currentBook.getVolumeInfo().getCategory() == null) {
            holder.bookCategory.setText("Categories: N/A");
        } else {
            String categories = currentBook.getVolumeInfo().getCategory().toString();
            holder.bookCategory.setText("Categories: " + categories);
        }
        if(currentBook.getVolumeInfo().getPublishedDate() == null){
            holder.publishedDate.setText("Published Date: N/A");
        }else {
            holder.publishedDate.setText("Published Date: " + currentBook.getVolumeInfo().getPublishedDate());
        }
        if(currentBook.getVolumeInfo().getPageCount() == null){
            holder.bookPages.setText("Pages: N/A");
        }else {
            holder.bookPages.setText("Pages: " + currentBook.getVolumeInfo().getPageCount().toString());
        }
        holder.bookRating.setRating(currentBook.getVolumeInfo().getAverageRating());
        Glide
                .with(mContext)
                .load(currentBook.getVolumeInfo().getImageLinks().getThumbnail().replace("http", "https"))
                .into(holder.imageBook);
    }

    @Override
    public int getItemCount() {
        return (mItems != null) ? mItems.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageBook;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookCategory;
        TextView publishedDate;
        TextView bookPages;
        RatingBar bookRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBook = (ImageView) itemView.findViewById(R.id.bookImage);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitle);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthor);
            bookCategory = (TextView) itemView.findViewById(R.id.bookCategory);
            publishedDate = (TextView) itemView.findViewById(R.id.bookDate);
            bookPages = (TextView) itemView.findViewById(R.id.bookPages);
            bookRating = (RatingBar) itemView.findViewById(R.id.bookRating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.OnClick(view, getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListener{
        void OnClick(View v, int position);
    }
}
