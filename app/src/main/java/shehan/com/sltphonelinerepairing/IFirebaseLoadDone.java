package shehan.com.sltphonelinerepairing;

import android.graphics.Movie;

import java.util.List;

public interface IFirebaseLoadDone {
    void onActivityResult(int req);

    void onFirebaseLoadSuccess(List<Cabinet>cabinetList);
    void onFirebaseLoadFailed(String message);
}
