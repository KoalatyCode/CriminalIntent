package edu.slcc.markou.criminalintent;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Crime
    implements Serializable
{
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

    private UUID mId;
    private String mTitle;
    private Date date;
    private boolean mSolved;

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_SOLVED, mSolved);
        json.put(JSON_DATE, date.getTime());
        return json;
    }

    public Crime(JSONObject json) throws JSONException
    {
        mId = UUID.fromString(json.getString(JSON_ID));
        if(json.has(JSON_TITLE))
        {
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        date = new Date(json.getLong(JSON_DATE));
    }

    public boolean isSolved()
    {
        return mSolved;
    }

    public void setSolved(boolean mSolved)
    {
        this.mSolved = mSolved;
    }

    public Date getDate()
    {
        return date;
    }


    public void  setDate(Date d)
    {
         date = d;
    }


    public Crime()
    {
            // Generate unique identifier
        mId = UUID.randomUUID();
        date = new Date();
    }
    public UUID getId()
    {
        return mId;
    }
    public String getTitle()
    {
        return mTitle;
    }
    public void setTitle(String title)
    {
        mTitle = title;
    }
    @Override
    public String toString() {
        return mTitle;
    }
}
