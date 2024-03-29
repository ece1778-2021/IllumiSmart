package android.example.illumismart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;

public class LightLevelSuggestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_level_suggest);
        View v = findViewById(R.id.light_level_suggest_screen);
        View root = v.getRootView();
        root.setBackgroundColor(Color.parseColor("#FAFAFA"));

        MaterialToolbar topAppBar = findViewById(R.id.lux_sugg_top_app_bar);
        TextView luxSuggestRange = findViewById(R.id.lux_suggest_range);
        TextView luxCorrectLight = findViewById(R.id.lux_correct_light);
        TextView luxReadMore = findViewById(R.id.light_level_suggest_read_more);

        // Set navigation back
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Set user guidance
        topAppBar.setOnMenuItemClickListener(menuItem -> {
            if(menuItem.getItemId() == R.id.nav_guide) {
                startActivity(new Intent(LightLevelSuggestActivity.this,
                        LightLevelGuideActivity.class));
                return true;
            }
            return false;
        });
        // Set illuminance range
        Intent intent = getIntent();
        String min = intent.getStringExtra("luxRangeMin");
        String max = intent.getStringExtra("luxRangeMax");
        String range = min + "-" + max + " Lux";
        luxSuggestRange.setText(range);

        luxCorrectLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LightLevelSuggestActivity.this, LightCorrectActivity.class));
            }
        });

        luxReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LightLevelSuggestActivity.this, ReadMoreLightActivity.class));
            }
        });
    }
}