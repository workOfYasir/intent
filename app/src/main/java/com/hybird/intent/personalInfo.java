package com.hybird.intent;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.FirebaseFirestore;
public class personalInfo implements Parcelable{

    private FirebaseFirestore objectFirebaseFirestore;
    private static final String COLLECTION_NAME="personalInfo";

    private int roll;
    private String name;
    //private String address;
    private Record personalData;


    public personalInfo() {
    }


    protected personalInfo(Parcel in) {
        personalData = in.readParcelable(Record.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(personalData, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<personalInfo> CREATOR = new Creator<personalInfo>() {
        @Override
        public personalInfo createFromParcel(Parcel in) {
            return new personalInfo(in);
        }

        @Override
        public personalInfo[] newArray(int size) {
            return new personalInfo[size];
        }
    };

    public void setPersonalData(Record personalData){
        this.personalData=personalData;
    }
    public Record getPersonalData()
    {
        return personalData;
    }

}
