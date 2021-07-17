package ru.geekbrains.HomeWork;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.geekbrains.HomeWork.UI.AdapterNote;
import ru.geekbrains.cityheraldry.R;

public class NoteDescriptionFragment extends Fragment {

    public static final String NOTE_LIST = "noteList";
    private Note note;


    public static NoteDescriptionFragment newInstance(Note note) {
        NoteDescriptionFragment f = new NoteDescriptionFragment();

        Bundle args = new Bundle();
        args.putParcelable(NOTE_LIST, note);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(NOTE_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.text11);
        Note.CardsSource data = new CardsSourceImpl(getResources()).init();
        initRecyclerView(recyclerView, data);
        return view;


    }

    private void initRecyclerView(RecyclerView recyclerView, Note.CardsSource
            data) {

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final AdapterNote adapter = new AdapterNote(data);


    }
}



