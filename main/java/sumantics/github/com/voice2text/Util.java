package sumantics.github.com.voice2text;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Util{
    static Intent getVoiceIntent(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "बोले");
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,  "kn-IN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,  "hi-IN");
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "hi-IN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        return intent;
    }

    enum Part {HEAD,HEART,HAND,ARM,KNEE,ANKLE}

    public static Locale getLang() {
        return new Locale("hin", "IND");
    }

    static List<Illness> getIllnesses(Part part){
        Log.d("getIllnesses",part.name());
        if(part.equals(part.HEAD))
            return Arrays.asList(new Illness("सरदर्द","क्या आपको सरदर्द है? ","head_headache"),new Illness("दांत","क्या आपके दांतों में दर्द  है?","head_toothache"),new Illness("खांसी","क्या आपको खांसी है?","head_cough"));
        else if(part.equals(part.HEART))
            return Arrays.asList(new Illness("heart heart heart heart heart heart ","heart heart heart heart heart ","red_cross"));
        else if(part.equals(part.ARM))
            return Arrays.asList(new Illness("बांह","बांहों में दर्द है?","arm_broken"));
        else if(part.equals(part.HAND))
            return Arrays.asList(new Illness("कलाई","कलाई में दर्द है?","wrist_broken"));
        else if(part.equals(part.KNEE))
            return Arrays.asList(new Illness("घुटना","क्या आपके घुटनों में दर्द है?","knee_pain"));
        else if(part.equals(part.ANKLE))
            return Arrays.asList(new Illness("पैर","क्या आपके पैर में मोच है?","ankle_broken"));
        else
            return Arrays.asList(new Illness("Headache","head ache","head_headache"));
    }

    static String analyze(Set<String> selectedIllnesses){
        if(selectedIllnesses.contains("head_headache")&&selectedIllnesses.contains("head_cough")){
            //return "Take an asprin now and meet the dentist tomorrow!";
            return "अब एक Asprin ले लो और कल दंत चिकित्सक से मिल ले!";
        }else if(selectedIllnesses.contains("head_headache")){
            return "अब एक Asprin ले लो!";
        }
        else{
            return "Take care!";
        }
    }
}

