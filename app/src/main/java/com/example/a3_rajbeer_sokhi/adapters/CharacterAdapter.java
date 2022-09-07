package com.example.a3_rajbeer_sokhi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a3_rajbeer_sokhi.databinding.CharacterRowLayoutBinding;
import com.example.a3_rajbeer_sokhi.models.ZeldaCharacters;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ItemViewHolder>{

    private final Context context;
    private final ArrayList<ZeldaCharacters> itemArrayList;
    CharacterRowLayoutBinding binding;



    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    public CharacterAdapter(Context context, ArrayList<ZeldaCharacters> items){
        this.itemArrayList = items;
        this.context = context;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CharacterRowLayoutBinding itemBinding;
        public ItemViewHolder(CharacterRowLayoutBinding binding) {
            super(binding.getRoot());
            // Define click listener for the ViewHolder's View
            this.itemBinding = binding;
        }

        public void bind(Context context, ZeldaCharacters currentItem){
            // TODO: Update the UI for the row
            String name = currentItem.getName();
            name = name.substring(0,1).toUpperCase() + name.substring(1);
            itemBinding.tvCharacterName.setText(name);
            itemBinding.tvDesc.setText(currentItem.getDescription());
            itemBinding.tvAttack.setText("Attack: " + currentItem.getAttack());
            itemBinding.tvDefense.setText("Defense: " + currentItem.getDefense());

            Glide.with(context).load(currentItem.getImageURL()).into(itemBinding.ivCharacterPhoto);

        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        return new ItemViewHolder(CharacterRowLayoutBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, final int position) {

        ZeldaCharacters currentItem = itemArrayList.get(position);
        viewHolder.bind(context,currentItem);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        Log.d("CharacterAdapter","GetItemCount: Number of items " + this.itemArrayList.size());
        return this.itemArrayList.size();
    }
}
