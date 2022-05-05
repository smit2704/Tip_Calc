package com.example.tipcalc;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tipcalc.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTip();
            }
        });
    }
    private void calculateTip(){
            final String strCost = binding.costOfServiceEdit.getText().toString();


        try{
            double deciCost = Double.parseDouble(strCost);
            double tipPercentage;
            switch (binding.tipOptions.getCheckedRadioButtonId()) {
                case R.id.amazing:
                    tipPercentage = 0.20;
                    break;
                case R.id.good:
                    tipPercentage = 0.18;
                    break;
                default:
                    tipPercentage = 0.15;
            }
            double tip;
            tip = deciCost * tipPercentage;
            boolean roundup = binding.roundUpSwitch.isChecked();
            if (roundup) {
                tip = Math.ceil(tip);
            }
            displayTip(tip);
        }
        catch(Exception e) {
                binding.tipResult.setText(R.string.plz_enter);
            }
        }
    private void displayTip(double tip) {
        String formattedtip = java.text.NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.setText(getString(R.string.tip_amount, formattedtip));
    }
}
