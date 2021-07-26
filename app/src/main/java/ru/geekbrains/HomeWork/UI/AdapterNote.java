package ru.geekbrains.HomeWork.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import javax.sql.DataSource;

import ru.geekbrains.HomeWork.Data.CardData;
import ru.geekbrains.HomeWork.Data.CardSource;
import ru.geekbrains.cityheraldry.R;


public class AdapterNote extends RecyclerView.Adapter<AdapterNote.ViewHolder> {


    //private final String[] dataSource;
    private CardSource dataSource;
    private OnItemClickListener itemClickListener;


    public AdapterNote(CardSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterNote.ViewHolder holder, int position) {
       /* TextView hold = holder.itemView.findViewById(R.id.textView1);
        hold.setText(dataSource[position]);*/

        holder.setData(dataSource.getCardData(position));
    }


    @Override
    public int getItemCount() {
        return dataSource.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteList;

        private void setData(CardData cardData) {
            noteList.setText(cardData.getDescription());
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //cardView = (CardView) itemView;
            noteList = (itemView).findViewById(R.id.textView1);
           noteList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }

        /*public CardView getTextView() {
            return cardView;
        }*/


        }
   public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }



}
