package activityresult.cui.com.library;

import android.content.Intent;

/**
 * Created by cuiyang on 2018/1/5.
 */

public class RxActivityForResultInfo {
    private int requestCode;
    private int resultCode;
    private Intent data;

    public RxActivityForResultInfo(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}
