package com.alissar.cardealershipapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Car implements Parcelable {
    @SerializedName("carId")
    private int carId;

    @SerializedName("manufacturer")
    private String manufacturer;

    @SerializedName("modelName")
    private String modelName;

    @SerializedName("carYear")
    private int carYear;

    @SerializedName("color")
    private String color;

    @SerializedName("carCondition")
    private String carCondition;

    @SerializedName("price")
    private double price;

    @SerializedName("mileage")
    private int mileage;

    // --- Helper Methods for UI ---
    public String getFullName() {
        return manufacturer + " " + modelName;
    }

    public String getFormattedPrice() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(price);
    }

    protected Car(Parcel in) {
        modelName = in.readString();
        price = Double.parseDouble(Objects.requireNonNull(in.readString()));
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modelName);
        dest.writeString(String.valueOf(price));
    }

    // You can generate Getters/Setters here if needed
}