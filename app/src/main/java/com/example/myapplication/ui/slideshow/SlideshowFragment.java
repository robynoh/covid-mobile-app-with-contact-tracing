package com.example.myapplication.ui.slideshow;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.myapplication.R;

public class SlideshowFragment extends Fragment {


    private LinearLayout one;
    private LinearLayout two;
    private LinearLayout three;
    private LinearLayout four;
    private LinearLayout five;
    private LinearLayout six;
    private LinearLayout seven;


    private SlideshowViewModel slideshowViewModel;
    

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);



        one =root.findViewById(R.id.one);
        one.setVisibility(View.VISIBLE);

         two =root.findViewById(R.id.two);
        two.setVisibility(View.INVISIBLE);


        three = root.findViewById(R.id.three);
        three.setVisibility(View.INVISIBLE);

        four = root.findViewById(R.id.four);
        four.setVisibility(View.INVISIBLE);


        five = root.findViewById(R.id.five);
        five.setVisibility(View.INVISIBLE);

        six = root.findViewById(R.id.six);
        six.setVisibility(View.INVISIBLE);


        seven = root.findViewById(R.id.seven);
        seven.setVisibility(View.INVISIBLE);





        final TextView textView = root.findViewById(R.id.text_slideshow);


        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);




            }
        });
        return root;




    }




}
