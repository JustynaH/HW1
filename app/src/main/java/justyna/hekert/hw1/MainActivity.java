package justyna.hekert.hw1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    public static final String INTENT_ID = "intent id";

    public static final String CONTACT_ID = "contact id";
    public static final int CONTACT_REQUEST = 0;
    private String current_contact = "Jon Doe";

    public static final String SOUND_ID = "sound id";
    public static final int SOUND_REQUEST = 0;
    private int current_sound = 0;

    private MediaPlayer buttonPlayer;
    static public Uri[] sounds;
    private boolean pause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button ch_contact  = findViewById(R.id.ch_contact_but);
        ch_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactSelect = new Intent(getApplicationContext(), ContactSelection.class);
                contactSelect.putExtra(CONTACT_ID,current_contact);
                startActivityForResult(contactSelect, CONTACT_REQUEST);
            }
        });

        final Button ch_sound  = findViewById(R.id.ch_sound_but);
        ch_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactSelect = new Intent(getApplicationContext(), SoundSelection.class);
                contactSelect.putExtra(SOUND_ID,current_sound);
                startActivityForResult(contactSelect, SOUND_REQUEST);
            }
        });

        TextView c_txv  = findViewById(R.id.contact_name);
        c_txv.setText(current_contact);

        sounds = new Uri[5];
        sounds[0] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ringd);
        sounds[1] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring01);
        sounds[2] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring02);
        sounds[3] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring03);
        sounds[4] = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ring04);

        buttonPlayer = new MediaPlayer();
        buttonPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pause) {
                    buttonPlayer.pause();
                    pause = false;
                } else {
                    Play();
                }
            }
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String w_intent = data.getStringExtra(INTENT_ID);

            if (requestCode == SOUND_REQUEST && w_intent.equals("sound activity")) {
                current_sound = data.getIntExtra(SOUND_ID, 0);
            }

            if (requestCode == CONTACT_REQUEST && w_intent.equals("contact activity")){
                current_contact = data.getStringExtra(CONTACT_ID);
                TextView c_txv  = findViewById(R.id.contact_name);
                c_txv.setText(current_contact);

                int random = (int) (Math.random() * ((8 - 1) + 1));
                ImageView c_pic = findViewById(R.id.contact_pic);

                switch(random)
                {
                    case 1: c_pic.setImageResource(R.drawable.avatar_1); break;
                    case 2: c_pic.setImageResource(R.drawable.avatar_2); break;
                    case 3: c_pic.setImageResource(R.drawable.avatar_3); break;
                    case 4: c_pic.setImageResource(R.drawable.avatar_4); break;
                    case 5: c_pic.setImageResource(R.drawable.avatar_5); break;
                    case 6: c_pic.setImageResource(R.drawable.avatar_6); break;
                    case 7: c_pic.setImageResource(R.drawable.avatar_7); break;
                    default: c_pic.setImageResource(R.drawable.avatar_8);
                }
            }
        }
        else if(resultCode == RESULT_CANCELED){
            Toast.makeText(getApplicationContext(),getText(R.string.back_message), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        buttonPlayer.pause();
        pause = false;
    }

    protected void Play(){

        buttonPlayer = MediaPlayer.create(this,sounds[current_sound]);

        buttonPlayer.start();
        buttonPlayer.setLooping(true);
        pause = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        buttonPlayer.release();
    }
}
