package project.kohler.com.kholer_project.CC;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Lorenzo on 13/02/2018.
 */

public class C_F {
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static Spanned fromHtml(String s) {
        if(s != null) {
            if(!s.isEmpty()) {
                if (Build.VERSION.SDK_INT >= 24) {
                    return Html.fromHtml(s, Html.FROM_HTML_MODE_LEGACY);
                } else {
                    return Html.fromHtml(s);
                }
            }
        }

        return new SpannableStringBuilder("");
    }

    public static Bitmap viewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
