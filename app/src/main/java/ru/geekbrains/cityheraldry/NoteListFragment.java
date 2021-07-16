package ru.geekbrains.cityheraldry;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NoteListFragment extends Fragment {

    public static final String NoteList = "NoteList";
    private Note currentNote;
    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes_list = getResources().getStringArray(R.array.NoteList);
        for (int i = 0; i < notes_list.length; i++) {
            String notes = notes_list[i];
            TextView tv = new TextView(getContext());
            tv.setText(notes);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentNote = new Note(fi, getResources().getStringArray(R.array.NoteList)[fi]);
                    ShowNoteDescription(currentNote);
                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(NoteList, currentNote);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(NoteList);
        } else {
            currentNote = new Note(0, getResources().getStringArray(R.array.NoteList)[0]);
        }
        if (isLandscape) {
            showLandNoteDescription(currentNote);
        }
    }

    private void ShowNoteDescription(Note currentNote) {
        if (isLandscape) {
            showLandNoteDescription(currentNote);
        } else {
            showPortNoteDescription(currentNote);
        }
    }

    private void showLandNoteDescription(Note currentNote) {

        NoteDescriptionFragment detail = NoteDescriptionFragment.newInstance(currentNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.NoteText, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    private void showPortNoteDescription(Note currentNote) {

        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteDescriptionActivity.class);
        intent.putExtra(NoteDescriptionFragment.NOTE_LIST, currentNote);
        startActivity(intent);
    }
}