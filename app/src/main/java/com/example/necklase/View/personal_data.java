package com.example.necklase.View;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.Model.Post.PersonalDataManagment;
import com.example.necklase.Model.Post.PersonalDataPostModel;
import com.example.necklase.Model.RetrofitApiModel;
import com.example.necklase.Model.RetrofitInterfaces.PersonalDataInterface;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link personal_data#newInstance} factory method to
 * create an instance of this fragment.
 */
public class personal_data extends Fragment {

    LinearLayout goBack;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public personal_data() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment personal_data.
     */
    // TODO: Rename and change types and number of parameters
    public static personal_data newInstance(String param1, String param2) {
        personal_data fragment = new personal_data();
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

    TextView nombreText, apellidoText, emailText, collaresText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personal_data, container, false);
        nombreText = view.findViewById(R.id.nameText);
        apellidoText = view.findViewById(R.id.lastNameText);
        emailText = view.findViewById(R.id.emailText);
        collaresText = view.findViewById(R.id.collaresText);
        goBack = view.findViewById(R.id.goBack);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginPrefs", getActivity().MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        String userId = JwtUtils.decode(token).getSubject();

        RetrofitApiModel retrofitApiModel = new RetrofitApiModel();
        Retrofit retrofit = retrofitApiModel.provideRetrofit();
        PersonalDataManagment personalDataManagment = new PersonalDataManagment(retrofit);

        personalDataManagment.getData(userId, new Callback<PersonalDataPostModel>() {
            @Override
            public void onResponse(Call<PersonalDataPostModel> call, Response<PersonalDataPostModel> response) {
                if (response.isSuccessful() && response.body() != null) {

                    PersonalDataPostModel personalData = response.body();
                    nombreText.setText(personalData.getNombre());
                    apellidoText.setText(personalData.getApellido());
                    emailText.setText(personalData.getEmail());
                    collaresText.setText(personalData.getNSensores());

                }
            }

            @Override
            public void onFailure(Call<PersonalDataPostModel> call, Throwable t) {
                nombreText.setText("no se pudo");
            }
        });








        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new personal_menu()).commit();
            }
        });

        return view;
    }

    public void actualizarInformacion(String nombre, String apellido, String email, String numeroDispositivos) {
        nombreText.setText(nombre);
        apellidoText.setText(apellido);
        emailText.setText(email);
        collaresText.setText(numeroDispositivos);
    }
}