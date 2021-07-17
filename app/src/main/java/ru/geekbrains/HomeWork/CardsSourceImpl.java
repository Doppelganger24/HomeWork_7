package ru.geekbrains.HomeWork;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.cityheraldry.R;

public class CardsSourceImpl implements Note.CardsSource {

    private List<Note> dataSource;
    private Resources resources;

    public CardsSourceImpl(Resources resources) {
        dataSource = new ArrayList<>(5);
        this.resources = resources;
    }
    public CardsSourceImpl init(){

        String[] descriptions = resources.getStringArray(R.array.noteDescription);
        for (int i = 0; i < descriptions.length; i++) {
            dataSource.add(new Note(descriptions[i]));
        }
        return this;
    }
    @Override
    public Note getCardData(int position) {
        return dataSource.get(position);

    }

    @Override
    public int size() {
        return dataSource.size();
    }
}
