package archanjit.rass.rockstarsloader;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Satty on 11/10/2015.
 */
public class RockStarsDialogClass extends Dialog  {

    private static Context thiscontext;

    private static RockStarsDialogClass dilogClass;
    private View view;
    private TextView textView;
  private LoadingView loadingView;
    private LinearLayout linearLayout;
    private RelativeLayout topLayout;
    private boolean cancelOnOutsidetouch;
    private boolean cancelOnTouch;

    private View.OnTouchListener touchListener;

    public RockStarsDialogClass(Context context) {
        super(context);
        createDilog(context);
    }

    public RockStarsDialogClass(Context context, int themeResId) {
        super(context, themeResId);
        createDilog(context);

    }
    public static RockStarsDialogClass newInstance(Context mcontext){
        if (dilogClass==null || !thiscontext.equals(mcontext)){
            synchronized (RockStarsDialogClass.class){
                if (dilogClass==null||!thiscontext.equals(mcontext)){
                    dilogClass=new RockStarsDialogClass(mcontext, R.style.dialogNew);
                }
            }
        }

        thiscontext=mcontext;
        return dilogClass;
    }

    private void createDilog(final Context context){
        view=View.inflate(context, R.layout.loading_dialog,null);
        topLayout=(RelativeLayout)view.findViewById(R.id.toplevel_layout);
        linearLayout=(LinearLayout)view.findViewById(R.id.dailog_back_layout);
        textView=(TextView)view.findViewById(R.id.progess_message);
        loadingView=(LoadingView)view.findViewById(R.id.loadingView);

           if (cancelOnOutsidetouch){
            Log.i("CancelFlag:=","True");
        }else {
            Log.i("CancelFlag:=","False");
        }
        if (cancelOnTouch){
            Log.i("CancelFlag:=","True");
        }else {
            Log.i("CancelFlag:=","False");
        }
        setContentView(view);
        }








    private void disbleTouch(ViewGroup viewGroup){
    int count =viewGroup.getChildCount();
    Log.i("ChildCount", String.valueOf(count));
    for (int i=0;i<count;i++){
        View v=viewGroup.getChildAt(i);
        if (v instanceof ViewGroup){
            disbleTouch((ViewGroup)v);
        }else {
            v.setOnTouchListener(null);
            v.setOnClickListener(null);
        }
    }
}
    public RockStarsDialogClass cancelableOnTouch(boolean flag) {
        this.cancelOnTouch=flag;
       if (flag==true){
           Log.i("Canclebale","True");
           this.topLayout.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View v, MotionEvent event) {
                   dilogClass.dismiss();
                   return false;
               }
           });
       }else {
           Log.i("Canclebale","True");
       }
        return this;
    }
    public RockStarsDialogClass cancellableOnOutSideTouch (boolean flag) {
        this.cancelOnOutsidetouch=flag;
        if (flag==true){
            Log.i("CanclebaleOutSide","True");
            disbleTouch(topLayout);
            this.topLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    dilogClass.dismiss();
                    return false;
                }
            });
        }else {
                 Log.i("CanclebaleOutSide","False");
        }
        return this;
    }

    public RockStarsDialogClass setMessage(CharSequence massage){
        textView.setText(massage);
        return this;
    }
    public RockStarsDialogClass setAnimType(int resourceId){
        loadingView.setMovieResource(resourceId);
        return this;

    }

    public RockStarsDialogClass setDrawableBackground(int drawableResource){
        linearLayout.setBackgroundResource(drawableResource);
               return this;
    }


    @Override
    public void show() {

        super.show();
    }

    @Override
    public void hide() {

        super.hide();
    }

    @Override
    public void dismiss() {

        super.dismiss();
    }



    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    public void setOnCancelListener(OnCancelListener listener) {
        super.setOnCancelListener(listener);
    }

    @Override
    public void setCancelable(boolean flag) {
        super.setCancelable(flag);
    }


}
