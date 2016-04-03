package dbk.com.swiperapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ParaModeActivity extends AppCompatActivity {
    ParaGetter paraGetter;
    ArrayList<String>  wordsList;
    TextView paraTextView;
    TextView timerView;
    Boolean shouldInputBeCleaned;
    Boolean alertSet;
    EditText editText;
    Button refreshButton;
    ProgressBar typingProgressBar;
    int noWords;
    int wordsIndex = 0;
    CountDownTimer cdTimer;
    protected void refreshPara()
    {
        paraGetter=new ParaGetter(this);
        String para = paraGetter.getRandomPara();                  //get paragraph
        wordsList = paraGetter.getWords(para);                     //get ARRAYLIST of Words
        paraTextView.setText(para, TextView.BufferType.SPANNABLE);
        typingProgressBar.setProgress(0);
        shouldInputBeCleaned = false;
        alertSet = false;
        wordsIndex = 0;
        noWords = paraGetter.getNoWords();
        cdTimer= new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerView.setText("00:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerView.setText("Time Up!");
            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paramode);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        refreshButton = (Button) findViewById(R.id.refreshButton);
        paraTextView = (TextView)findViewById(R.id.paraTextView);
        editText = (EditText) findViewById(R.id.editText);
        timerView = (TextView) findViewById(R.id.timerView);
        typingProgressBar = (ProgressBar) findViewById(R.id.typingProgressBar);
        refreshPara();

        refreshButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshPara();
            }
        });
        
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().replace(" ","").equals(wordsList.get(wordsIndex)))
                {
                    shouldInputBeCleaned = true;
                    wordsIndex++;
                    typingProgressBar.setProgress((int)(100 * (float)wordsIndex/noWords));
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(shouldInputBeCleaned == true)
                {
                    shouldInputBeCleaned = false;
                    editText.setText("");
                    updateColor(paraTextView, paraGetter.getNextIndex(wordsIndex-1));
                }
                else if (!wordsList.get(wordsIndex).contains(s.toString().replace(" ","")))
                {
                    wrongAlert(paraTextView, paraGetter.getCurrentIndex() , paraGetter.lookaheadIndex(wordsIndex ));

                }



            }
        });
        //updateColor(paraTextView, paraGetter.getNextIndex(1));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public static void updateColor(TextView t,int lastindex)
    {
        Spannable wordtoSpan = new SpannableString(t.getText());
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.rgb(34,139,34)), 0, lastindex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        t.setText(wordtoSpan);
    }
    public static void wrongAlert(TextView t, int startindex, int endindex)
    {
        Spannable wordtoSpan = new SpannableString(t.getText());
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.rgb(139,34,34)), startindex, endindex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        t.setText(wordtoSpan);
    }

}
/*
class InputChecker implements Runnable
{
    Thread inputCheckerThread;
    EditText inputText;
    TextView paraTextView;
    ArrayList<String> wordsList;
    ParaGetter paraGetter;
    int wordsIndex ;
    InputChecker(EditText inputText,TextView paraTextView, ArrayList<String> wordsList, ParaGetter paraGetter )
    {
        this.inputText = inputText;
        this.paraTextView = paraTextView;
        this.wordsList = wordsList;
        inputCheckerThread = new Thread(this, "Input Checker Thread ");
        this.wordsIndex = 0;
        this.paraGetter = paraGetter;

    }

    public void start()
    {
        inputCheckerThread.start();
    }
    @Override
    public void run() {
        while(true)
        {
            if (inputText.getText().equals(wordsList.get(wordsIndex))) {
                ParaModeActivity.updateColor(paraTextView, paraGetter.getNextIndex(wordsIndex));
                inputText.setText(" ");
                wordsIndex++;
            }
        }
    }
}*/