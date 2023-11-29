package com.example.necklase.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
 * Use the {@link MyPet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPet extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyPet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPet.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPet newInstance(String param1, String param2) {
        MyPet fragment = new MyPet();
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

    TextView nameText, razaText, generoText, dueñoText;
    LinearLayout goBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_pet, container, false);

        nameText = view.findViewById(R.id.nameText);
        razaText = view.findViewById(R.id.razaText);
        generoText = view.findViewById(R.id.generoText);
        dueñoText = view.findViewById(R.id.dueñoText);
        goBack = view.findViewById(R.id.goBack);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginPrefs", getActivity().MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        String userId = JwtUtils.decode(token).getSubject();


        RetrofitApiModel retro = new RetrofitApiModel(getContext());
        Retrofit retrofit = retro.provideRetrofit();
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);

        myPetManagment.getData(userId, new Callback<MyPetPostModel>() {
            @Override
            public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
                if (response.isSuccessful() && response.body() != null) {

                    MyPetPostModel personalData = response.body();
                    nameText.setText(personalData.getNombre());
                    razaText.setText(personalData.getRaza());
                    generoText.setText(personalData.getGenero());
                    dueñoText.setText(personalData.getDueño());
                }
            }
            @Override
            public void onFailure(Call<MyPetPostModel> call, Throwable t) {}
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new analytics()).commit();
            }
        });

        return view;
    }
}