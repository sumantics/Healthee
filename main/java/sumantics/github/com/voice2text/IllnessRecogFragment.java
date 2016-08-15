package sumantics.github.com.voice2text;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IllnessRecogFragment extends Fragment {
    static String KEY = "part";

    private Util.Part mPart;
    List<Illness> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            mPart = (Util.Part) arguments.get(IllnessRecogFragment.KEY);
            list = Util.getIllnesses(mPart);
        }
        ListView lfragMainView = (ListView)inflater.inflate(R.layout.fragment_illness_recog, container, false);
        IllnessRecogAdapter adapter = new IllnessRecogAdapter(getContext(), list);
        lfragMainView.setAdapter(adapter);
        return lfragMainView;
    }
}
