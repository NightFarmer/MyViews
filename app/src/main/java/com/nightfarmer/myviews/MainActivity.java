package com.nightfarmer.myviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.nightfarmer.myviews.wediget.SwitchView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SwitchView sv1 = (SwitchView) findViewById(R.id.switchView1);
        sv1.setImageResource(R.drawable.switch_btn_slip, R.drawable.switch_bkg_switch, R.drawable.switch_bkg_switch);

        final SwitchView sv2 = (SwitchView) findViewById(R.id.switchView2);
        sv2.setImageResource(R.drawable.del_btn, R.drawable.open_toggle_bg, R.drawable.clost_toggle_bg);

        sv1.setOnCheckedStateChangedListener(new SwitchView.OnCheckedStateChangedListener() {
            @Override
            public void onChange(SwitchView switchView, boolean checked) {
                sv2.setChecked(checked);
                Toast.makeText(MainActivity.this, ""+checked, Toast.LENGTH_SHORT).show();
            }
        });

        sv2.setOnCheckedStateChangedListener(new SwitchView.OnCheckedStateChangedListener() {
            @Override
            public void onChange(SwitchView switchView, boolean checked) {
                sv1.setChecked(checked);
            }
        });
    }
}
