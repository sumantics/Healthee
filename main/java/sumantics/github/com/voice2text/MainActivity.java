package sumantics.github.com.voice2text;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RadioButton head;
    RadioButton heart;
    RadioButton arm;
    RadioButton hand;
    RadioButton knee;
    RadioButton ankle;

    void initDisplay(){
        head = (RadioButton)findViewById(R.id.head);
        //head.setChecked(false);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplication().getApplicationContext(), IllnessRecogActivity.class);
                    intent.putExtra(IllnessRecogFragment.KEY, Util.Part.HEAD);
                    startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    Log.e("Main",a.getMessage(),a);
                    Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        heart = (RadioButton)findViewById(R.id.heart);
        //heart.setChecked(false);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplication().getApplicationContext(), IllnessRecogActivity.class);
                    intent.putExtra(IllnessRecogFragment.KEY, Util.Part.HEART);
                    startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    Log.e("Main",a.getMessage(),a);
                    Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arm = (RadioButton)findViewById(R.id.leftForearm);
        //arm.setChecked(false);
        arm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplication().getApplicationContext(), IllnessRecogActivity.class);
                    intent.putExtra(IllnessRecogFragment.KEY, Util.Part.ARM);
                    startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    Log.e("Main",a.getMessage(),a);
                    Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        hand = (RadioButton)findViewById(R.id.rightHand);
        //hand.setChecked(false);
        hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplication().getApplicationContext(), IllnessRecogActivity.class);
                    intent.putExtra(IllnessRecogFragment.KEY, Util.Part.HAND);
                    startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    Log.e("Main",a.getMessage(),a);
                    Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        knee = (RadioButton)findViewById(R.id.knee);
        //knee.setChecked(false);
        knee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplication().getApplicationContext(), IllnessRecogActivity.class);
                    intent.putExtra(IllnessRecogFragment.KEY, Util.Part.KNEE);
                    startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    Log.e("Main",a.getMessage(),a);
                    Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ankle = (RadioButton)findViewById(R.id.ankle);
        //ankle.setChecked(false);
        ankle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplication().getApplicationContext(), IllnessRecogActivity.class);
                    intent.putExtra(IllnessRecogFragment.KEY, Util.Part.ANKLE);
                    startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    Log.e("Main",a.getMessage(),a);
                    Toast.makeText(getApplicationContext(), "NOT supported", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDisplay();
        initTTS();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDisplay();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Util.ANALYSIS_SPEECH_TO_TEXT_RES: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Log.d("onActivityResult",result.get(0));
                }
                break;
            }
        }
    }

    private void initTTS() {
        Util.setCtx(getApplicationContext());
        Util.speak(Util.getText_greeting()+Util.getText_select());
    }
}
