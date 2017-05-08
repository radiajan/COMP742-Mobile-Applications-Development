package com.cornell.air.a10ants.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.internal.ParcelableSparseArray;

/**
 * Created by root on 8/05/17.
 */

public class Property implements Parcelable {

    private String name;
    private String Address;
    private String Description;

    public Property(){

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    // Parcelling part
    public Property(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);

        // the order needs to be the same as in writeToParcel() method
        setName(data[0]);
        setAddress(data[1]);
        setDescription(data[2]);
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.name,
                this.Address,
                this.Description
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        public Property[] newArray(int size) {
            return new Property[size];
        }
    };
}
