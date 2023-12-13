package com.example.necklase.View;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.security.keystore.StrongBoxUnavailableException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.necklase.MVVM.Interactors.AnaliticsInteractor;
import com.example.necklase.Model.Get.tempPerHourModel;
import com.example.necklase.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link analytics#newInstance} factory method to
 * create an instance of this fragment.
 */
public class analytics extends Fragment {

    LinearLayout dogStatus;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public analytics() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment analytics.
     */
    // TODO: Rename and change types and number of parameters
    public static analytics newInstance(String param1, String param2) {
        analytics fragment = new analytics();
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

    ImageView selectDevice, goDogInfo, triste, neutral, feliz;
    TextView dogName, temp, activeTime, test;
    LinearLayout malisimo, malo, regular, bueno, muyBueno;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analytics, container, false);

        SharedPreferences device = getActivity().getSharedPreferences("deviceID", getActivity().MODE_PRIVATE);
        String idDevice = device.getString("id", "1");

        SharedPreferences codeDevice = getActivity().getSharedPreferences("collar", getActivity().MODE_PRIVATE);
        String code = codeDevice.getString("codigo", null);

        dogName = view.findViewById(R.id.dogName);
        malisimo = view.findViewById(R.id.malisimo);
        malo = view.findViewById(R.id.malo);
        regular = view.findViewById(R.id.regular);
        bueno = view.findViewById(R.id.bueno);
        muyBueno = view.findViewById(R.id.muyBueno);
        triste = view.findViewById(R.id.triste);
        neutral = view.findViewById(R.id.neutral);
        feliz = view.findViewById(R.id.feliz);
        test = view.findViewById(R.id.test);
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        GraphView graphResting = (GraphView) view.findViewById(R.id.graphResting);


        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 2),
                new DataPoint(2, 3),
                new DataPoint(3, 4),
                new DataPoint(4, 5),
                new DataPoint(5, 6),
                new DataPoint(6, 7),
                new DataPoint(7, 8),
                new DataPoint(8, 9),
                new DataPoint(9, 10),
                new DataPoint(10, 11),
                new DataPoint(11, 12),
                new DataPoint(12, 13),

        });
        graphResting.addSeries(series);

        AnaliticsInteractor analiticsInteractor = new AnaliticsInteractor(getActivity());
        LiveData<String> info = analiticsInteractor.getInfoDog(idDevice);

        info.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newInfo) {
                dogName.setText(newInfo);
            }
        });

        LiveData<List<tempPerHourModel>> infoGraph = analiticsInteractor.getGraph(code);
        infoGraph.observe(getViewLifecycleOwner(), new Observer<List<tempPerHourModel>>() {
            @Override
            public void onChanged(List<tempPerHourModel> tempPerHourModels) {
                LineGraphSeries<DataPoint> graphInfo = new LineGraphSeries<>(new DataPoint[]{
                        new DataPoint(0, findValueForHour(tempPerHourModels, 0)),
                        new DataPoint(1, findValueForHour(tempPerHourModels, 1)),
                        new DataPoint(2, findValueForHour(tempPerHourModels, 2)),
                        new DataPoint(3, findValueForHour(tempPerHourModels, 3)),
                        new DataPoint(4, findValueForHour(tempPerHourModels, 4)),
                        new DataPoint(5, findValueForHour(tempPerHourModels, 5)),
                        new DataPoint(6, findValueForHour(tempPerHourModels, 6)),
                        new DataPoint(7, findValueForHour(tempPerHourModels, 7)),
                        new DataPoint(8, findValueForHour(tempPerHourModels, 8)),
                        new DataPoint(9, findValueForHour(tempPerHourModels, 9)),
                        new DataPoint(10, findValueForHour(tempPerHourModels, 10)),
                        new DataPoint(11, findValueForHour(tempPerHourModels, 11)),
                        new DataPoint(12, findValueForHour(tempPerHourModels, 12)),
                });
                graph.addSeries(graphInfo);
            }
        });


        // humedad data
        LiveData<String> hum = analiticsInteractor.getHum(code);
        hum.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newHum) {
                if(newHum != null){

                }else{

                }
            }
        });

        // temperatura data
        LiveData<List<String>> tempLiveData = analiticsInteractor.getTemp(code);
        tempLiveData.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> tempList) {
                String tempV = tempList.get(0);
                String nivelV = tempList.get(1);
            }
        });

        // Air data
        LiveData<String> airLiveData = analiticsInteractor.getLevelAir(code);
        airLiveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                float alphaMalisimo = 0.3f;
                float alphaMalo = 0.3f;
                float alphaRegular = 0.3f;
                float alphaBueno = 0.3f;
                float alphaMuyBueno = 0.3f;

                switch (s) {
                    case "1":
                        alphaMuyBueno = 1.0f;
                        break;
                    case "2":
                        alphaBueno = 1.0f;
                        break;
                    case "3":
                        alphaRegular = 1.0f;
                        break;
                    case "4":
                        alphaMalo = 1.0f;
                        break;
                    case "5":
                        alphaMalisimo = 1.0f;
                        break;
                    case "6":
                        alphaMalisimo = 1.0f;
                        break;
                }
                malisimo.setAlpha(alphaMalisimo);
                malo.setAlpha(alphaMalo);
                regular.setAlpha(alphaRegular);
                bueno.setAlpha(alphaBueno);
                muyBueno.setAlpha(alphaMuyBueno);
            }
        });

        LiveData<List<String>> emocionLiveData = analiticsInteractor.getEmocion(code);
        emocionLiveData.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                activeTime.setText(strings.get(0)); // reposo

                float tristeAlpha = 0.3f;
                float neutralAlpha = 0.3f;
                float felizAlpha = 0.3f;

                switch (strings.get(1)){ // emocional status
                    case "1":
                        tristeAlpha = 1.0f;
                        break;
                    case "2":
                        neutralAlpha = 1.0f;
                        break;
                    case "3":
                        felizAlpha = 1.0f;
                        break;
                }
                triste.setAlpha(tristeAlpha);
                neutral.setAlpha(neutralAlpha);
                feliz.setAlpha(felizAlpha);
            }
        });







        goDogInfo = view.findViewById(R.id.goDogInfo);
        goDogInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new MyPet()).commit();
            }
        });

        dogStatus = view.findViewById(R.id.dogStatus);
        dogStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new dogStatus()).commit();
            }
        });

        selectDevice = view.findViewById(R.id.selectDevice);
        selectDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new selectDevice()).commit();
            }
        });
        return view;
    }

    private double findValueForHour(List<tempPerHourModel> models, double hour) {
        for (tempPerHourModel model : models) {
            try {
                SimpleDateFormat sdfInput = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                Date date = sdfInput.parse(model.getCreated_at());

                // Comparar solo las horas (ignorar minutos y segundos)
                if (hour == date.getHours()) {
                    return Double.parseDouble(model.getValue());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        // Valor predeterminado si no se encuentra ninguna correspondencia
        return 0.0;
    }
}