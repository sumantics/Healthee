package sumantics.github.com.voice2text;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class IllnessRecogActivity  extends ActionBarActivity {
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
}
