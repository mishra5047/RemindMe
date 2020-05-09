package com.example.remindme.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.remindme.R;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemAdapter> mList;
    private Context mContext;

    public ReminderAdapter(List<ItemAdapter> list, Context context){
        super();
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public ReminderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View booksView = LayoutInflater.from(mContext).inflate(R.layout.item_reminder, parent, false);

        ViewHolder viewHolder = new ViewHolder(booksView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemAdapter itemAdapter = mList.get(position);

        ((ViewHolder) holder).title.setText(itemAdapter.getTitle());
        ((ViewHolder) holder).description.setText(itemAdapter.getDescription());
        ((ViewHolder) holder).image.setImageResource(itemAdapter.getImage());
        ((ViewHolder) holder).priority.setText(itemAdapter.getPriority());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, priority;
        public ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleText);
            description = itemView.findViewById(R.id.descItem);
            image = itemView.findViewById(R.id.imageItem);
            priority = itemView.findViewById(R.id.priority);
        }
    }
}
