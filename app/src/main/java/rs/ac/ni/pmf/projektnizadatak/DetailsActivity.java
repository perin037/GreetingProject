package rs.ac.ni.pmf.projektnizadatak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {

    public static final String TAG = "Greeting_info";

    public static final String FIRST_KEY = "FIRST_NAME";
    public static final String LAST_KEY = "LAST_NAME";

    private EditText _editFirst;
    private EditText _editLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        _editFirst = findViewById(R.id.editFirstName);
        _editLast = findViewById(R.id.editLastName);

        final Intent intent = getIntent();

        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel(view);
            }
        });
        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }
        });
    }

    private void save(View view) {
        String firstN = _editFirst.getText().toString();
        String lastN = _editLast.getText().toString();

        //Log.i(TAG, "On save button firstName is " + firstN);

        if(!firstN.equals("") && !lastN.equals("")) {
            final Intent resultatIntent = new Intent();

            resultatIntent.putExtra(FIRST_KEY, firstN);
            resultatIntent.putExtra(LAST_KEY, lastN);

            setResult(RESULT_OK, resultatIntent);

            finish();
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), R.string.action_incomplete, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void cancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}