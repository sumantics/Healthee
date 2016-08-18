package sumantics.github.com.voice2text;

import android.media.Image;
import android.os.Parcelable;

public class Illness{
    Util.IllnessNames illnessName;
    String illnessName_local;
    String illnessDesc;
    String illnessImgName;
    boolean selected;
    public Illness(Util.IllnessNames illnessName, String illnessName_local, String illnessDesc, String illnessImgName) {
        this.illnessName = illnessName;
        this.illnessName_local = illnessName_local;
        this.illnessDesc = illnessDesc;
        this.illnessImgName = illnessImgName;
    }
}
