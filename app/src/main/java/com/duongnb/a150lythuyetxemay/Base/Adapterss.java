package com.duongnb.a150lythuyetxemay.Base;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.R;
import com.duongnb.a150lythuyetxemay.SV.TLCauHoi;

import java.util.ArrayList;

public class Adapterss extends BaseAdapter {
    ArrayList<Questions> arrayList;
    Context context;

    public Adapterss(ArrayList<Questions> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewHoler itemViewHoler ;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gv,parent,false);
            itemViewHoler = new ItemViewHoler(convertView);
            convertView.setTag(itemViewHoler);
        }else itemViewHoler  = (ItemViewHoler) convertView.getTag();

        Questions q = arrayList.get(position);


        itemViewHoler.setView(q,position);
        return convertView;
    }


    class ItemViewHoler{
        TextView textView;

        public ItemViewHoler(View convertView) {
            textView = convertView.findViewById(R.id.tv_number);
        }

        public void setView(Questions question, int position) {
            textView.setText(position+1+"");
            if (TLCauHoi.hashMapDapAn.get(position).equals("1")){
                textView.setBackgroundResource(R.drawable.bg_text_false);
            }else       textView.setBackgroundResource(R.drawable.bg_text_true);

        }
    }
}
