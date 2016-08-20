package sumantics.github.com.voice2text;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

public class AnalysisActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Util.speak(Util.getText_pressButton()+Util.getText_getName());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        if (savedInstanceState == null) {
            Fragment fragment = new AnalysisActivityFragment();
            //fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.analysis_container, fragment, "analysis_tag").commit();
        }
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
