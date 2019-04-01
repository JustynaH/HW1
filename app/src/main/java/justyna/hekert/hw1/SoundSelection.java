package justyna.hekert.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SoundSelection extends AppCompatActivity {

    private int selected_sound = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_selection);

        Intent received_intent = getIntent();
        Integer sound_id = received_intent.getIntExtra(MainActivity.SOUND_ID,0);
        //TextView txV = (TextView)findViewById(R.id.current_sound_text);
        //txV.setText(getText(R.string.current_sound_str) + sound_id.toString());
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            RadioGroup group = findViewById(R.id.radioGroup);
            switch (view.getId()){
                case R.id.radioButton: selected_sound = 0;break;
                case R.id.radioButton2: selected_sound = 1;break;
                case R.id.radioButton3: selected_sound = 2;break;
                case R.id.radioButton4: selected_sound = 3;break;
                case R.id.radioButton5: selected_sound = 4;break;
            }
        }
    }

    public void OK_Click(View v){
        Intent data = new Intent();
        data.putExtra(MainActivity.SOUND_ID,selected_sound);
        data.putExtra(MainActivity.INTENT_ID,"sound activity");
        setResult(RESULT_OK, data);
        finish();
    }

    public void CANCEL_Click(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}

