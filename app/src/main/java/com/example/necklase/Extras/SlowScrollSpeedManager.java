package com.example.necklase.Extras;

import android.view.animation.LinearInterpolator;

import androidx.recyclerview.widget.RecyclerView;

public class SlowScrollSpeedManager extends RecyclerView.OnScrollListener {
    private final int duration;

    public SlowScrollSpeedManager(int duration) {
        this.duration = duration;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dx > 0 || dy > 0) {
            recyclerView.smoothScrollBy(dx, dy, new LinearInterpolator(), duration);
        }
    }
}
