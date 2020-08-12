package com.codecademy.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

class Questions implements Parcelable  {
    private int image;
    private int correctAnswer;
    private int category;
    private String question;
    private String optionOne;
    private String optionTwo;
    private String optionThree;
    private String optionFour;

    Questions(int category, String question, String optionOne, String optionTwo, String optionThree, String optionFour, int correctAnswer) {
        this.category = category;
        this.image = 0;
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAnswer = correctAnswer;
    }
    Questions(int category, int image, String question, String optionOne, String optionTwo, String optionThree, String optionFour, int correctAnswer) {
        this.category = category;
        this.image = image;
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = optionThree;
        this.optionFour = optionFour;
        this.correctAnswer = correctAnswer;


    }
    Questions(int category, String question, String optionOne, String optionTwo,int correctAnswer) {
        this.category = category;
        this.image = 0;
        this.question = question;
        this.optionOne = optionOne;
        this.optionTwo = optionTwo;
        this.optionThree = "";
        this.optionFour = "";
        this.correctAnswer = correctAnswer;


    }

    protected Questions(Parcel in) {
        image = in.readInt();
        correctAnswer = in.readInt();
        category = in.readInt();
        question = in.readString();
        optionOne = in.readString();
        optionTwo = in.readString();
        optionThree = in.readString();
        optionFour = in.readString();
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public int getImage() {
        return image;
    }

    public int getCategory() { return category;}

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,
            int flags) {
        dest.writeInt(image);
        dest.writeInt(correctAnswer);
        dest.writeInt(category);
        dest.writeString(question);
        dest.writeString(optionOne);
        dest.writeString(optionTwo);
        dest.writeString(optionThree);
        dest.writeString(optionFour);
    }
}
