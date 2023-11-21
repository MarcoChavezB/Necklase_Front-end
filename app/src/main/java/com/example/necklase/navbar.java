package com.example.necklase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.necklase.databinding.ActivityMainBinding;
import com.example.necklase.databinding.ActivityNavbarBinding;

import android.os.Bundle;

public class navbar extends AppCompatActivity {
    ActivityNavbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new collar_managment());

        binding.navBar.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.location) {
                replaceFragment(new collar_managment());
            } else if (item.getItemId() == R.id.statistics) {
                replaceFragment(new analytics());
            } else if (item.getItemId() == R.id.home) {
                replaceFragment(new activity_home());
            } else if (item.getItemId() == R.id.personal) {
                replaceFragment(new personal_menu());
<<<<<<< HEAD
            } else if (item.getItemId() == R.id.home){
                replaceFragment(new collar_managment());
=======
            } else if (item.getItemId() == R.id.add){
                // aqui remplazas el fragmento por el que quieras en el else if R.id.home esta parte es el
                // id del item que esta en el archivo menu/button_nav_menu
                replaceFragment(new activity_device());
>>>>>>> f7a5b816b0150ab509b53b72dc3f83c3fd8c00e9
            }

            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
}