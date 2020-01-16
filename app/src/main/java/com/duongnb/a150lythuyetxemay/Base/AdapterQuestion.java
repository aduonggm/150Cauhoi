package com.duongnb.a150lythuyetxemay.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.duongnb.a150lythuyetxemay.Model.Question;
import com.duongnb.a150lythuyetxemay.R;

import java.util.ArrayList;

public class AdapterQuestion  extends BaseAdapter {

    ArrayList<Question> arrayList;
    Context context;

    public AdapterQuestion(ArrayList<Question> arrayList, Context context) {
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
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.items_question,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else viewHolder = (ViewHolder) convertView.getTag();

        Question question = arrayList.get(position);
        viewHolder.setView(question,position);
        return convertView;
    }

    class ViewHolder{
        TextView tvQuestion , tvDapAn1, tvDapAn2 , tvDapAn3 , tvDapAn4;
        CheckBox cb1 , cb2 , cb3, cb4;
        public ViewHolder(View view) {
            tvQuestion = view.findViewById(R.id.tv_frag_question);
            tvDapAn1 = view.findViewById(R.id.tv_frag_cauhoi1);
            tvDapAn2 = view.findViewById(R.id.tv_frag_cauhoi2);
            tvDapAn3 = view.findViewById(R.id.tv_frag_cauhoi3);
            tvDapAn4 = view.findViewById(R.id.tv_frag_cauhoi4);

            cb1 = view.findViewById(R.id.cb_frag_1);
            cb2 = view.findViewById(R.id.cb_frag_2);
            cb3 = view.findViewById(R.id.cb_frag_3);
            cb4 = view.findViewById(R.id.cb_frag_4);

        }

        public void setView(Question question, int position) {
            tvQuestion.setText("Câu"+position +" : " +question.getCauHoi() );

            tvDapAn1.setText(question.getCauTL1()+"");
            tvDapAn2.setText(question.getCauTL2()+"");
            tvDapAn3.setText(question.getCauTL3()+"");
            tvDapAn4.setText(question.getCauTL4()+"");

            String str = question.getDapAn();
            String[] array  = str.split("\\.");


            for (int i = 0 ; i< array.length ; i++){
                switch (Integer.parseInt(array[i])){
                    case 1:
                        cb1.setChecked(true);
                        break;
                    case 2:
                        cb2.setChecked(true);
                        break;
                    case 3:
                        cb3.setChecked(true);
                        break;
                    case 4:
                        cb4.setChecked(true);
                        break;
                }
            }
        }
    }
}
