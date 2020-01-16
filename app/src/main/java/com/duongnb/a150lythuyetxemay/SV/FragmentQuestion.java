package com.duongnb.a150lythuyetxemay.SV;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.duongnb.a150lythuyetxemay.Model.Questions;
import com.duongnb.a150lythuyetxemay.Model.Selected;
import com.duongnb.a150lythuyetxemay.Model.Question;
import com.duongnb.a150lythuyetxemay.R;
import com.squareup.picasso.Picasso;

public class FragmentQuestion extends Fragment {
    LinearLayout ln4 , Ln4 , ln3 , Ln3, lnimg;
    TextView tvCauHoi, tvCauTL1, tvCauTL2, tvCauTL3, tvCauTL4, tvDapAn;
    CheckBox cb1, cb2, cb3, cb4;
    String cau;
    int position;
    View view;
    ImageView img;
    private Questions question;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_question, container, false);
        init();
        setView();


        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = (Questions) getArguments().getSerializable("input");
        position = getArguments().getInt("position");


    }


    public static FragmentQuestion newInstace(Questions question,int position) {
        FragmentQuestion fragment = new FragmentQuestion();
        Bundle args = new Bundle();
        args.putSerializable("input", question);
        args.putSerializable("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    void init() {
        lnimg = view.findViewById(R.id.ln_img);
        ln4 = view.findViewById(R.id.ln_4);
        Ln4 = view.findViewById(R.id.ln4);
        Ln3 = view.findViewById(R.id.ln3);
        ln3 = view.findViewById(R.id.ln_3);
        tvCauHoi = view.findViewById(R.id.tv_frag_question);
        tvCauTL1 = view.findViewById(R.id.tv_frag_cauhoi1);
        tvCauTL2 = view.findViewById(R.id.tv_frag_cauhoi2);
        tvCauTL3 = view.findViewById(R.id.tv_frag_cauhoi3);
        tvCauTL4 = view.findViewById(R.id.tv_frag_cauhoi4);
        tvDapAn = view.findViewById(R.id.tv_cau_tl);
        img = view.findViewById(R.id.img_frg);
        cb1 = view.findViewById(R.id.cb_frag_1);
        cb2 = view.findViewById(R.id.cb_frag_2);
        cb3 = view.findViewById(R.id.cb_frag_3);
        cb4 = view.findViewById(R.id.cb_frag_4);
        tvDapAn.setText(question.getDapAn());

        cau = question.getId();

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestionState(1,cb1);
            }
        });
        cb2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        saveQuestionState(2,cb2);
    }
});
        cb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestionState(3,cb3);
            }
        });
        cb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestionState(4,cb4);
            }
        });

    }

    void setView() {
        if (question.getImage().isEmpty()){
           lnimg.setVisibility(View.GONE);
        }else Picasso.with(getContext()).load(question.getImage()).error(R.drawable.ic_camera).into(img);
        tvCauHoi.setText(question.getCauHoi());
        tvCauTL1.setText(question.getCauTL1());
        tvCauTL2.setText(question.getCauTL2());
        if (question.getCauTL3().isEmpty()){
            ln3.setVisibility(View.GONE);
            Ln3.setVisibility(View.GONE);
        }else tvCauTL3.setText(question.getCauTL3());
        if (question.getCauTL4().isEmpty()){
            ln4.setVisibility(View.GONE);
            Ln4.setVisibility(View.GONE);
        }else tvCauTL4.setText(question.getCauTL4());

    }
    private void saveQuestionState(int i, CheckBox checkBox) {
        Selected traLoi =  TLCauHoi.map.get(Integer.valueOf(this.cau));
        if (traLoi == null) {
            traLoi = new Selected(String.valueOf(this.cau));
        }
        traLoi.setTraLoi(i, checkBox.isChecked());
       TLCauHoi.map.put(Integer.parseInt(this.cau), traLoi);
    }


    public void KiemTraKq(){

        if (!question.getDapAn().contains("1") && this.cb1.isChecked()){
            tvCauTL1.setTextColor(Color.RED);
            cb1.setChecked(false);
        }else if (question.getDapAn().contains("1") && !this.cb1.isChecked()){
            tvCauTL1.setTextColor(Color.YELLOW);
        }else if (question.getDapAn().contains("1") && this.cb1.isChecked()){
            tvCauTL1.setTextColor(Color.GREEN);
        }
        if (!question.getDapAn().contains("2") && this.cb2.isChecked()){
            tvCauTL2.setTextColor(Color.RED);
            cb2.setChecked(false);
        }else if (question.getDapAn().contains("2") && !this.cb2.isChecked()){
            tvCauTL2.setTextColor(Color.YELLOW);
        }else if (question.getDapAn().contains("2") && this.cb2.isChecked()){
            tvCauTL2.setTextColor(Color.GREEN);
        }
        if (!question.getDapAn().contains("3") && this.cb3.isChecked()){
            tvCauTL3.setTextColor(Color.RED);
            cb3.setChecked(false);
        }else if (question.getDapAn().contains("3") && !this.cb3.isChecked()){
            tvCauTL3.setTextColor(Color.YELLOW);
        }else if (question.getDapAn().contains("3") && this.cb3.isChecked()){
            tvCauTL3.setTextColor(Color.GREEN);
        }
        if (!question.getDapAn().contains("4") && this.cb4.isChecked()){
            tvCauTL4.setTextColor(Color.RED);
            cb4.setChecked(false);
        }else if (question.getDapAn().contains("4") && !this.cb4.isChecked()){
            tvCauTL4.setTextColor(Color.YELLOW);
        }else if (question.getDapAn().contains("4") && this.cb4.isChecked()){
            tvCauTL4.setTextColor(Color.GREEN);
        }




//        if (question.getDapAn().equals(strDapan.toString())){
//            TLCauHoi.hashMapDapAn.put(question.getId(),true);
//            TLCauHoi.dung++;
//
//
//        }else {
//            TLCauHoi.hashMapDapAn.put(question.getId(),false);
//            TLCauHoi.sai ++;
//        }
//        Toast.makeText(getContext(), "ĐÚng " +TLCauHoi.dung + "  Sai"+ TLCauHoi.sai, Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onResume() {
        super.onResume();
        Log.d("1223", "onResume: " + cau);

//        if (TLCauHoi.Checked == true){
//            Selected selected = TLCauHoi.map.get(this.cau);
//            if (selected==null){
//
//            }else {
//                if (selected.isTraloi1() && !question.getDapAn().contains("1")){
//                    tvCauTL1.setTextColor(Color.RED);
//                }else if (selected.isTraloi1() && question.getDapAn().contains("1")){
//                    tvCauTL1.setTextColor(Color.GREEN);
//                }else if (!selected.isTraloi1() && question.getDapAn().contains("1")){
//                    tvCauTL1.setTextColor(Color.YELLOW);
//                }
//                if (selected.isTraloi2() && !question.getDapAn().contains("2")){
//                    tvCauTL2.setTextColor(Color.RED);
//                }
//                else if (selected.isTraloi2() && question.getDapAn().contains("2")){
//                    tvCauTL2.setTextColor(Color.GREEN);
//                }else if (!selected.isTraloi2() && question.getDapAn().contains("2")){
//                    tvCauTL2.setTextColor(Color.YELLOW);
//                }
//                if (selected.isTraloi3() && !question.getDapAn().contains("3")){
//                    tvCauTL3.setTextColor(Color.RED);
//                }else if (selected.isTraloi3() && question.getDapAn().contains("3")){
//                    tvCauTL3.setTextColor(Color.GREEN);
//                }else if (!selected.isTraloi3() && question.getDapAn().contains("3")){
//                    tvCauTL3.setTextColor(Color.YELLOW);
//                }
//                if (selected.isTraloi4() && !question.getDapAn().contains("4")){
//                    tvCauTL4.setTextColor(Color.RED);
//                }else if (selected.isTraloi4() && question.getDapAn().contains("4")){
//                    tvCauTL4.setTextColor(Color.GREEN);
//                }else if (!selected.isTraloi4() && question.getDapAn().contains("4")){
//                    tvCauTL4.setTextColor(Color.YELLOW);
//                }
//
//            }
//
//        }

    }



}
