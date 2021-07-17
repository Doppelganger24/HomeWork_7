package ru.geekbrains.HomeWork.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import ru.geekbrains.HomeWork.Note;
import ru.geekbrains.cityheraldry.R;


public class AdapterNote extends RecyclerView.Adapter<AdapterNote.ViewHolder> {

    private Note.CardsSource dataSource;
    private OnItemClickListener itemClickListener;



    public AdapterNote(Note.CardsSource dataSource) {
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
    public void onBindViewHolder(AdapterNote.ViewHolder viewHolder, int position) {
      viewHolder.setData (dataSource.getCardData(position));

    }


    @Override
    public int getItemCount() {
        return dataSource.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.text11);
            description.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }

        public void setData(Note cardData){
            description.setText(cardData.getNoteDescriptionIndex());

        }

        /*public TextView getTextView() {
            return textView;
        }*/
    }

    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }


}
