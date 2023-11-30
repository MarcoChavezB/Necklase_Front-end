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
import android.widget.Toast;

import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.MyPetManagment;
import com.example.necklase.Model.Post.MyPetPostModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;

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
    ImageView cambiar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_home, container, false);

        nombredeperro = view.findViewById(R.id.nombredeperro);
        cambiar = view.findViewById(R.id.cambiar);

        SharedPreferences device = getActivity().getSharedPreferences("deviceID", getActivity().MODE_PRIVATE);
        String idDevice = device.getString("id", null);


        RetrofitApiModelToken retro = new RetrofitApiModelToken();
        Retrofit retrofit = retro.provideRetrofit();
        MyPetManagment myPetManagment = new MyPetManagment(retrofit);


        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("deviceID", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("id");
                editor.apply();

                Router.redirectTo(getActivity(), SelectDog.class);
            }

        });

        myPetManagment.getData(idDevice, new Callback<MyPetPostModel>() {
            @Override
            public void onResponse(Call<MyPetPostModel> call, Response<MyPetPostModel> response) {
                if (response.isSuccessful()){
                    nombredeperro.setText(response.body().getNombre());
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("DogInfo", getActivity().MODE_PRIVATE).edit();
                    editor.putString("nombre", response.body().getNombre());
                    editor.putString("raza", response.body().getRaza());
                    editor.putString("genero", response.body().getGenero());
                    editor.apply();
                }else{
                    Toast.makeText(view.getContext(), "Error en cargar los datos del perro", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<MyPetPostModel> call, Throwable t) {}
        });
        return view;
    }
}