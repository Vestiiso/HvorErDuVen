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

//hovedsidens RecyclerView+Cardview+adapter opsætning inkl. indsæt/fjern knapperne er lavet ud fra fremgangsmåden i en youtube serie af Coding in flow. Links til videoerne herunder:
// https://www.youtube.com/watch?v=Nw9JF55LDzE&t=1s
// https://www.youtube.com/watch?v=17NbUcEts9c
// https://www.youtube.com/watch?v=kaf2dCd8Zfs
// https://www.youtube.com/watch?v=bhhs4bwYyhc
//fuld playliste: https://www.youtube.com/playlist?list=PLrnPJCHvNZuBtTYUuc5Pyo4V7xZ2HNtf4



public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewholder> {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardRef = database.getReference("Card");

    private ArrayList<Card> mCardList;
    private OnItemClickListener mListener;

    //lyt efter om man klipper på et af kortene
    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardViewholder extends RecyclerView.ViewHolder{ //anbefalet at holde det som en static class fordi det er en inder-class
            //public ImageView mImageView;
            public TextView mTextview1;
            public TextView mTextview2;

            public CardViewholder(@NonNull View itemView, final OnItemClickListener listener) {
                super(itemView);
                //indsæt data i vores felter f.eks. tekstfelter i vores kort
                //mImageView = itemView.findViewById(R.id.imageView);
                mTextview1 = itemView.findViewById(R.id.textView);
                mTextview2 = itemView.findViewById(R.id.textView2);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();

                            if (position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
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
        CardViewholder evh = new CardViewholder(v, mListener);
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
