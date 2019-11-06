package com.demo.music.town.screen.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.demo.music.town.R;
import com.demo.music.town.app.CoreApplication;
import com.demo.music.town.app.base.BaseFragment;
import com.demo.music.town.screen.home.HomeFragment;
import com.demo.music.town.screen.home.HomeModule;
import com.demo.music.town.screen.home.HomePresenter;
import com.demo.music.town.screen.music.MusicFragment;
import com.demo.music.town.screen.music.MusicModule;
import com.demo.music.town.screen.music.MusicPresenter;
import com.demo.music.town.screen.video_local.VideoLocalFragment;
import com.demo.music.town.screen.video_local.VideoLocalModule;
import com.demo.music.town.screen.video_local.VideoPresenter;
import com.demo.music.town.util.CustomViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MSI on 26/11/2017.
 */

public class DashboardFragment extends BaseFragment implements DashboardContract.View {
    private final String TAG = DashboardFragment.class.getName();

    HomeFragment homeFragment;
    MusicFragment musicFragment;
    VideoLocalFragment videoLocalFragment;
    @Inject
    HomePresenter homePresenter;

    @Inject
    MusicPresenter musicPresenter;

    @Inject
    VideoPresenter videoPresenter;

    @BindView(R.id.viewPager)
    CustomViewPager viewPager;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;


    public DashboardFragment() {
        // Required empty public constructor
    }


    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this, view);
        configFragments();
        setupViewPager();
        setupTabLayout();
        return view;
    }

    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void configFragments() {
        boolean noFragmentAdded = false;
        Fragment currentFragment = getChildFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (currentFragment != null) {
            if (currentFragment instanceof HomeFragment) {
                homeFragment = (HomeFragment) currentFragment;
            } else if (currentFragment instanceof MusicFragment) {
                musicFragment = (MusicFragment) currentFragment;
            } else if (currentFragment instanceof VideoLocalFragment) {
                videoLocalFragment = (VideoLocalFragment) currentFragment;
            }
        } else {
            noFragmentAdded = true;
        }

        if (homeFragment == null) {
            homeFragment = HomeFragment.newInstance();
        }

        if (musicFragment == null) {
            musicFragment = MusicFragment.newInstance();
        }

        if (videoLocalFragment == null) {
            videoLocalFragment = VideoLocalFragment.newInstance();
        }


        //khởi tạo presenter theo fragment
        CoreApplication.getInstance().getApplicationComponent().plus(
                new HomeModule(homeFragment),
                new MusicModule(musicFragment),
                new VideoLocalModule(videoLocalFragment),
                new DashboardModule(this)).inject(this);


    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_master, popup.getMenu());
        popup.show();
    }
    @OnClick({
            R.id.ib_menu, R.id.ib_menu_option
    })
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ib_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.ib_menu_option:
                showPopup(view);
                break;
        }
    }
    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        //  this.mPresenter = Precondition.checkNotNull(presenter);
    }

    @Override
    public void showProgressBar() {
        showProgressDialog();
    }

    @Override
    public void hideProgressBar() {
        hideProgressDialog();
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //  mPresenter.stop();
    }


    private void startDialogNoti(String content) {
        Activity activity = getActivity();
        if (activity != null) {
            new AlertDialog.Builder(activity)
                    .setTitle(getString(R.string.text_sweet_dialog_title))
                    .setMessage(content)
                    .setPositiveButton(getString(R.string.text_ok), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();

                        }
                    }).show();
        }
    }


    @Override
    public void showError(String error) {
        startDialogNoti(error);
    }

    @Override
    public void showSuccess(String message) {
        startDialogNoti(message);
    }


    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(homeFragment);
        adapter.addFragment(videoLocalFragment);
        adapter.addFragment(musicFragment);

        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }
}
