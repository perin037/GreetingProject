package rs.ac.ni.pmf.projektnizadatak;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GreetingActivity extends AppCompatActivity {
    public static final String TAG = "Greeting_info";

    private static final int REQUEST_DETAILS = 1;
    private TextView _labelMessage;

    private String _firstName = "";
    private String _lastName = "";

    //dodatak zbog deprecated
    private ActivityResultLauncher<Intent> _detailsActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnEnterName).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterName(view);
            }
        });
        findViewById(R.id.btnGreeti).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                greet(view);
            }
        });

        _labelMessage = findViewById(R.id.labelMessage);


        //dodatak
        _detailsActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> onDetailsActivityResult(result.getResultCode(), result.getData())
        );
    }


    private void greet(View view) {
        //Log.i(TAG, "First is " + _firstName);
        //Log.i(TAG, "Last is " + _lastName);

        if(_firstName != "" && _lastName != "") {
            final String _fullMessage = getResources().getString(R.string.message, _firstName, _lastName);
            _labelMessage.setText(_fullMessage);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), R.string.action_incomplete, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void enterName(View view) {
        //aktiviram intent kojim se pokrece druga aktivnost kako bi dosli do podataka
        final Intent intent = new Intent(this, DetailsActivity.class);
        //startActivityForResult(intent, REQUEST_DETAILS);

        //dodatak
        _detailsActivityLauncher.launch(intent);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_DETAILS:
                if(resultCode == RESULT_OK){
                    _firstName = data.getStringExtra(DetailsActivity.FIRST_KEY);
                    _lastName = data.getStringExtra(DetailsActivity.LAST_KEY);
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.final_action, Toast.LENGTH_SHORT);
                    toast.show();
                }
                if(resultCode == RESULT_CANCELED){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.action_canceled, Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }*/


    private void onDetailsActivityResult(int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            _firstName = data.getStringExtra(DetailsActivity.FIRST_KEY);
            _lastName = data.getStringExtra(DetailsActivity.LAST_KEY);
            Toast toast = Toast.makeText(getApplicationContext(), R.string.final_action, Toast.LENGTH_SHORT);
            toast.show();
        }
        if(resultCode == RESULT_CANCELED){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.action_canceled, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}