package edu.slcc.markou.criminalintent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;

import java.util.UUID;


/**
 * Created by athanasiosmarkou on 3/7/16.
 */
public class CrimeFragment
    extends Fragment
{
    public static final String EXTRA_CRIME_ID =
            "edu.slcc.markou.criminalintent.crime_id";

    private Crime crime;
    private EditText editTextTite;
    private Button buttonDate;
    private CheckBox checkBoxSolved;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        UUID crimeId = (UUID)getArguments().getSerializable( EXTRA_CRIME_ID );
        crime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate( R.layout.fragment_crime, parent, false);
        editTextTite = (EditText) v.findViewById(R.id.crime_title);

        editTextTite.setText(crime.getTitle());

        editTextTite.addTextChangedListener
                (new TextWatcher()
                        {
                            public void onTextChanged(CharSequence c, int start, int before, int count)
                            {
                                crime.setTitle(c.toString());
                            }
                            public void beforeTextChanged(CharSequence c, int start, int count, int after) {}

                            public void afterTextChanged(Editable c) {}
                        });
        buttonDate =  (Button) v.findViewById(R.id.crime_date);
        buttonDate.setText(crime.getDate().toString());
        buttonDate.setEnabled(false);

        checkBoxSolved = (CheckBox)v.findViewById(R.id.crime_solved);
        checkBoxSolved.setChecked(crime.isSolved());
        checkBoxSolved.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener()
                {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                    {
                        crime.setSolved(isChecked);
                    }});
        return v;
    }


    public static CrimeFragment newInstance(UUID crimeId)
    {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;

    }
}
