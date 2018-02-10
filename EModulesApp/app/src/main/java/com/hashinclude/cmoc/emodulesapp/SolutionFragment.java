package com.hashinclude.cmoc.emodulesapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class SolutionFragment extends Fragment {
    EventBus bus;
    LinearLayout solutionLinearLayout,notSolutionLinearLayout;
    QuestionModel questionModel;
    WebView solutionWebView;
    TextView solutionTextView;

    public SolutionFragment() {
        bus = EventBus.getDefault();
    }

    public static SolutionFragment newInstance(QuestionModel questionModel) {
        Bundle bundle = new Bundle();
        SolutionFragment fragment = new SolutionFragment();
        bundle.putParcelable("questionModel", questionModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bus.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    //catch Event from fragment A
    public void onEvent(SubmitButtonClickedEvent event) {
        solutionLinearLayout.setVisibility(View.VISIBLE);
        notSolutionLinearLayout.setVisibility(View.GONE);
        solutionWebView.loadDataWithBaseURL("", questionModel.getSolution(), "text/html", "UTF-8", "");
        solutionTextView.setText("Correct answer : " + questionModel.getCorrect());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        QuestionModel questionModel = bundle.getParcelable("questionModel");
        View row = inflater.inflate(R.layout.fragment_solution, container, false);
        this.questionModel = questionModel;


        solutionLinearLayout = row.findViewById(R.id.rootWhenSolutionVisible);
        solutionWebView = row.findViewById(R.id.solutionWebView);
        solutionTextView = row.findViewById(R.id.solutionTextView);
        notSolutionLinearLayout = row.findViewById(R.id.rootWhenSolutionNotVisible);
        solutionWebView.getSettings().setJavaScriptEnabled(true);

        if (TextUtils.isEmpty(questionModel.getMarked())) {
            notSolutionLinearLayout.setVisibility(View.VISIBLE);
        } else {
            solutionLinearLayout.setVisibility(View.VISIBLE);
            notSolutionLinearLayout.setVisibility(View.GONE);
            solutionWebView.loadDataWithBaseURL("", questionModel.getSolution(), "text/html", "UTF-8", "");
            solutionTextView.setText("Correct answer : " + questionModel.getCorrect());
        }
        return row;
    }
}