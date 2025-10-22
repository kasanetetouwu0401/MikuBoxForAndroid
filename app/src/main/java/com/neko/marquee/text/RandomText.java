package com.neko.marquee.text;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.TextUtils;

import java.util.Random;

public class RandomText extends AppCompatTextView implements Runnable {

    private final Handler handler;
    private final boolean runnable;

    public RandomText(Context context, AttributeSet attrs) {
        super(context, attrs);

        handler = new Handler();
        runnable = attrs.getAttributeBooleanValue(null, "uwu_runnable", true);

        // Aktifkan marquee (teks berjalan)
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1); // -1 = infinite
        setHorizontallyScrolling(true);
        setSelected(true); // Wajib agar marquee bisa berjalan

        if (runnable) {
            run();
        } else {
            setRandomText();
        }
    }

    @Override
    public void run() {
        setRandomText();
        handler.postDelayed(this, 3000); // ulang tiap 3 detik
    }

    private void setRandomText() {
        Resources res = getResources();
        Context context = getContext();
        String packageName = context.getPackageName();

        // Ambil resource array "uwu_random_text"
        int id = res.getIdentifier("array/uwu_random_text", "array", packageName);
        if (id == 0) {
            return;
        }

        String[] texts = res.getStringArray(id);
        if (texts.length == 0) {
            return;
        }

        Random random = new Random();
        int index = random.nextInt(texts.length);
        setText(texts[index]);

        // Pastikan tetap marquee setelah mengganti teks
        setSelected(true);
    }
}
