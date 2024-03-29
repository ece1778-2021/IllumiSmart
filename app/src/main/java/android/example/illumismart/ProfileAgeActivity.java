package android.example.illumismart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.card.MaterialCardView;

public class ProfileAgeActivity extends AppCompatActivity implements View.OnClickListener{
    private MaterialCardView ageBelow40Card;
    private MaterialCardView age4050Card;
    private MaterialCardView age5065Card;
    private MaterialCardView ageAbove65Card;
    private Button skipHome;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_age);

        ageBelow40Card = findViewById(R.id.age_below40_btn);
        age4050Card = findViewById(R.id.age_4050_btn);
        age5065Card = findViewById(R.id.age_5065_btn);
        ageAbove65Card = findViewById(R.id.age_above65_btn);
        skipHome = findViewById(R.id.age_skip);

        preferences = getSharedPreferences("ProfileInfo",MODE_PRIVATE);

        ageBelow40Card.setOnClickListener(this);
        age4050Card.setOnClickListener(this);
        age5065Card.setOnClickListener(this);
        ageAbove65Card.setOnClickListener(this);
        skipHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        editor = preferences.edit();
        if (id == R.id.age_below40_btn) {
            editor.putString("Age", "Below 40");
        } else if (id == R.id.age_4050_btn) {
            editor.putString("Age", "40-50");
        } else if (id == R.id.age_5065_btn) {
            editor.putString("Age", "50-65");
        } else if (id == R.id.age_above65_btn) {
            editor.putString("Age", "Above 65");
        }
        editor.apply();

        SharedPreferences preferences = getSharedPreferences("Profile",MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            //fist time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.apply();
            startActivity(new Intent(ProfileAgeActivity.this, HomeActivity.class));
        } else {
            //enter from profile
            startActivity(new Intent(ProfileAgeActivity.this, ProfileActivity.class));
        }

        if (id == R.id.age_skip) {
            if (!ranBefore) {
                //fist time
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("RanBefore", true);
                editor.apply();
                startActivity(new Intent(ProfileAgeActivity.this, HomeActivity.class));
            } else {
                //enter from profile
                startActivity(new Intent(ProfileAgeActivity.this, ProfileActivity.class));
            }
        }
    }
}