package com.example.necklase.View;

import static android.content.Context.MODE_PRIVATE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.necklase.MVVM.Interactors.MapsInteractor;
import com.example.necklase.R;
import com.example.necklase.TokenValidator.VerificarToken;
import com.example.necklase.ViewModel.GoogleMapsViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.List;

public class activity_maps extends Fragment {

    private GoogleMap mMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            try {
                boolean success = googleMap.setMapStyle(
                        MapStyleOptions.loadRawResourceStyle(
                                getContext(), R.raw.googlemapsstyle));

                if (!success) {
                    Log.e("MapsActivity", "Estilo del mapa no se pudo cargar.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e("MapsActivity", "No se puede encontrar el estilo. Error: ", e);
            }

            LatLng posicionInicial = new LatLng(25.531561,-103.321922);
            miMarcador = googleMap.addMarker(new MarkerOptions().position(posicionInicial)
                    .icon(icon)
                    .title("Mi Ubicación"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicionInicial, 13));
        }
    };

    public void actualizarUbicacionMarcador(double nuevaLatitud, double nuevaLongitud) {
        LatLng nuevaPosicion = new LatLng(nuevaLatitud, nuevaLongitud);
        if (miMarcador == null) {
            miMarcador = mMap.addMarker(new MarkerOptions().position(nuevaPosicion).icon(icon));
        } else {
            miMarcador.setPosition(nuevaPosicion);
        }

        if (seguimientoActivo) {
            mMap.animateCamera(CameraUpdateFactory.newLatLng(nuevaPosicion));
        }
    }


    private MapsInteractor maps;
    private Handler handler = new Handler();
    private GoogleMapsViewModel miview;
    private Runnable runnableCode;
    private ImageView img;
    private Marker miMarcador;
    private BitmapDescriptor icon;
    private Button btnSeguirPunto;

    private boolean seguimientoActivo = false;

    private TextView txt, txt2, cordenadas;

    private String code;
    private String petname;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_maps, container, false);
        VerificarToken.Verificar(view.getContext());
        btnSeguirPunto = view.findViewById(R.id.btnSeguirPunto);

        cordenadas = view.findViewById(R.id.coordenadas);
        miview = new ViewModelProvider(this ).get(GoogleMapsViewModel.class);
        btnSeguirPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguimientoActivo = !seguimientoActivo;
                if (seguimientoActivo) {
                    btnSeguirPunto.setText("Detener Seguimiento");
                } else {
                    btnSeguirPunto.setText("Seguir Punto");
                }
            }
        });

        SharedPreferences codeDevice = getActivity().getSharedPreferences("deviceInfo", getActivity().MODE_PRIVATE);
        code = codeDevice.getString("deviceCode", null);
        miview.setDevicecode(code);

        miview.getDevicecode().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                code = s;
            }
        });

        maps = new MapsInteractor(view.getContext());
        runnableCode = new Runnable() {
            @Override
            public void run() {
                maps.getLocation(code);
                handler.postDelayed(this, 15000);
            }
        };

        handler.post(runnableCode);

        maps.locationLiveData.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                String coordenadas = strings.get(1);
                String[] partes = coordenadas.split(",");
                miview.setLatitud(coordenadas);

                if (partes.length >= 2) {
                    String latitud = partes[0];
                    String longitud = partes[1];

                    double lat = Double.parseDouble(latitud);
                    double lng = Double.parseDouble(longitud);

                    actualizarUbicacionMarcador(lat, lng);
                }
            }
        });

        miview.getLatitud().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                cordenadas.setText(s);
            }
        });

        txt = view.findViewById(R.id.nombreperro);
        txt2 = view.findViewById(R.id.nombreperro2);

        SharedPreferences setName = getActivity().getSharedPreferences("tempName", MODE_PRIVATE);
        String petname = setName.getString("tempName", null);

        txt.setText(petname);
        txt2.setText(petname);

        img = view.findViewById(R.id.luz);
        Animation scaleAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.light_blur_big);
        img.startAnimation(scaleAnimation);
        icon = BitmapDescriptorFactory.fromResource(R.mipmap.collar_icon);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


}