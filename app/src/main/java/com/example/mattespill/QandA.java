package com.example.mattespill;

import android.os.Parcel;
import android.os.Parcelable;

public class QandA implements Parcelable{

    String question;
    String answer;

    public QandA(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    private QandA(Parcel in){
        question = in.readString();
        answer = in.readString();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags){
        out.writeString(question);
        out.writeString(answer);
    }

    public static final Parcelable.Creator<QandA> CREATOR = new Parcelable.Creator<QandA>() {
        @Override
        public QandA createFromParcel(Parcel in) {
            return new QandA(in);
        }

        @Override
        public QandA[] newArray(int size) {
            return new QandA[size];
        }
    };
}
