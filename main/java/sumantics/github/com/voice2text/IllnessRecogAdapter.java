package sumantics.github.com.voice2text;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class IllnessRecogAdapter extends ArrayAdapter<Illness>{
    private View mButton;
    private View mParent;

    public IllnessRecogAdapter(Context context, List<Illness> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_illness, parent, false);
        }
        mParent = parent;
        addButton();
        final Illness illness = getItem(position);
        //TextView mSelected = (TextView) lfragMainView.findViewById(R.id.illness_selected);
        ImageView mIllnessImg = (ImageView)view.findViewById(R.id.illness_img);
        mIllnessImg.setImageResource(getContext().getResources().getIdentifier(illness.illnessImgName, "mipmap", getContext().getPackageName()));
        TextView mIllnessName = (TextView) view.findViewById(R.id.illness_name);
        mIllnessName.setText(illness.illnessName_local);
        TextView mIllnessDesc = (TextView) view.findViewById(R.id.illness_desc);
        mIllnessDesc.setText(illness.illnessDesc);
        final ImageButton mSelectedImg = (ImageButton)view.findViewById(R.id.green_tick);
        mSelectedImg.setClickable(true);
        View.OnClickListener selectIllnessListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.addIllness(illness.illnessName);
                mSelectedImg.setImageResource(getContext().getResources().getIdentifier("green_tick_black", "mipmap", getContext().getPackageName()));
                //speak(Util.analyze());
                //ask("hello");//best to move to a new activity for speak/ask
                addButton();
            }
        };
        mSelectedImg.setOnClickListener(selectIllnessListener);
        mIllnessImg.setOnClickListener(selectIllnessListener);
        if(Util.isSelected(illness.illnessName)){
            mSelectedImg.performClick();
        }
        final ImageButton mUnselectImg = (ImageButton)view.findViewById(R.id.red_cross);
        mUnselectImg.setClickable(true);
        mUnselectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.removeIllness(illness.illnessName);
                mSelectedImg.setImageResource(getContext().getResources().getIdentifier("green_tick", "mipmap", getContext().getPackageName()));
                addButton();
            }
        });
        return view;
    }

    private void addButton() {
        if(mButton==null)
            mButton = (Button)mParent.getRootView().findViewById(R.id.analysis_enable);//this is in the activity
        if(Util.getSelectedIllnessCount()>0){
            ((Button)mButton).setText(Util.getText_Next());
            mButton.setVisibility(View.VISIBLE);
        }else{
            Animation animFadeIn = AnimationUtils.loadAnimation(getContext().getApplicationContext(), R.anim.fade_in);
            mButton.setVisibility(View.GONE);
        }
    }
}
