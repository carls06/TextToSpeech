package com.ejemploudb.speechtotext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //creacion de las variables
    private TextToSpeech Tts;
    private EditText edt1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instancia de las variables
        edt1=findViewById(R.id.EditTxt1);
        btn1=findViewById(R.id.Play);

        //lenguaje y locacion para el tipo de voz del reproductor
        Locale spanish = new Locale("es","ES");

        //instancia del TTS que requiere el contexto y el listener
        Tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                //verifica que el estado del servicio tts sea diferente de error
                //si es diferente setea el lenguaje al que se reproducira
                if(status != TextToSpeech.ERROR) {
                    Tts.setLanguage(spanish);
                }
            }
        });


    }
    //metodo que se ejecuta al precionar el boton de reproducir
    public void Play(View view) {

        //lee lo que se encuentra dentro del EditText
            String toSpeak = edt1.getText().toString();
            //muestra una notificacion con el mensaje Reproduciendo
            Toast.makeText(getApplicationContext(), "Reproduciendo",Toast.LENGTH_SHORT).show();
            //ocupa el servicio , sintetiza y reproduce el texto
            Tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

    }

    //metodo que se ejecuta al cerrar la aplicacion, o poner en segundo plano
    public void onPause(){
        //verifica si el tts esta en uso y si es asi detiene y cierra el servicio
        if(Tts != null){
            Tts.stop();
            Tts.shutdown();
        }
        super.onPause();
    }


}