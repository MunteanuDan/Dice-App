package eu.ase.diceApp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button btn;
    public ImageView zar1, zar2;
    public MediaPlayer mp;
    public int[] zaruri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

  //      Toast.makeText(getApplicationContext(), "OnCreate Method", Toast.LENGTH_SHORT).show();

        btn = findViewById(R.id.btn_Zar);
        zar1 = findViewById(R.id.zar1);
        zar2 = findViewById(R.id.zar2);
        mp = MediaPlayer.create(this, R.raw.dice2);

        zaruri = new int[]{
                R.drawable.zar1,
                R.drawable.zar2,
                R.drawable.zar3,
                R.drawable.zar4,
                R.drawable.zar5,
                R.drawable.zar6
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBtn_onPlay();
                mp.start(); // sa porneasca sunetul
                changeImages();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // dupa ce se termina de rulat sunetul
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        changeBtn_onStop(); // dupa terminarea sunetului, butonul va fi iar enabled
                    }
                });


            }
        });

    }

    public int getRandom(int[] array){
        int random = new Random().nextInt(array.length); // am dat array.length ca interval de unde ne este alesa valoarea
        return array[random];
    }

    public void changeBtn_onPlay(){
        btn.setEnabled(false); // facem butonul dezactivat
        btn.setText(getResources().getString(R.string.btn_clicked));
    }

    public void changeBtn_onStop(){
        btn.setEnabled(true); // facem butonul dezactivat
        btn.setText(getResources().getString(R.string.btn_unclicked));
    }

    public void changeImages(){
        int poza = getRandom(zaruri); // am luat elementul random
        zar1.setImageDrawable(getResources().getDrawable(poza)); // am pus poza pt primul zar
        poza = getRandom(zaruri);
        zar2.setImageDrawable(getResources().getDrawable(poza)); // am pus poza pt al doilea zar

        // pt animatii:

        zar1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zar_stanga));
        zar2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zar_dreapta));

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(getApplicationContext(), "OnStart Method", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        Toast.makeText(getApplicationContext(), "OnPause Method", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        Toast.makeText(getApplicationContext(), "OnStop Method", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        Toast.makeText(getApplicationContext(), "OnDestroy Method", Toast.LENGTH_SHORT).show();
//    }
}