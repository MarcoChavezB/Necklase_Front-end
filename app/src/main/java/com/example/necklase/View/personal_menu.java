package com.example.necklase.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.MVVM.Interactors.AnaliticsInteractor;
import com.example.necklase.MVVM.Interactors.PersonalMenuInteractor;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModelToken;
import com.example.necklase.Model.Post.LogoutManagment;
import com.example.necklase.Model.Post.LogoutPostModel;
import com.example.necklase.Model.Post.PersonalDataManagment;
import com.example.necklase.Model.Post.PersonalDataPostModel;
import com.example.necklase.Model.IntanciasRetrofit.RetrofitApiModel;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModel.PersonalMenuViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link personal_menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class personal_menu extends Fragment {
    LinearLayout personal_data, suport;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public personal_menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment personal_menu.
     */
    // TODO: Rename and change types and number of parameters
    public static personal_menu newInstance(String param1, String param2) {
        personal_menu fragment = new personal_menu();
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

    LinearLayout logout, pet, location;
    TextView test, namePerson, emailPerson;
    private PersonalMenuViewModel personalMenuViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_menu, container, false);

        VerificarToken.Verificar(view.getContext());

        SharedPreferences prefs = getActivity().getSharedPreferences("loginPrefs", getActivity().MODE_PRIVATE);
        String token = prefs.getString("token", null);
        DecodedJWT decodedJWT = JwtUtils.decode(token);
        String userId =  decodedJWT.getSubject();

        pet = view.findViewById(R.id.pet);
        logout = view.findViewById(R.id.logout);
        personal_data = view.findViewById(R.id.personal);
        test = view.findViewById(R.id.test);
        namePerson = view.findViewById(R.id.namePerson);
        emailPerson = view.findViewById(R.id.emailPerson);
        location = view.findViewById(R.id.location);

        PersonalMenuInteractor interactor = new PersonalMenuInteractor(getActivity());
        LiveData<List<String>> info = interactor.getInfoData(userId);

        info.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                namePerson.setText(strings.get(0));
                emailPerson.setText(strings.get(1));
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new activity_maps()).commit();
            }
        });


        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), activity_pets_info.class);
                view.getContext().startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interactor.logout(userId);
            }
        });

        personal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new personal_data()).commit();
            }
        });

        suport = view.findViewById(R.id.suport);
        suport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame, new about()).commit();
            }
        });

        return view;
    }
}