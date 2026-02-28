package com.alissar.cardealershipapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.text.NumberFormat;
import java.util.Locale;

public class Car implements Parcelable { // Removed Serializable (Parcelable is enough)

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

    // --- 1. Empty Constructor (Needed for Gson sometimes) ---
    public Car() {}

    // --- 2. Parcelable Constructor (Reading data) ---
    // MUST READ IN THE SAME ORDER AS WROTE
    protected Car(Parcel in) {
        carId = in.readInt();
        manufacturer = in.readString();
        modelName = in.readString();
        carYear = in.readInt();
        color = in.readString();
        carCondition = in.readString();
        price = in.readDouble();
        mileage = in.readInt();
    }

    // --- 3. writeToParcel (Saving data) ---
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(carId);
        dest.writeString(manufacturer);
        dest.writeString(modelName);
        dest.writeInt(carYear);
        dest.writeString(color);
        dest.writeString(carCondition);
        dest.writeDouble(price);
        dest.writeInt(mileage);
    }

    @Override
    public int describeContents() {
        return 0;
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

    // --- Getters ---
    public String getFullName() {
        return manufacturer + " " + modelName;
    }

    public String getFormattedPrice() {
        return NumberFormat.getCurrencyInstance(Locale.US).format(price);
    }

    public String getModelName() { return modelName; }
    public String getManufacturer() { return manufacturer; }
    public String getColor() { return color; }
    public String getCondition() { return carCondition; }
    public double getPrice() { return price; }
    public int getCarYear() { return carYear; }
    public int getMileage() { return mileage; }
}