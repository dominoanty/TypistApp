package dbk.com.swiperapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameSelectionActivity extends AppCompatActivity {

    TextView gameModeTitle;
    Button paraModeButton;
    String modeTitleText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);
        gameModeTitle = (TextView) findViewById(R.id.modeTitle);
        paraModeButton = (Button) findViewById(R.id.paraModeButton);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        modeTitleText = i.getStringExtra(MainActivity.GAME_MODE);
        gameModeTitle.setText(modeTitleText);

        paraModeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                paraModeCall();
            }
        });

    }

    protected void paraModeCall()
    {
        Intent i = new Intent(this, ParaModeActivity.class);
        i.putExtra(MainActivity.GAME_MODE,modeTitleText);
        startActivity(i);
    }

}
