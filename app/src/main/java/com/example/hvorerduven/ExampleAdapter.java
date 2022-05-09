package com.example.hvorerduven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewholder> {
    private ArrayList<ExampleItem> mExampleList;

        public static class ExampleViewholder extends RecyclerView.ViewHolder{
            public ImageView mImageView;
            public TextView mTextview1;
            public TextView mTextview2;

            public ExampleViewholder(@NonNull View itemView) {
                super(itemView);
                //indsæt data i vores felter f.eks. tekstfelter i vores kort
                mImageView = itemView.findViewById(R.id.imageView);
                mTextview1 = itemView.findViewById(R.id.textView);
                mTextview2 = itemView.findViewById(R.id.textView2);
            }
        }

    //vi laver vores adapter, giver den en arrayliste af exampleitems, og giver herefter den arrayliste til mExampleList.
        public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
            mExampleList = exampleList;
        }

    @NonNull
    @Override
    public ExampleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hovedsiden_item, parent, false);
        ExampleViewholder evh = new ExampleViewholder(v);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewholder holder, int position) { //giver vores values til ExampleViewholder, fra den position der er givet ved 'position'
        ExampleItem currentItem = mExampleList.get(position); //det item der er ved 'position' i arraylisten

        holder.mImageView.setImageResource(currentItem.getmImageResource()); //tager billedressourcen fra vores item og sætter den ind i holders imageview
        holder.mTextview1.setText(currentItem.getmText1());
        holder.mTextview2.setText(currentItem.getmText2());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
