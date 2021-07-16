package ru.geekbrains.cityheraldry;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View view = inflater.inflate(R.layout.fragment_note_description, container, false);
        TextView textView = view.findViewById(R.id.textView);
        String[] noteDescription = view.getResources().getStringArray(R.array.noteDescription);
        for (int i = 0; i < noteDescription.length; i++) {
            textView.setText(noteDescription[note.getNoteIndex()]);
        }
        return view;

    }
}