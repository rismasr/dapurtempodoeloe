package com.dapurtempoedoeloe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private BannerSlider bannerSlider;
    private LinearLayout mLinearLayout;

    public static void start(Context context){
        Intent intent = new Intent(context, Dashboard.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bannerSlider = findViewById(R.id.sliderView);
        mLinearLayout = findViewById(R.id.pagesContainer);
        setupSlider();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void setupSlider() {
        bannerSlider.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();

        //link image
        fragments.add(FragmentSlider.newInstance("https://1.bp.blogspot.com/-janzBM24Tjs/YOhI2rvDKqI/AAAAAAAABrg/rg-BNwTHMgEToy3pxIH7M-yJpTwpuz3JgCLcBGAsYHQ/s500/baner1.png"));
        fragments.add(FragmentSlider.newInstance("https://1.bp.blogspot.com/-XbeQCD0ILrc/YOhI2tsxbEI/AAAAAAAABrk/Wk9TKmCxgXQaOUlsLXZE11b0cMbgpav6ACLcBGAsYHQ/s500/baner2.png"));
        fragments.add(FragmentSlider.newInstance("https://1.bp.blogspot.com/-RLpirvEXIo4/YOhI2ueGNZI/AAAAAAAABro/3IddV38Zl6slRVHyFf4G9eYxuzGX8_kfgCLcBGAsYHQ/s500/baner3.png"));
        fragments.add(FragmentSlider.newInstance("https://1.bp.blogspot.com/-jRlfJx7EMEQ/YOhI3VGQSjI/AAAAAAAABrs/UcNHMKjsgccahAdlaqStHjDkK-Uwl6zlwCLcBGAsYHQ/s500/baner4.png"));

        mAdapter = new SliderPagerAdapter(getSupportFragmentManager(), fragments);
        bannerSlider.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, bannerSlider, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
    public void makanan(View view){
        Intent next = new Intent(Dashboard.this, Makanan.class);
        startActivity(next);
    }
    public void minuman(View view){
        Intent next = new Intent(Dashboard.this, Minuman.class);
        startActivity(next);
    }
    public void pesanan(View view){
        Intent next = new Intent(Dashboard.this, Pesanan.class);
        startActivity(next);
    }
    public void riwayat(View view){
        Intent next = new Intent(Dashboard.this, Riwayat.class);
        startActivity(next);
    }

    public void tentang(View view){
        Intent next = new Intent(Dashboard.this, About.class);
        startActivity(next);
    }
}