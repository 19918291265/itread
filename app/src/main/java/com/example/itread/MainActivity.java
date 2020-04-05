package com.example.itread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.itread.Base.BaseFragment;
import com.example.itread.Ui.fragment.guide.BookListFragment;
import com.example.itread.Ui.fragment.guide.NewBookFragment;
import com.example.itread.Ui.fragment.guide.PersonFragment;
import com.example.itread.Util.SharedPreferencesUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="NewBookActivity" ;
    @BindView(R.id.main_navigation_bar)
    public BottomNavigationView mNavigationView;
    private NewBookFragment newBookFragment;
    private BookListFragment bookListFragment;
    private PersonFragment personFragment;
    private FragmentManager fm;
    private Unbinder unbinder;
    private SharedPreferencesUtil check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_acitivity);
        unbinder = ButterKnife.bind(this);

        initFragments();
        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();



    }

//
//    @Override
//    protected void onStop() {
//        super.onStop();
//
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (unbinder!=null){
//            unbinder.unbind();
//        }
    }

    private void initFragments() {
        newBookFragment = new NewBookFragment();
        bookListFragment = new BookListFragment();
        personFragment = new PersonFragment();
        fm = getSupportFragmentManager();
        switchFragment(newBookFragment);
        check = SharedPreferencesUtil.getInstance(getApplicationContext());
    }

    private void initListener() {
    mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.book_new:
             switchFragment(newBookFragment);
                    break;
                case R.id.book_list:
                    switchFragment(bookListFragment);
                    break;
                case R.id.person:

                    if (check.isLogin())
                    switchFragment(personFragment);
                    else {
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    break;

            }

            return true;
        }
    });
    }

    public void switchFragment(BaseFragment targetFragment) {
       FragmentTransaction fragmentTransaction = fm.beginTransaction();
       fragmentTransaction.replace(R.id.book_new_container,targetFragment);
       fragmentTransaction.commit();

    }


}