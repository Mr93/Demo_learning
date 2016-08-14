package com.example.demolearning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mamram on 8/14/2016.
 */
public class CustomFragment extends Fragment {
    private String title;
    public CustomFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        title = getArguments().getString("title");
        View view = inflater.inflate(R.layout.layout_fragment, container, false);
        ((TextView)view.findViewById(R.id.textView)).setText(title);
        return view;
    }
}
