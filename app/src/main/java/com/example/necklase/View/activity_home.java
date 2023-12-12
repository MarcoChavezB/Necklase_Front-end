package com.example.necklase.View;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.necklase.MVVM.Interactors.HomeInteractor;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.Router.Router;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModelToken.ViewModelTokenIns;
import java.text.DecimalFormat;
import java.util.List;


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

    TextView nombredeperro, textViewEstadistica, textViewEstadistica4, porcentaje, feels, maxTemp, minTemp, temp, ciudad, estado;
    ImageView cambiar;
    Button buttonLocate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_activity_home, container, false);

        VerificarToken.Verificar(view.getContext());

        ViewModelTokenIns viewModelTokenIns = ViewModelTokenIns.getinstance();
        ViewModelTokenIns.settoken(view.getContext());

        HomeInteractor homeInteractor = new HomeInteractor(getActivity());
        homeInteractor.setCorrectDevice(ViewModelTokenIns.getinstance().getId());

        nombredeperro = view.findViewById(R.id.nombredeperro);
        cambiar = view.findViewById(R.id.cambiar);
        textViewEstadistica = view.findViewById(R.id.textViewEstadistica);
        textViewEstadistica4 = view.findViewById(R.id.textViewEstadistica4);
        buttonLocate = view.findViewById(R.id.buttonLocate);
        porcentaje = view.findViewById(R.id.porcentaje);
        feels = view.findViewById(R.id.feels);
        maxTemp = view.findViewById(R.id.maxTemp);
        minTemp = view.findViewById(R.id.minTemp);
        temp = view.findViewById(R.id.temp);
        ciudad = view.findViewById(R.id.ciudad);
        estado = view.findViewById(R.id.estado);

        SharedPreferences device = getActivity().getSharedPreferences("deviceID", getActivity().MODE_PRIVATE);
        String idDevice = device.getString("id", null);

        SharedPreferences codeDevice = getActivity().getSharedPreferences("collar", getActivity().MODE_PRIVATE);
        String code = codeDevice.getString("codigo", null);


        SharedPreferences dogId = getActivity().getSharedPreferences("infoDog", getActivity().MODE_PRIVATE);
        String DogId = dogId.getString("dogId", null);

        LiveData<String> hum = homeInteractor.getHum(code);
        hum.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String newHum) {
                if(newHum != null){
                    textViewEstadistica.setText(newHum + "%");
                }else{
                    textViewEstadistica.setText("NaN");
                }
            }
        });

        LiveData<String> infoDog = homeInteractor.getInfoDog(DogId);
        infoDog.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null){
                    nombredeperro.setText(s);
                }else{
                    nombredeperro.setText("NaN");
                }
            }
        });

        LiveData<String> tempDog = homeInteractor.getTemp(code);
        tempDog.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        LiveData<List<String>> infoCalories = homeInteractor.getCalories(code, "10");
        infoCalories.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> calories) {
                String v = noDecimal(calories.get(0));
                textViewEstadistica4.setText(v + "kcl");
            }
        });

        LiveData<List<String>> infoClima = homeInteractor.getClima("25.533082, -103.320441");
        infoClima.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                porcentaje.setText(strings.get(6) + "%");
                feels.setText(strings.get(2) + "°C");
                maxTemp.setText(strings.get(5) + "°C");
                minTemp.setText(strings.get(4) + "°C");
                temp.setText(strings.get(3) + "°C");
                ciudad.setText(strings.get(0));
                estado.setText(strings.get(1));
            }
        });

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor remove = getActivity().getSharedPreferences("collar", Context.MODE_PRIVATE).edit();
                remove.remove("codigo");
                remove.apply();

                Router.redirectTo(getActivity(), SelectDog.class);
            }
        });


        buttonLocate.setOnClickListener(v ->{
            getFragmentManager().beginTransaction().replace(R.id.frame,new activity_maps()).commit();
        });


        return view;
    }


    public String noDecimal(String valor){
      double numero = Double.parseDouble(valor);
        DecimalFormat format = new DecimalFormat("#");
        String numeroFormateado = format.format(numero);
        return numeroFormateado.toString();
    };

}