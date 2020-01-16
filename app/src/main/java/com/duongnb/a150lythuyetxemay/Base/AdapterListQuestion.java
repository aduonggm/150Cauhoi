package com.duongnb.a150lythuyetxemay.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongnb.a150lythuyetxemay.Model.Question;
import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.R;

import java.util.ArrayList;

public class AdapterListQuestion extends BaseAdapter {

    ArrayList<Questions> arrayList;
    Context context;

    public AdapterListQuestion(ArrayList<Questions> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolde viewHolde;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_question, viewGroup, false);
            viewHolde = new ViewHolde(view);
            view.setTag(viewHolde);
        } else viewHolde = (ViewHolde) view.getTag();

        Questions question = arrayList.get(i);
        viewHolde.setView(question, i);

        return view;
    }

    class ViewHolde {
        TextView tvID, tvName;

        public ViewHolde(View view) {
            tvID = view.findViewById(R.id.tv_position);
            tvName = view.findViewById(R.id.tv_name);
        }

        public void setView(Questions question, Integer i) {

            tvID.setText(i+1 +"");
            tvName.setText(question.getCauHoi());
        }
    }
}
