package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    OnLongClickListener longClickListener;

    List<String> items;
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items=items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent,false);
        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);
    }

    //responsible for binding data to a particular vieww holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // grab item at the position
    String item=items.get(position);

    // Bind item to specified view holder

        holder.bind(item);
    }

    @Override
    //tells the RV how many items are in the list
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list

    class ViewHolder extends RecyclerView.ViewHolder{
    TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem=itemView.findViewById(android.R.id.text1);
        }
        // update view inside of the view holder with this data

        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                public boolean onLongClick(View V){

                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
