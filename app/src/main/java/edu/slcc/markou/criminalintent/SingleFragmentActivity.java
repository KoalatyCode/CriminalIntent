package edu.slcc.markou.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

public abstract class SingleFragmentActivity
        extends FragmentActivity
{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

          if (fragment == null)
          {
            fragment = createFragment();
              Log.d( "1", " insise if onCreate abstact" );
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
          }
    }
}