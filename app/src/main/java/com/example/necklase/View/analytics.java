package com.example.necklase.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;

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
    TextView dogName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_analytics, container, false);

        SharedPreferences device = getActivity().getSharedPreferences("deviceID", getActivity().MODE_PRIVATE);
        String idDevice = device.getString("id", null);

        dogName = view.findViewById(R.id.dogName);

        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);

        myPetManagment.getData(idDevice, new Callback<MyPetPostModel>() {
            @Override
            public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
                if(response.isSuccessful()){
                    dogName.setText(response.body().getNombre());
                }
            }
            @Override
            public void onFailure(Call<MyPetPostModel> call, Throwable t) {}
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