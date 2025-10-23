package com.neko.marquee.text;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.neko.marquee.text.AutoMarqueeTextView;
import android.widget.FrameLayout;

import java.util.Random;

public class RandomText extends AppCompatTextView implements Runnable {

    private final AutoMarqueeTextView marqueeTextView;
    private final Handler handler;
    private final boolean runnable;

    public RandomText(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create an instance of AutoMarqueeTextView (composition)
        marqueeTextView = new AutoMarqueeTextView(context, attrs);
        addView(marqueeTextView, new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));

        handler = new Handler();

        // Get "uwu_runnable" attribute from XML (default = true)
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
        // Rerun every 3000 ms (0xbb8 in hexadecimal = 3000)
        handler.postDelayed(this, 3000);
    }

    private void setRandomText() {
        Resources res = getResources();
        Context context = getContext();
        String packageName = context.getPackageName();

        // Get an array resource named "uwu_random_text"
        int id = res.getIdentifier("array/uwu_random_text", "array", packageName);
        if (id == 0) {
            return; // If not found, exit
        }

        String[] texts = res.getStringArray(id);
        if (texts.length == 0) {
            return;
        }

        Random random = new Random();
        int index = random.nextInt(texts.length);
        marqueeTextView.setText(texts[index]);
    }

    // Additional methods to be able to use it like a normal TextView
    public void setText(CharSequence text) {
        marqueeTextView.setText(text);
    }

    public CharSequence getText() {
        return marqueeTextView.getText();
    }
}
