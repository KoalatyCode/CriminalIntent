package edu.slcc.markou.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

public class CrimeListActivity
        extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        Log.d("********************", " createFragment()");
        return new CrimeListFragment();
    }
}