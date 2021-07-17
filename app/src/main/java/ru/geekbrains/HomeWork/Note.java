package ru.geekbrains.HomeWork;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private int noteIndex;
    private String noteDescriptionIndex;

    public Note(int noteIndex, String noteDescriptionIndex) {
        this.noteIndex = noteIndex;
        this.noteDescriptionIndex = noteDescriptionIndex;
    }

    protected Note(Parcel in) {
        noteIndex = in.readInt();
        noteDescriptionIndex = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int notes) {
        dest.writeInt(getNoteIndex());
        dest.writeString(getNoteDescriptionIndex());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getNoteIndex() {
        return noteIndex;
    }

    public String getNoteDescriptionIndex() {
        return noteDescriptionIndex;
    }
}
