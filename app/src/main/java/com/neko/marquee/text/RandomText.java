package com.neko.marquee.text;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.neko.marquee.text.AutoMarqueeTextView;

import java.util.Random;

public class RandomText extends AutoMarqueeTextView implements Runnable {

    private final Handler handler;
    private final boolean runnable;

    public RandomText(Context context, AttributeSet attrs) {
        super(context, attrs);

        handler = new Handler();

        // Ambil atribut "uwu_runnable" dari XML (default = true)
        runnable = attrs.getAttributeBooleanValue(null, "uwu_runnable", true);

        if (runnable) {
            run();
        } else {
            setRandomText();
        }
    }

    @Override
    public void run() {
        setRandomText();
        // Jalankan ulang setiap 3000 ms (0xbb8 dalam heksadesimal = 3000)
        handler.postDelayed(this, 3000);
    }

    private void setRandomText() {
        Resources res = getResources();
        Context context = getContext();
        String packageName = context.getPackageName();

        // Ambil resource array bernama "uwu_random_text"
        int id = res.getIdentifier("array/uwu_random_text", "array", packageName);
        if (id == 0) {
            return; // Jika tidak ditemukan, keluar
        }

        String[] texts = res.getStringArray(id);
        if (texts.length == 0) {
            return;
        }

        Random random = new Random();
        int index = random.nextInt(texts.length);
        setText(texts[index]);
    }
}
