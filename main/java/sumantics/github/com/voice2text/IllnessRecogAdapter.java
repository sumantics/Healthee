package sumantics.github.com.voice2text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class IllnessRecogAdapter extends ArrayAdapter<Illness>{
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
        ImageButton mSelectedImg = (ImageButton)view.findViewById(R.id.green_tick);
        mSelectedImg.setClickable(true);
        final ImageButton mUnselectImg = (ImageButton)view.findViewById(R.id.red_cross);
        mUnselectImg.setClickable(true);
        return view;
    }
}
