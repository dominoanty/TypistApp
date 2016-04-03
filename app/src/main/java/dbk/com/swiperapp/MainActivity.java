package dbk.com.swiperapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button singlePlayerButton;
    Button multiPlayerButton;
    public static String GAME_MODE = "dbk.com.swiperapp.GAME_MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singlePlayerButton = (Button) findViewById(R.id.singlePlayerButton);
        multiPlayerButton = (Button) findViewById(R.id.multiPlayerButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        singlePlayerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                singlePlayerCall();
            }
        });
        multiPlayerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                multiPlayerCall();
            }
        });

    }
    protected void singlePlayerCall()
    {
        Intent i = new Intent(this, GameSelectionActivity.class);
        i.putExtra(GAME_MODE, "SinglePlayer");
        startActivity(i);
    }
    protected void multiPlayerCall()
    {
        Intent i = new Intent(this, GameSelectionActivity.class);
        i.putExtra(GAME_MODE, "MultiPlayer");
        startActivity(i);
    }

}
