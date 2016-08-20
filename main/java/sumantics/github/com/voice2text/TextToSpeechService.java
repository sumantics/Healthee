package sumantics.github.com.voice2text;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;

public class TextToSpeechService extends Service implements TextToSpeech.OnInitListener{
    private TextToSpeech tts;
    private boolean ready;
    private Handler handler;

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(getApplicationContext(), this);
        handler = new Handler();
        ready = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacksAndMessages(null);
        speak(intent.getStringExtra(Util.SPEAKTEXT));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopSelf();
            }
        }, 15*1000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
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
        }
    }

    private void speak(String s) {
        ready = false;
        if (tts != null) {
            //tts.speak("s", TextToSpeech.QUEUE_FLUSH, null);
            tts.speak(s, TextToSpeech.QUEUE_ADD, null);
        }
        ready = true;
    }
}
