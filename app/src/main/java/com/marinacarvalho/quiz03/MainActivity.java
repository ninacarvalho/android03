package com.marinacarvalho.quiz03;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.slider.Slider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Slider and TextView
        Slider slider = findViewById(R.id.rrsp_slider);
        TextView sliderValue = findViewById(R.id.slider_value);

        // Update TextView as the slider moves
        slider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(Slider slider, float value, boolean fromUser) {
                sliderValue.setText("Value: " + (int) value);
            }
        });
    }
}
