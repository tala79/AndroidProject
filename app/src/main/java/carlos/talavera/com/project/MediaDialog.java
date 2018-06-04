package carlos.talavera.com.project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import static carlos.talavera.com.project.MainActivity.intentContent;
import static carlos.talavera.com.project.MainActivity.webView;

public class MediaDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Elige video o audio");
        builder.setPositiveButton("Descarga video", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                intentContent = intentContent.substring("https://youtu.be/".length());

                System.out.println("Vamos a buscar la canción con ID: " + intentContent);


                webView.loadUrl("https://youtubemp3api.com/es/@api/button/videos/"+intentContent);



            }
        });
        builder.setNegativeButton("Descarga audio", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                intentContent = intentContent.substring("https://youtu.be/".length());

                System.out.println("Vamos a buscar la canción con ID: " + intentContent);

                webView.loadUrl("https://youtubemp3api.com/es/@api/button/mp3/" + intentContent);

            }
        });
        // Create the AlertDialog object and return it

        return builder.create();
    }



}
