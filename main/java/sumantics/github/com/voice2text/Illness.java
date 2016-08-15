package sumantics.github.com.voice2text;

import android.media.Image;
import android.os.Parcelable;

public class Illness{
    String illnessName;
    String illnessDesc;
    String illnessImgName;
    boolean selected;
    public Illness(String illnessName, String illnessDesc, String illnessImgName) {
        this.illnessName = illnessName;
        this.illnessDesc = illnessDesc;
        this.illnessImgName = illnessImgName;
    }
}
