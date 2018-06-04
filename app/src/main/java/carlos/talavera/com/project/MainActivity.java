package carlos.talavera.com.project;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class
MainActivity extends AppCompatActivity {

    private String type = "";
    private String action = "";
    private String intentContent ="";
    private WebView webView;
    //private String packageName = "";
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
                GetSong(intentContent);
                break;
            case 2:
                YouTubeSearch(intentContent);
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

    /**
     *
     * Método que realiza una búsqueda en YouTube del contenido que le pasa Shazam
     * @param search el contenido del texto del intent
     */
    public void YouTubeSearch(String search) {

        // Extraemos el texto con el título y el autor
        search = search.substring("He usado Shazam para descubrir ".length(),search.indexOf("https://"));
        System.out.println("Buscando " + search);

        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.setPackage("com.google.android.youtube");
        //la cadena a buscar
        intent.putExtra("query", search);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try
        {
            startActivity(intent);
        }
        catch (ActivityNotFoundException e)
        {
            //la app no está instalada, mostrar por ejemplo un AlertDialog
        }
    }

    /**
     * Método que llama a la api de Youtube y muestra los resultados
     * @param urlYT enlace de youtube
     */
    public void GetSong (String urlYT) {

        // Cargamos la vista
        setContentView(R.layout.activity_main);
        //Asignamos los elementos de la vista
        webView = (WebView)findViewById(R.id.webview);

        urlYT = urlYT.substring("https://youtu.be/".length());
        System.out.println("Vamos a buscar la canción con ID: " + urlYT);

        // Configuramos el webview
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("https://youtubemp3api.com/@api/button/mp3/" + urlYT);

    }
}
