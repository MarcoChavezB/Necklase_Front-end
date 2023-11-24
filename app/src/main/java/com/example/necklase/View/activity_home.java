package com.example.necklase.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.RetrofitApiModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link activity_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class activity_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public activity_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment activity_home.
     */
    // TODO: Rename and change types and number of parameters
    public static activity_home newInstance(String param1, String param2) {
        activity_home fragment = new activity_home();
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

    TextView nombredeperro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_home, container, false);

        nombredeperro = view.findViewById(R.id.nombredeperro);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginPrefs", getActivity().MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        String userId = JwtUtils.decode(token).getSubject();

        RetrofitApiModel retro = new RetrofitApiModel(getContext());
        Retrofit retrofit = retro.provideRetrofit();
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);

        myPetManagment.getData(userId, new Callback<MyPetPostModel>() {
            @Override
            public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
                nombredeperro.setText(response.body().getNombre());
            }
            @Override
            public void onFailure(Call<MyPetPostModel> call, Throwable t) {}
        });



        return view;
    }
}