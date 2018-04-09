package edu.slcc.markou.criminalintent;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;


public class CrimeActivity
        extends SingleFragmentActivity
{

    @Override
    protected Fragment createFragment()
    {

        Log.d("%%%%%%%%%%", " CrimeActivity createFragment");
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);

        return CrimeFragment.newInstance(crimeId);

    }

}
