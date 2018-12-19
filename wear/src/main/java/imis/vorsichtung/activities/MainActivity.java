/**
 * Package & Import
 */
package imis.vorsichtung.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import imis.vorsichtung.R;
import imis.vorsichtung.controller.TriageController;
import imis.vorsichtung.utilities.Constants;
import imis.vorsichtung.utilities.xml.XMLReader;

/**
 * Main class
 */
public class MainActivity extends WearableActivity {

    private TriageController triageController;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestStoragePermission();
        setAmbientEnabled();


        triageController = new TriageController(this);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == Constants.STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "permission not granted why", Toast.LENGTH_SHORT).show();
                finish();
                System.exit(0);
            }
        }
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constants.STORAGE_PERMISSION_CODE);
        }
    }

    /**
     * Auto-generated stuff for low power ambient mode and update display
     */
    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    /**
     * When uncommented there is a bug that will crash the app when entering power saving mode
     * --> this way it only turns black & white when entering power saving mode - don't ask me why -_-
     */
    private void updateDisplay() {
        if (isAmbient()) {
            //mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            //mTextView.setTextColor(getResources().getColor(android.R.color.white));
            //mClockView.setVisibility(View.VISIBLE);
            //mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            //mContainerView.setBackground(null);
            //mTextView.setTextColor(getResources().getColor(android.R.color.black));
            //mClockView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Main Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://imis.vorsichtung/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW,
                "Main Page",
                Uri.parse("http://host/path"),
                Uri.parse("android-app://imis.vorsichtung/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * Methods to send data via WLAN to a server
     * TODO: IP und Port nicht fest konfigurieren
     * TODO: Alle Infos aufnehmen
     * TODO: Warten und späteres Schicken, falls kein Netz vorhanden. Benutzer dann informieren
     * TODO: Funktion für manuelles Übertragen (USB?)
     */
    public void sendData(int classification)  {
        Log.d("test1", "anfang");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Log.d("test1", "vor");
            Socket socket = new Socket("192.168.1.104", 1234);
            Log.d("test1", "1");
            PrintWriter out = new PrintWriter(socket.getOutputStream(),
                    true);
            Log.d("test1", "2");
            out.println(classification);
            Log.d("test1", "3");
        } catch (IOException i) {
            Log.d("IOException", ""+i);
        }

    }
}
