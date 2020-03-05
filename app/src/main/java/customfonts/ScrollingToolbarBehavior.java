package customfonts;

import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;


class ScrollingToolbarBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    public ScrollingToolbarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Toolbar child, View dependency) {
        if (dependency instanceof AppBarLayout) {

            int distanceToScroll = child.getHeight();

            int bottomToolbarHeight = child.getHeight();//TODO replace this with bottom toolbar height.

            float ratio = dependency.getY() / (float) bottomToolbarHeight;
            child.setTranslationY(-distanceToScroll * ratio);
        }
        return true;
    }
}
