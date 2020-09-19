package com.example.mattespill;

import android.os.Parcel;
import android.os.Parcelable;

public class Results implements Parcelable {
    String name;
    String score;

    public Results(String name, String score){
        this.name = name;
        this.score = score;
    }

    private Results(Parcel in) {
        name = in.readString();
        score = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(score);
    }
    public static final Parcelable.Creator<Results> CREATOR =
            new Parcelable.Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
}
