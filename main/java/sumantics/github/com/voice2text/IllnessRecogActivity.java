package sumantics.github.com.voice2text;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class IllnessRecogActivity  extends ActionBarActivity {
    static final int SPEECH_TO_TEXT_RES = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_recog);

        if (savedInstanceState == null) {
            Fragment fragment = new IllnessRecogFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.illness_recog_container, fragment, "tag").commit();
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public void gotoAnalysisActivity(View v){
        try {
            Intent intent = new Intent(getApplication().getApplicationContext(), AnalysisActivity.class);
            startActivity(intent);
        } catch (ActivityNotFoundException a) {
            Log.e("IllnessRecogActivity",a.getMessage(),a);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEECH_TO_TEXT_RES: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> extraRes = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.d("onActivityResult","Number of results: "+extraRes.size());
                    for(String s:extraRes) {
                        Log.d("onActivityResult", s + " ");
                        //tts_illnessRecogAdapter.setLanguage(Util.getLang());
                        //tts_illnessRecogAdapter.speak(s, TextToSpeech.QUEUE_ADD, null);
                    }
                }
                break;
            }
        }
    }

    void ask(String q){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, SPEECH_TO_TEXT_RES);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
        }
    }

}
