package com.vcalazas.formasgeomtricas.fragments;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.vcalazas.formasgeomtricas.R;
import com.vcalazas.formasgeomtricas.interfaces.FragmentHosted;
import com.vcalazas.formasgeomtricas.interfaces.OnListener;
import com.vcalazas.formasgeomtricas.models.TouchEventCanva;
import com.vcalazas.formasgeomtricas.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FormsFragment extends Fragment implements FragmentHosted {

    private OnListener onListener;
    private View view;

    public FormsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_forms, container, false);
//        ;
//        FormsFragment.this.view = new Tela(getContext());


        RelativeLayout relativeLayout = (RelativeLayout) rootView.findViewById(R.id.rect);
        relativeLayout.addView(FormsFragment.this.defineTheShape(getArguments()));

        return rootView;
    }

    @Override
    public void setListener(OnListener onListener) {
        FormsFragment.this.onListener = onListener;
    }

    private class Rectangle extends View{
        Paint paint = new Paint();

        public Rectangle(Context context) {
            super(context);
        }
        @Override
        public void onDraw(Canvas canvas) {
            paint.setColor(Color.GREEN);
            Rect rect = new Rect(20, 56, 200, 112);
            canvas.drawRect(rect, paint );
        }
    }

    private View defineTheShape(Bundle bundle){
        try {
            return new Tela(getContext(), bundle.getInt(Constants.NameOfForms.BUNDLE_FORMS, -1));
        } catch (Exception e){
            return null;
        }
    }

    class Tela extends View {

        private List<TouchEventCanva> touchEventCanvas;
        private int mForm;

        public Tela (Context context, int form){
            super (context);
            Tela.this.touchEventCanvas = new ArrayList<TouchEventCanva>();
            Tela.this.mForm = form;
        }

        public void onDraw(Canvas canvas){

            Paint paint = new Paint();
            canvas.drawColor(Color.WHITE);
            paint.setAntiAlias(true);

            paint.setColor(Color.BLUE);

            switch (Tela.this.mForm){
                case Constants.NameOfForms.CIRCLE:
                    for(int i = 0; i < Tela.this.touchEventCanvas.size(); i++){
                        canvas.drawCircle(Tela.this.touchEventCanvas.get(i).x, Tela.this.touchEventCanvas.get(i).y, 50, paint);
                    }
                    break;
                case Constants.NameOfForms.OVAL:
                    for(int i = 0; i < Tela.this.touchEventCanvas.size(); i++){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            canvas.drawOval(Tela.this.touchEventCanvas.get(i).x, Tela.this.touchEventCanvas.get(i).y,
                                    ( Tela.this.touchEventCanvas.get(i).x + 100), ( Tela.this.touchEventCanvas.get(i).y + 175), paint);
                        }
                    }
                    break;
                case Constants.NameOfForms.SQUARE:
                    for(int i = 0; i < Tela.this.touchEventCanvas.size(); i++){
                        canvas.drawRect(Tela.this.touchEventCanvas.get(i).x, Tela.this.touchEventCanvas.get(i).y,
                                (Tela.this.touchEventCanvas.get(i).x + 100), (Tela.this.touchEventCanvas.get(i).y + 100), paint);
                    }
                    break;
                case Constants.NameOfForms.RECTANGULE:
                    for(int i = 0; i < Tela.this.touchEventCanvas.size(); i++){
                        canvas.drawRect(Tela.this.touchEventCanvas.get(i).x, Tela.this.touchEventCanvas.get(i).y,
                                (Tela.this.touchEventCanvas.get(i).x + 100), (Tela.this.touchEventCanvas.get(i).y + 200), paint);
                    }
                    break;

                    default:

                        break;
            }


        }
        public boolean onTouchEvent(MotionEvent evento){
            if (evento.getAction() == MotionEvent.ACTION_DOWN){
                Tela.this.touchEventCanvas.add(new TouchEventCanva(evento.getX(), evento.getY()));
                invalidate();
            }
            return true;
        }



    }

}
