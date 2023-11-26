package com.example.necklase.View;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.necklase.MVVM.Factorys.MyViewModelFactory;
import com.example.necklase.Model.Get.Device;
import com.example.necklase.Model.RetrofitApiModel;
import com.example.necklase.Model.RetrofitInterfaces.DispositivosInterface;
import com.example.necklase.Model.Token.JwtUtils;
import com.example.necklase.R;
import com.example.necklase.View.Adapter.DeviceConfigAdapter;
import com.example.necklase.MVVM.ViewModels.DevicesConfigViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link activity_device#newInstance} factory method to
 * create an instance of this fragment.
 */
public class activity_device extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DeviceConfigAdapter deviceAdapter;
    private DevicesConfigViewModel viewModel;

    private RecyclerView recyclerView;

    public activity_device() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment activity_device.
     */
    // TODO: Rename and change types and number of parameters
    public static activity_device newInstance(String param1, String param2) {
        activity_device fragment = new activity_device();
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

    private RetrofitApiModel apiService;

    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_device, container, false);

        SharedPreferences prefs = view.getContext().getSharedPreferences("loginPrefs", MODE_PRIVATE);
        String token = prefs.getString("token", null);
        DecodedJWT decodedJWT = JwtUtils.decode(token);
        String userId = decodedJWT.getSubject();

        MyViewModelFactory factory = new MyViewModelFactory(getActivity().getApplication(), userId );
        viewModel = new ViewModelProvider(this, factory).get(DevicesConfigViewModel.class);

        recyclerView = view.findViewById(R.id.recycler_view_device_config);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        viewModel.getMyDataList().observe(getViewLifecycleOwner(), resource -> {
            if (resource != null) {
                Log.d("FragmentObserver", "Resource status: " + resource.status);
                switch (resource.status) {
                    case SUCCESS:
                        if (resource.data != null && !resource.data.isEmpty()) {
                            Log.d("FragmentObserver", "Success with data: " + resource.data);
                            deviceAdapter = new DeviceConfigAdapter(view.getContext(), new DeviceConfigAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(Device item) {
                                    Intent intent = new Intent(view.getContext(), activity_home.class);
                                    intent.putExtra("id", item.getId());
                                    startActivity(intent);
                                }
                            }, resource.data);
                            recyclerView.setAdapter(deviceAdapter);
                        } else {
                            Toast.makeText(getContext(), "La lista de dispositivos está vacía", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case ERROR:
                        Log.e("FragmentObserver", "Error: " + resource.message);
                        Toast.makeText(getContext(), resource.message, Toast.LENGTH_SHORT).show();
                        break;
                    case LOADING:
                        Log.i("FragmentObserver", "Loading data");
                        break;
                }
            } else {
                Log.e("FragmentObserver", "Resource is null");
                Toast.makeText(getContext(), "Error desconocido", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}

