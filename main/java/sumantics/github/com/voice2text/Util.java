package sumantics.github.com.voice2text;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Util{
    static final int ANALYSIS_SPEECH_TO_TEXT_RES = 301;
    static final int ANALYSIS_TTS_CHECK_CODE = 302;
    static final String SPEAKTEXT = "SPEAKTEXT";
    static int max_stage=0;
    static String name;

    public static void setCtx(Context ctx) {
        Util.ctx = ctx;
    }

    static Context ctx;

    private static final HashSet<IllnessNames> selectedIllnesses = new HashSet<>();
    static void addIllness(IllnessNames illness){
        selectedIllnesses.add(illness);
        Log.d("selectedIllnesses",selectedIllnesses.toString());
    }
    static void removeIllness(IllnessNames illness){
        selectedIllnesses.remove(illness);
    }
    static boolean isSelected(IllnessNames illness) {return selectedIllnesses.contains(illness);}
    static int getSelectedIllnessCount() {return selectedIllnesses.size();}
    static Intent getVoiceIntent(){
       return getVoiceIntent("बोले");
    }
    static Intent getVoiceIntent(String prompt){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, prompt);
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,  "kn-IN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,  "hi-IN");
        intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "hi-IN");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        return intent;
    }

    public static String getText_TollFreeNumber(){
        return "1234567890";
    }

    public static String getText_SMSSendToNumber() {
        return "1234567890";
    }

    public static String getText_SMSFromNumber() {
        return "1234567890";
    }

    public static String getText_SMSText() { return name+""+selectedIllnesses.toString(); }

    public static String getVoiceCallDetail() {
        return "skype:Skype?call&video=true";//hardcoding the userID for testing
    }

    public static String getText_Next() {
        return getText_select_pressButton();
    }

    public static String getText_Call() { return "विशेषज्ञ से बात करें"; }

    public static String getText_TalkToExpert() { return "अगर आप हमारे विशेषज्ञ से बात करना चाहते हैं, तो बैंगनी बटन दबाएं";}

    public static String getText_getName() { return "अपना नाम और गांव का नाम बताएं"; }

    public static void setName(String s) {
        name = s;
    }

    public static String getText_greeting() { return "नमस्कार!"; }

    public static String getText_pressButton() { return "बैंगनी  बटन दबाकर ";} //हरा
    public static String getText_select() { if(max_stage==0) {return "आपको कहाँ परेशानी है? गोल बटन दबाकर उपयुक्त विकल्प चुनें ";}else return "";}
    public static String getVoiceText_select_pressButton() {  if(max_stage<1){max_stage++; return "उपयुक्त विकल्प चुनें और नीचे बने  बैंगनी बटन दबाएँ।";} else return "";}
    public static String getText_select_pressButton() { return "उपयुक्त विकल्प चुनें और यह बटन दबाएँ।"; }

    enum Part {HEAD,HEART,HAND,ARM,KNEE,ANKLE}
    enum IllnessNames {headache,toothache,cough,brokenArm, brokenWrist, kneePain,brokenAnkle,heartTrouble}
    public static Locale getLang() {
        return new Locale("hin", "IND");
    }

    static List<Illness> getIllnesses(Part part){
        Log.d("getIllnesses",part.name());
        if(part.equals(part.HEAD))
            return Arrays.asList(new Illness(IllnessNames.headache,"सरदर्द","क्या आपको सरदर्द है? ","head_headache"),new Illness(IllnessNames.toothache,"दांत","क्या आपके दांतों में दर्द  है?","head_toothache"),new Illness(IllnessNames.cough,"खांसी","क्या आपको खांसी है?","head_cough"),new Illness(IllnessNames.headache,"सरदर्द","क्या आपको सरदर्द है? ","head_headache"));
        else if(part.equals(part.HEART))
            return Arrays.asList(new Illness(IllnessNames.heartTrouble,"दिल ","क्या आपको छाती में दर्द है?","heart"));
        else if(part.equals(part.ARM))
            return Arrays.asList(new Illness(IllnessNames.brokenArm,"बांह","बांहों में दर्द है?","arm_broken"));
        else if(part.equals(part.HAND))
            return Arrays.asList(new Illness(IllnessNames.brokenWrist,"कलाई","कलाई में दर्द है?","wrist_broken"));
        else if(part.equals(part.KNEE))
            return Arrays.asList(new Illness(IllnessNames.kneePain,"घुटना","क्या आपके घुटनों में दर्द है?","knee_pain"));
        else if(part.equals(part.ANKLE))
            return Arrays.asList(new Illness(IllnessNames.brokenAnkle,"पैर","क्या आपके पैर में मोच है?","ankle_broken"));
        else
            return Arrays.asList(new Illness(IllnessNames.headache,"Headache","head ache","head_headache"));
    }

    static String analyze(){
        String retStr = "";
        if(selectedIllnesses.contains(IllnessNames.heartTrouble)){
            retStr+= " निकटतम चिकित्सालय जाएँ और एक एंजियोग्राम करा लें, हम कुछ ही  समय में आपको  एक चिकित्सा विशेषज्ञ से जोड़ेंगे  !"+"\n";
        }
        if(selectedIllnesses.contains(IllnessNames.headache)&&selectedIllnesses.contains(IllnessNames.toothache)){
            //return "Take an asprin now and meet the dentist tomorrow!";
            retStr+= " अब एक Asprin ले लें और कल दंत चिकित्सक से मिल ले!"+"\n";
        }else if(selectedIllnesses.contains(IllnessNames.headache)){
            retStr+= " अब एक Asprin ले लें!"+"\n";
        }
        else{
            retStr+= " विशेषज्ञ से बात करें!"+"\n";
        }
        return retStr;
    }

    static void speak(String s) {
        Intent speechIntent = new Intent(ctx, TextToSpeechService.class);
        speechIntent.putExtra(Util.SPEAKTEXT, s);
        ctx.startService(speechIntent);
    }
}

