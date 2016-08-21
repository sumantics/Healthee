package sumantics.github.com.voice2text;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.util.Log;

public class TextToSpeechService extends Service implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private boolean ready;
    private Handler handler;
    private String text="";
    private static boolean isWelcomeMsg = true;

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(getApplicationContext(), this);
        handler = new Handler();
        ready = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //handler.removeCallbacksAndMessages(null);
        //speak(intent.getStringExtra(Util.SPEAKTEXT));
        if(!text.contains(intent.getStringExtra(Util.SPEAKTEXT)))
            text += intent.getStringExtra(Util.SPEAKTEXT);
        handler.post(new Runnable() {
            @Override
            public void run() {
                speak();
            }
        });
        Log.d("sumanth","onStartCommand");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        }, 300*1000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        Log.d("onDestroy","tts destoryed");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Util.getLang());
            handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        speak();
                                    }
                                },3*1000
            );
            isWelcomeMsg=false;
        }
    }

    private void speak() {
        speak(text);
        if(!isWelcomeMsg)
            text = "";
    }

    private void speak(String s) {
        ready = false;
        if (tts != null) {
            if(isWelcomeMsg)
                tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
            tts.speak(s, TextToSpeech.QUEUE_ADD, null);
        }
        ready = true;
    }
}
