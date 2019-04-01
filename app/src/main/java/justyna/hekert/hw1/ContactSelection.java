package justyna.hekert.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class ContactSelection extends AppCompatActivity {

    private int selected_contact = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_selection);
    }

    public void OK_Click(View v){
        Intent data = new Intent();
        String content_name = ((Spinner)findViewById(R.id.spinner_c)).getSelectedItem().toString();
        data.putExtra(MainActivity.CONTACT_ID,content_name);
        data.putExtra(MainActivity.INTENT_ID,"contact activity");
        setResult(RESULT_OK, data);
        finish();
    }

    public void CANCEL_Click(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
}
