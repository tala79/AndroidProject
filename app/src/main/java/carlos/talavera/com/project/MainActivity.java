package carlos.talavera.com.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class
MainActivity extends AppCompatActivity {

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        onNewIntent(getIntent());
    }

    @Override
    public void onNewIntent(Intent intent){
        type = intent.getType();
        System.out.println("Tipo del intent" + type);

    }
}
