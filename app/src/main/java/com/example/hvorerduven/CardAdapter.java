package com.example.hvorerduven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewholder> {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardRef = database.getReference("Card");

    private ArrayList<Card> mCardList;

        public static class CardViewholder extends RecyclerView.ViewHolder{
            //public ImageView mImageView;
            public TextView mTextview1;
            public TextView mTextview2;

            public CardViewholder(@NonNull View itemView) {
                super(itemView);
                //indsæt data i vores felter f.eks. tekstfelter i vores kort
                //mImageView = itemView.findViewById(R.id.imageView);
                mTextview1 = itemView.findViewById(R.id.textView);
                mTextview2 = itemView.findViewById(R.id.textView2);
            }
        }

    //vi laver vores adapter, giver den en arrayliste af exampleitems, og giver herefter den arrayliste til mExampleList.
        public CardAdapter(ArrayList<Card> cardList) {
            mCardList = cardList;
        }

    @NonNull
    @Override
    public CardViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hovedsiden_item, parent, false);
        CardViewholder evh = new CardViewholder(v);
        return  evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewholder holder, int position) { //giver vores values til ExampleViewholder, fra den position der er givet ved 'position'
        Card currentCard = mCardList.get(position); //det item der er ved 'position' i arraylisten


        holder.mTextview1.setText(currentCard.getCardName());
        holder.mTextview2.setText(currentCard.getUserNamesInCard());

    }

    @Override
    public int getItemCount() { //definerer hvor mange items der er i vores liste
        return mCardList.size();
    }
}
