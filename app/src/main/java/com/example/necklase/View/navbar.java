package com.example.necklase.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.necklase.R;
import com.example.necklase.databinding.ActivityNavbarBinding;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class navbar extends AppCompatActivity {
    ActivityNavbarBinding binding;
    private static final String SELECTED_ITEM_ID = "selected_item_id";
    private int selectedItem;
    private FrameLayout fr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fr = findViewById(R.id.frame);

        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getInt(SELECTED_ITEM_ID, R.id.inicio);
        } else {
            selectedItem = R.id.inicio;
        }
        binding.navBar.setSelectedItemId(selectedItem);

        binding.navBar.setOnItemSelectedListener(item -> {
            selectedItem = item.getItemId();

            if (selectedItem == R.id.inicio) {
                replaceFragment(new activity_home());
            } else if (selectedItem == R.id.statistics) {
                replaceFragment(new analytics());
            } else if (selectedItem == R.id.personal) {
                replaceFragment(new personal_menu());
            } else if (selectedItem == R.id.add) {
                replaceFragment(new activity_device());
            } else if (selectedItem == R.id.location) {
                replaceFragment(new activity_maps());
            }

            return true;
        });


        binding.navBar.setSelectedItemId(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, selectedItem);
    }

    private void replaceFragment(Fragment fragment){

        if (fragment instanceof activity_maps) {
            ViewGroup.LayoutParams params = fr.getLayoutParams();
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            fr.setLayoutParams(params);
        } else {
            ViewGroup.LayoutParams params = fr.getLayoutParams();
            params.height = dpToPx(0);
            fr.setLayoutParams(params);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();
    }
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
