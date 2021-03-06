package com.tyl.quickmath.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tyl.quickmath.R;
import com.tyl.quickmath.databinding.ActivityFragmentViewPagerBinding;
import java.util.Locale;

public class FragmentViewPagerActivity extends AppCompatActivity {

    ActivityFragmentViewPagerBinding binding;
    String appLanguage;
    // tab titles

    private String[] titleHe = new String[]{"קל", "בינוני", "קשה"};
    private String[] titleEn = new String[] {"Easy",  "Medium", "Hard"};

//    String a = getResources().getString(R.string.easy) ;
//    String b = getResources().getString(R.string.medium);
//    String c = getResources().getString(R.string.hard);
//    private String[] titles = new String[]{a,b,c};

    int intentFragment;
    boolean fromMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appLanguage = Locale.getDefault().getLanguage();
        fromMain = getIntent().getExtras().getBoolean("fromMain");
        binding = ActivityFragmentViewPagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intentFragment = getIntent().getExtras().getInt("frgToLoad");
        init();

    }

    private void init() {
        // removing toolbar elevation
        getSupportActionBar().setElevation(0);

        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));
        binding.viewPager.setCurrentItem(intentFragment);
        // attaching tab mediator
        if (appLanguage.equals("en")){
            new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                    (tab, position) -> tab.setText(titleEn[position])).attach();
        }
        else{
            new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                    (tab, position) -> tab.setText(titleHe[position])).attach();
        }
    }

    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new EasyFragment();
                case 1:
                    return new MediumFragment();
                case 2:
                    return new HardFragment();
            }
            return new EasyFragment();
        }

        @Override
        public int getItemCount() {
            if (appLanguage.equals("en")){
                return titleEn.length;
            }
            else{
                return titleHe.length;
            }

        }

    }
}
