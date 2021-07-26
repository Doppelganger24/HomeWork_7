package ru.geekbrains.HomeWork.Data;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.cityheraldry.R;

public class CardSourceImpl implements CardSource {
    private List<CardData> dataSource;
    private Resources resources;

    public CardSourceImpl(Resources resources) {
        dataSource = new ArrayList<>();
        this.resources = resources;

    }

    private List<CardData> init() {
        String[] noteList = resources.getStringArray(R.array.NoteList);
        for (int i = 0; i < noteList.length; i++) {
            dataSource.add(new CardData(noteList[i]));
        }
        return dataSource;
    }

    @Override
    public int size() {
        return dataSource.size();
    }

    @Override
    public CardData getCardData(int position) {
        return dataSource.get(position);
    }

    public List<CardData> getDataSource() {
        return dataSource;
    }
}
