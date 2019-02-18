package com.londonappbrewery.quizzler;

public class TrueFalse {


    private int mQuestionId;
    private boolean mAnswer;

    public TrueFalse(int questionResourceId, boolean trueOrFalse){
        mQuestionId = questionResourceId;
        mAnswer = trueOrFalse;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public boolean getAnswer() {
        return mAnswer;
    }


}
