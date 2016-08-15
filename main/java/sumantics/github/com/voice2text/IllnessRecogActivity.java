package sumantics.github.com.voice2text;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

public class IllnessRecogActivity  extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illness_recog);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            Fragment fragment = new IllnessRecogFragment();
            fragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.illness_recog_container, fragment, "tag").commit();
        }
    }
}
