package apero.aperosg.monetizationsample.javafragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import apero.aperosg.monetizationsample.R;
import apero.aperosg.monetizationsample.databinding.ActivityFragmentBinding;

public class JavaFragmentActivity extends AppCompatActivity {
    private ActivityFragmentBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().add(
                R.id.container, JavaAdsFragment.class, null
        ).commit();
    }
}
