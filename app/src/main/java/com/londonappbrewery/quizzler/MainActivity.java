package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {




    // TODO: Declare member variables here:
    Button mTrueButton, mFalseButton;
    ProgressBar mProgressBar;
    TextView mQuestionTextView,mScoreTextview;

    int i_currentQuestion = 0, mScore=0;

    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };



    // TODO: Declare constants here

    final int TOTAL_QUESTIONS = mQuestionBank.length;
    final int PROGRESS_BAR_INCREMENT = (int)Math.ceil(100.0/TOTAL_QUESTIONS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState != null){
            mScore=savedInstanceState.getInt("ScoreKey");
            i_currentQuestion=savedInstanceState.getInt("IndexKey");

        }else{
            i_currentQuestion=0;
            mScore = 0;
        }

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        mScoreTextview = (TextView)findViewById(R.id.score);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);

      View.OnClickListener myListener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // String toastMessage = "";
                switch (view.getId())
                {
                    case R.id.true_button :
                        //toastMessage = "True Btn Pressed!";

                        checkAnswerByBoolean(true);

                        break;

                    case R.id.false_button :
                        //toastMessage = "False Btn Pressed!";

                        checkAnswerByBoolean(false);
                        break;
                }

                changeToNextQuestion();

            }
        };

        UpdateScoreTextView();
        mFalseButton.setOnClickListener(myListener);
        mTrueButton.setOnClickListener(myListener);
    }


    //Intended to be call each time after question is answered, after checking the answer
    //Changes the text view text with the next question
    void changeToNextQuestion(){

        i_currentQuestion++;
        //i_currentQuestion = i_currentQuestion%TOTAL_QUESTIONS; // When it gets to 1
        if(i_currentQuestion>=TOTAL_QUESTIONS-1)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("You Scored "+ mScore + " points !");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
//            return;
        }

        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mQuestionTextView.setText(mQuestionBank[i_currentQuestion].getQuestionId());

    }


    //Intended to be used after clicking the users answer btn to check if it is right or not and send some feedback
    void checkAnswerByBoolean(Boolean answerBool){
       int toastMessage = R.string.incorrect_toast;
        if(answerBool == mQuestionBank[i_currentQuestion].getAnswer() ){
           toastMessage = R.string.correct_toast;
           mScore++;
           UpdateScoreTextView();
                 }
        Toast myToast = Toast.makeText(getApplicationContext(),toastMessage,Toast.LENGTH_SHORT);
        myToast.show();
    }

    void UpdateScoreTextView(){
        mScoreTextview.setText("Score "+String.valueOf(mScore)+"/"+String.valueOf(TOTAL_QUESTIONS));

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey", mScore);
        outState.putInt("IndexKey",i_currentQuestion);
    }
}

