package sumantics.github.com.voice2text;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AnalysisActivityFragment extends Fragment {

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
        if(isOnline()){
            addVideoCallButton(retView);
            restCall();
        }else {
            addVoiceCallButton(retView);
            sendSMS();
        }
        return retView;
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
        callIntent.setData(Uri.parse("tel:"+Util.getTollFreeNumber()));
        startActivity(callIntent);
    }

    private void addAnalysisTextBox(View retView) {
        String text = Util.analyze();
        TextView textView = new TextView(getContext());
        textView.setText(text);
        textView.setBackgroundColor(Color.parseColor("#798dd8"));
        textView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setMinLines(5);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setElevation(1.0f);
        }
        textView.setPadding(20,20,20,20);
        //ArrayList<View> viewList =  new ArrayList<>();
        //viewList.add(textView);
        //retView.addTouchables(viewList);
        ((RelativeLayout)retView).addView(textView);
    }

    private void addVideoCallButton(View retView) {
        ImageButton imgButton = new ImageButton(retView.getContext());
        imgButton.setImageResource(R.mipmap.ic_launcher);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoCall();
            }
        });
        imgButton.setVisibility(View.VISIBLE);
        ((RelativeLayout)retView).addView(imgButton);
    }
    private void addVoiceCallButton(View retView) {
        ImageButton imgButton = new ImageButton(retView.getContext());
        imgButton.setImageResource(R.mipmap.ic_launcher);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callTollFree();
            }
        });
        imgButton.setVisibility(View.VISIBLE);
        ((RelativeLayout)retView).addView(imgButton);
    }

    private void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(Util.getSMSSendToNumber(), Util.getSMSFromNumber(), Util.getSMSText(), null, null);
    }

}
