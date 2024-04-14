package com.example.zaliczeniedwa;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

   private Context mContex;
   private int Zdj[] = {
           R.drawable.zdj01,R.drawable.zdj02,R.drawable.zdj03,
           R.drawable.zdj04,R.drawable.zdj05,R.drawable.zdj06,
           R.drawable.zdj07,R.drawable.zdj08,R.drawable.zdj09,
           R.drawable.zdj10,R.drawable.zdj11,R.drawable.zdj12,
           R.drawable.zdj13,R.drawable.zdj14,R.drawable.zdj15};

   ImageAdapter(Context c){
       mContex =c;
   }

    @Override
    public int getCount() {
        return Zdj.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0L;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ImageView foto;

        if (view == null) { //potrzeba inicjalizacji

            foto = new ImageView(mContex);
            foto.setLayoutParams(new ViewGroup.LayoutParams(1300,800));
            foto.setScaleType(ImageView.ScaleType.CENTER_CROP);
            foto.setPadding(50,8,8,8);
        }
       else
           foto = (ImageView) view;
       foto.setImageResource(Zdj[i]);

        return foto;
    }
}
