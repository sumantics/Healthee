package sumantics.github.com.voice2text;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AnalysisActivityFragment extends Fragment {
    Button nameButton;
    Button callButton;
    public boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNw = cm.getActiveNetworkInfo();
        return activeNw!=null && activeNw.isConnectedOrConnecting();
        //activeNw.getType() == ConnectivityManager.TYPE_WIFI;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View retView = inflater.inflate(R.layout.fragment_analysis, container, false);

        addAnalysisTextBox(retView);
        Util.speak(Util.getText_pressButton()+Util.getText_getName());
        //getName();
        nameButton = addButtonNameInput(retView);
        callButton = addCallButton(retView);
        return retView;
    }

    private void getName() {
        startActivityForResult(Util.getVoiceIntent(Util.getText_getName()), Util.ANALYSIS_SPEECH_TO_TEXT_RES);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent res_data) {
        super.onActivityResult(requestCode, resultCode, res_data);
        switch (requestCode) {
            case Util.ANALYSIS_SPEECH_TO_TEXT_RES: {
                if (null != res_data) {
                    ArrayList<String> res = res_data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Util.setName(res.get(0));
                    if(isOnline()){
                        restCall();
                    }else {
                        //sendSMS();
                    }
                    Util.speak(Util.getText_TalkToExpert());
                    for (String s : res) {
                        Log.d("onActivityResult", s + " " + resultCode);
                    }
                }
                break;
            }
        }
    }

    private void restCall() {
    }

    private void videoCall(){
        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse(Util.getVoiceCallDetail()));
        startActivity(callIntent);
    }

    private void callTollFree() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        callIntent.setData(Uri.parse("tel:"+Util.getText_TollFreeNumber()));
        startActivity(callIntent);
    }

    private void addAnalysisTextBox(View retView) {
        TextView textView = new TextView(getContext());
        textView.setText(Util.analyze());
        Util.speak(Util.analyze());
        textView.setBackgroundColor(Color.parseColor("#798dd8"));
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setMinLines(5);
        textView.setLineSpacing(5.0f,2.0f);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setElevation(1.0f);
        }
        textView.setPadding(20,20,20,20);
        ((LinearLayout)retView).addView(textView);
    }
    private void constructButton(Button btn){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            btn.setForegroundGravity(Gravity.END);
        }
        //imgButton.setImageResource(R.mipmap.call_green);
        btn.setText(Util.getText_Call());
        btn.setBackgroundColor(Color.GRAY);
        btn.setClickable(false);
    }
    private Button addButtonNameInput(View retView) {
        //ImageButton imgButton = new ImageButton(retView.getContext());
        Button imgButton = new Button(retView.getContext());
        constructButton(imgButton);
        activate(imgButton);
        imgButton.setText(Util.getText_getName());
        imgButton.setClickable(true);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getName();
                deActivate(nameButton);
                activate(callButton);
            }
        });
        imgButton.setVisibility(View.VISIBLE);
        ((LinearLayout)retView).addView(imgButton);
        return imgButton;
    }

    private void activate(Button button) {
        if(button!=null) {
            button.setBackgroundColor(Color.parseColor("#a0fd6a"));
            button.setClickable(true);
        }
    }
    private void deActivate(Button button) {
        button.setBackgroundColor(Color.GRAY);
        button.setClickable(false);
    }


    private Button addCallButton(View retView) {
        //ImageButton imgButton = new ImageButton(retView.getContext());
        Button imgButton = new Button(retView.getContext());
        constructButton(imgButton);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline())
                    videoCall();
                else
                    callTollFree();
            }
        });
        imgButton.setVisibility(View.VISIBLE);
        ((LinearLayout)retView).addView(imgButton);
        return imgButton;
    }

    private void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(Util.getText_SMSSendToNumber(), Util.getText_SMSFromNumber(), Util.getText_SMSText(), null, null);
    }
}
