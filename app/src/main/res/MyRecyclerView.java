import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.jar.Attributes;

public class MyRecyclerView extends RecyclerView {
    private static final int FLING_MAX_VELOCITY = 8000;

    public MyRecyclerView(Context c){
        super(c);
    }

    public MyRecyclerView(Context c, AttributeSet attr){
        super(c, attr);
    }

    public MyRecyclerView(Context c, AttributeSet attr, int defStyle){
        super(c, attr, defStyle);
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX = adjustVelocity(velocityX);
        velocityY = adjustVelocity(velocityY);
        return super.fling(velocityX, velocityY);

    }
    private int adjustVelocity(int velocity){
        if(velocity > 0)
            return Math.min(velocity, FLING_MAX_VELOCITY);
        else return  Math.max(velocity, FLING_MAX_VELOCITY);
    }
}
