package carlos.talavera.com.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class
MainActivity extends AppCompatActivity {

    private String type = "";
    private String action = "";
    private String intentContent ="";
    private String packageName = "";
    int mode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        onNewIntent(intent);

        if (action.equals("android.intent.action.SEND")) {
            if (type.equals("text/plain")) {
                // Obtenemos el contenido del intent
                intentContent = intent.getStringExtra(Intent.EXTRA_TEXT);

                System.out.println("Contenido del intent : " + intentContent);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {

            System.out.println("-- Nombre del paquete: " + getReferrer());

            // Si el intent viene de Youtube
            if (getReferrer()!=null && getReferrer().toString().contains("com.google.android.youtube")) {
                mode = 1;

            // Si el intent viene de Shazam
            } else if (getReferrer()!=null && getReferrer().toString().contains("com.shazam.android")) {
                mode = 2;
            }

        } else if (intentContent.contains("youtu.be")) {
            mode = 1;

        } else if (intentContent.contains("Shazam")) {
            mode = 2;
        }

        switch (mode){
            case 1:
                //
                System.out.println("Vengo de Youtube");
                break;
            case 2:
                //
                System.out.println("Vengo de Shazam");
                break;
            case 0:
                //
                System.out.println("Me ha abierto el usuario");
                setContentView(R.layout.activity_main);

        }


    }

    @Override
    public void onNewIntent(Intent intent){
        type = intent.getType();
        action = intent.getAction();


        System.out.println("--Tipo del intent: " + type);
        System.out.println("--Acción del intent: " + action);




    }
}
