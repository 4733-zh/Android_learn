package com.example.bundlefragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {

    private View mInflate;
    private Button mBtn;
    private TextView mText;
    private String mMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflate = inflater.inflate(R.layout.fragment_first,container,false);
        mBtn = mInflate.findViewById(R.id.first_button);
        mText = mInflate.findViewById(R.id.first_text);
        mMessage = getArguments().getString("message");
        mBtn.setOnClickListener(view -> mText.setText(mMessage));
        return mInflate;
    }
}