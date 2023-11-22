package com.example.necklase.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.necklase.Model.history;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.historyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link healt_history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class healt_history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public healt_history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment healt_history.
     */
    // TODO: Rename and change types and number of parameters
    public static healt_history newInstance(String param1, String param2) {
        healt_history fragment = new healt_history();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerV;
    ImageView goBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_healt_history, container, false);

        goBack = view.findViewById(R.id.goBack);
        goBack.setOnClickListener(v -> {
            getFragmentManager().beginTransaction().replace(R.id.frame, new dogStatus()).commit();
        });

        recyclerV = view.findViewById(R.id.recycler);
        List<history> historyList = new ArrayList<>();

        historyList.add(new history("Vacuna", "2020-10-10"));
        historyList.add(new history("Vacuna", "2020-10-10"));
        historyList.add(new history("Vacuna", "2020-10-10"));
        historyList.add(new history("Vacuna", "2020-10-10"));
        historyList.add(new history("Vacuna", "2020-10-10"));
        historyList.add(new history("Vacuna", "2020-10-10"));

        historyAdapter adapter = new historyAdapter(historyList);
        recyclerV.setAdapter(adapter);

        recyclerV.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerV.setHasFixedSize(true);

        return view;
    }
}