package edu.slcc.markou.criminalintent;

import android.content.Intent;
import java.io.File;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class CrimeListFragment extends ListFragment
{
    private ArrayList<Crime> crimes;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Log.d("++++++++++++++",  " FragmentList onCreate");
        getActivity().setTitle(R.string.crimes_title);
        crimes = CrimeLab.get(getActivity()).getCrimes();
        CrimeAdapter adapter = new CrimeAdapter(crimes);
        setListAdapter(adapter);
    }

    private class CrimeAdapter
            extends ArrayAdapter<Crime>
    {
        public CrimeAdapter(ArrayList<Crime> crimes)
        {
            super(   getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
// If we weren't given a view, inflate one
            if (convertView == null)
            {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
            }
// Configure the view for this Crime
            Crime c = getItem(position);
            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());
            return convertView;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Crime c = ((CrimeAdapter)getListAdapter()).getItem(position);

        //Log.d(TAG, c.getTitle() + " was clicked");

        Intent i = new Intent( getActivity(), CrimeActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }




    private boolean load()
    {
        File f = new File("Test.txt");


        try
        {
            if(!f.exists())
                return false;
            ObjectInputStream out = new ObjectInputStream(new FileInputStream(f));
            crimes = ( ArrayList<Crime> ) out.readObject();
        }
        catch( IOException e)
        {
            Log.e("Exception", "load: " + e.getMessage() );
            return false;
        }
        catch( ClassNotFoundException e)
        {
            Log.d("Class not found : ",  e.getMessage() );
            return false;
        }

        return true;
    }
    private void save()
    {
        File f = new File("crimes.cri");


        try
        {
            //if(!f.exists())
              //  f.createNewFile();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            out.writeObject( crimes );
        }
        catch( IOException e)
        {
            Log.e("Exception", "save: " + e.getMessage() );
        }
    }
}