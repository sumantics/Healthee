package sumantics.github.com.voice2text;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;

class IllnessRecogAdapter extends ArrayAdapter<Illness>{
    private final HashSet<String> selectedIllnesses = new HashSet<>();
    TextToSpeech tts = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {  }
    });

    public IllnessRecogAdapter(Context context, List<Illness> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_illness, parent, false);
        }
        final Illness illness = getItem(position);
        //TextView mSelected = (TextView) lfragMainView.findViewById(R.id.illness_selected);
        ImageView mIllnessImg = (ImageView)view.findViewById(R.id.illness_img);
        mIllnessImg.setImageResource(getContext().getResources().getIdentifier(illness.illnessImgName, "mipmap", getContext().getPackageName()));
        TextView mIllnessName = (TextView) view.findViewById(R.id.illness_name);
        mIllnessName.setText(illness.illnessName);
        TextView mIllnessDesc = (TextView) view.findViewById(R.id.illness_desc);
        mIllnessDesc.setText(illness.illnessDesc);
        final ImageButton mSelectedImg = (ImageButton)view.findViewById(R.id.green_tick);
        mSelectedImg.setClickable(true);
        mSelectedImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIllnesses.add(illness.illnessImgName);
                mSelectedImg.setImageResource(getContext().getResources().getIdentifier("green_tick_black", "mipmap", getContext().getPackageName()));
                Log.d("selectedIllnesses",selectedIllnesses.toString());
                String msg = Util.analyze(selectedIllnesses);
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
                speak(msg);
            }
        });
        final ImageButton mUnselectImg = (ImageButton)view.findViewById(R.id.red_cross);
        mUnselectImg.setClickable(true);
        mUnselectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIllnesses.remove(illness.illnessImgName);
                mSelectedImg.setImageResource(getContext().getResources().getIdentifier("green_tick", "mipmap", getContext().getPackageName()));
                Log.d("selectedIllnesses",selectedIllnesses.toString());
            }
        });
        return view;
    }

    void speak(String s) {
        tts.setLanguage(Util.getLang());
        tts.speak(s, TextToSpeech.QUEUE_ADD, null);
    }

}
