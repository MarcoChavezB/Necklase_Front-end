package com.example.necklase.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.necklase.R;
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
            } else if (item.getItemId() == R.id.home){
                replaceFragment(new collar_managment());
            } else if (item.getItemId() == R.id.add){
                replaceFragment(new activity_device());
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