package helpers;

import java.util.ArrayList;

import android.graphics.*;
import android.view.Display;
import org.apache.http.NameValuePair;

import android.content.Context;
import android.view.View;

public class MyGraphview extends View
{
	
    private Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] value_degree;
    private int[] COLORS={Color.BLUE,Color.GREEN,Color.GRAY,Color.CYAN,Color.RED,Color.BLACK,Color.MAGENTA,Color.YELLOW};

	float width = 550;
	float padding = 50;

    RectF rectf = new RectF (padding, padding, width-padding, width-padding);
    int temp=0;
   
    public MyGraphview(Context context, ArrayList<NameValuePair> values) {
    	super(context);
    }
    
    public MyGraphview(Context context, float[] values) {

        super(context);
        value_degree=new float[values.length];
        for(int i=0;i<values.length;i++)
        {
            value_degree[i]=values[i];
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
		System.out.println(canvas.getWidth()+" "+canvas.getHeight());
        for (int i = 0; i < value_degree.length; i++) {//values2.length; i++) {
            if (i == 0) {
                paint.setColor(COLORS[i]);
                canvas.drawArc(rectf, 0, value_degree[i], true, paint);
            } 
            else
            {
                    temp += (int) value_degree[i - 1];
                    paint.setColor(COLORS[i]);
                    canvas.drawArc(rectf, temp, value_degree[i], true, paint);
            }
        }
    }
  }