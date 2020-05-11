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

    private List<ItemAdapter> list;
    Context context;

    public ReminderAdapter(List<ItemAdapter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent,false);

        ViewHolder viewHolder  = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ItemAdapter itemAdapter = list.get(position);

        ((ViewHolder) holder).name.setText(itemAdapter.getTitle());
        ((ViewHolder) holder).desc.setText(itemAdapter.getDescription());
        ((ViewHolder) holder).prior.setText(itemAdapter.getPriority());
        ((ViewHolder) holder).img.setImageResource(itemAdapter.getImage());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name, desc, prior;
        public ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameItem);
            desc = itemView.findViewById(R.id.descItem);
            prior = itemView.findViewById(R.id.priorityItem);
            img = itemView.findViewById(R.id.imageItem);
        }
}
}