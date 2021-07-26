package ru.geekbrains.HomeWork;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.HomeWork.UI.AdapterNote;
import ru.geekbrains.cityheraldry.R;

public class NoteListFragment extends Fragment {

    public static final String NoteList = "NoteList";
    private Note currentNote;
    private boolean isLandscape;

    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        String[] data = getResources().getStringArray(R.array.NoteList);
        initRecyclerView(recyclerView, data);
        return view;

    }

    private void initRecyclerView(RecyclerView recyclerView, String[] data) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        final AdapterNote adapterNote = new AdapterNote(data);
        recyclerView.setAdapter(adapterNote);
        adapterNote.SetOnItemClickListener(new AdapterNote.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentNote = new Note(position, data[position]);
                ShowNoteDescription(currentNote);
            }
        });
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
        ((FragmentTransaction) fragmentTransaction).replace(R.id.NoteText, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    public void showPortNoteDescription(Note currentNote) {

        Intent intent = new Intent();
        intent.setClass(getActivity(), NoteDescriptionActivity.class);
        intent.putExtra(NoteDescriptionFragment.NOTE_LIST, currentNote);
        startActivity(intent);
    }
}
