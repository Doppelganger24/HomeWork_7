package ru.geekbrains.HomeWork;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.sql.DataSource;

import ru.geekbrains.HomeWork.Data.CardData;
import ru.geekbrains.HomeWork.Data.CardSource;
import ru.geekbrains.HomeWork.Data.CardSourceImpl;
import ru.geekbrains.HomeWork.UI.AdapterNote;
import ru.geekbrains.cityheraldry.R;

public class NoteListFragment extends Fragment {

    public static final String NoteList = "NoteList";
    private Note currentNote;
    private boolean isLandscape;
    private CardSourceImpl cardSourceImpl;
    private AdapterNote adapterNote;
    private RecyclerView recyclerView;


    public static NoteListFragment newInstance() {
        return new NoteListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_lines);
        initRecyclerView(recyclerView);
        setHasOptionsMenu(true);
        return view;

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.my_menu, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add:
                cardSourceImpl.addCardData(new CardData("Новая заметка" + (cardSourceImpl.size() + 1)));
                adapterNote.notifyItemInserted(cardSourceImpl.size() - 1);
                recyclerView.smoothScrollToPosition(cardSourceImpl.size() - 1);
                return true;
            case R.id.delete:
                cardSourceImpl.clearCardData();
                adapterNote.notifyDataSetChanged();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapterNote.getPosition();
        switch (item.getItemId()) {

            case R.id.add:
                cardSourceImpl.addCardData(new CardData("Новая заметка" + (cardSourceImpl.size() + 1)));
                adapterNote.notifyItemInserted(cardSourceImpl.size() - 1);
                recyclerView.smoothScrollToPosition(cardSourceImpl.size() - 1);
                return true;
            case R.id.delete:
                cardSourceImpl.deleteCardData(position);
                adapterNote.notifyItemRemoved(position);


                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        cardSourceImpl = new CardSourceImpl(getResources());
        cardSourceImpl.init();
        String[] descriptionNote = getResources().getStringArray(R.array.noteDescription);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapterNote = new AdapterNote(cardSourceImpl, this);
        recyclerView.setAdapter(adapterNote);
        adapterNote.SetOnItemClickListener(new AdapterNote.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                currentNote = new Note(position, descriptionNote[position]);
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
