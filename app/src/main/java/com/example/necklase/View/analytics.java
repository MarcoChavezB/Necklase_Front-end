package com.example.necklase.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.MVVM.Interactors.AnaliticsInteractor;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    ImageView selectDevice, goDogInfo;
    TextView dogName, restingTime, temp;
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
        restingTime = view.findViewById(R.id.restingTime);
        temp = view.findViewById(R.id.temp);
        malisimo = view.findViewById(R.id.malisimo);
        malo = view.findViewById(R.id.malo);
        regular = view.findViewById(R.id.regular);
        bueno = view.findViewById(R.id.bueno);
        muyBueno = view.findViewById(R.id.muyBueno);

        AnaliticsInteractor analiticsInteractor = new AnaliticsInteractor(getActivity());
        LiveData<String> info = analiticsInteractor.getInfoDog(idDevice);

        info.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newInfo) {
                dogName.setText(newInfo);
            }
        });

        // humedad data

        LiveData<String> hum = analiticsInteractor.getHum(code);
        hum.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newHum) {
                if(newHum != null){
                    restingTime.setText(newHum);
                }else{
                    restingTime.setText("No data");
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
                temp.setText(nivelV);
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
}