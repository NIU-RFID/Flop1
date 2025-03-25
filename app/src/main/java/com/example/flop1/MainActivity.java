package com.example.flop1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ImageView iv1, iv2, iv3, iv4, iv5, iv6;
    TextView textView;
    int[] card = { R.drawable.front1, R.drawable.front1, R.drawable.front2,
            R.drawable.front2, R.drawable.front3, R.drawable.front3 };
    boolean[] bool = { false, false, false, false, false, false };
    Timer timer = new Timer();
    int flop1, flop2, btncount = 0, time = 0, end = 0;
    ImageView fiv1, fiv2;
    MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 =  findViewById(R.id.imageView1);
        iv2 =  findViewById(R.id.imageView2);
        iv3 =  findViewById(R.id.imageView3);
        iv4 =  findViewById(R.id.imageView4);
        iv5 =  findViewById(R.id.imageView5);
        iv6 =  findViewById(R.id.imageView6);
        textView = findViewById(R.id.textView);

        for (int i = 0; i < 10000; i++) {
            int shuffle = (int) (Math.random() * 6);
            int c = card[shuffle];
            card[shuffle] = card[0];
            card[0] = c;
        }

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }, 0, 200);

        iv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv1, 0);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv2, 1);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv3, 2);
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv4, 3);
            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv5, 4);
            }
        });

        iv6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animation(iv6, 5);
            }
        });

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment()).commit();
//        }
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 1) {
                setTitle("遊戲時間：" + (time++) / 5 + " s ");
                textView.setText("遊戲時間：" + (time++) / 5 + " s ");

                if (btncount == 3) {
                    if (card[flop1] == card[flop2]) {
                        fiv1.setVisibility(View.INVISIBLE);
                        fiv2.setVisibility(View.INVISIBLE);
                        end++;
                        mp = MediaPlayer
                                .create(MainActivity.this, R.raw.o);
                        if (card.length / 2 == end) {
                            timer.cancel();
                            setTitle("遊戲結束!! 記錄：" + time / 5 + " s ");
                            textView.setText("遊戲結束!! 記錄：" + time / 5 + " s ");
                        }
                    } else {
                        fiv1.setEnabled(true);
                        fiv1.setImageResource(R.drawable.back);
                        fiv2.setImageResource(R.drawable.back);
                        bool[flop1] = false;
                        bool[flop2] = false;
                        mp = MediaPlayer
                                .create(MainActivity.this, R.raw.x);
                    }
                    mp.start();
                    btncount = 0;
                    iv1.setEnabled(true);
                    iv2.setEnabled(true);
                    iv3.setEnabled(true);
                    iv4.setEnabled(true);
                    iv5.setEnabled(true);
                    iv6.setEnabled(true);
                } else if (btncount >= 2)
                    btncount++;
            }
        }

    };

    private void animation(final ImageView img, final int ivValue) {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
                R.anim.back);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                // TODO Auto-generated method stub
                if (!bool[ivValue]) {
                    img.setImageResource(card[ivValue]);
                    bool[ivValue] = true;
                }
                img.startAnimation(AnimationUtils.loadAnimation(
                        MainActivity.this, R.anim.front));

                btncount++;
                if (btncount == 1) {
                    fiv1 = img;
                    flop1 = ivValue;
                    img.setEnabled(false);
                }
                else if (btncount == 2) {
                    fiv2 = img;
                    flop2 = ivValue;
                    iv1.setEnabled(false);
                    iv2.setEnabled(false);
                    iv3.setEnabled(false);
                    iv4.setEnabled(false);
                    iv5.setEnabled(false);
                    iv6.setEnabled(false);
                }
            }
        });
        img.startAnimation(animation);
    }

//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container,
//                    false);
//            return rootView;
//        }
//    }


}