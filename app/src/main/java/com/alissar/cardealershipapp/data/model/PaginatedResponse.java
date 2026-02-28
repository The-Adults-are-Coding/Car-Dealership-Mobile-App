package com.alissar.cardealershipapp.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

// Use Generics <T> so you can reuse this for other lists (e.g., History, Profile)
public class PaginatedResponse<T> {
    @SerializedName("items")
    private List<T> items;

    @SerializedName("hasNext")
    private boolean hasNext;

    @SerializedName("pageNumber")
    private int pageNumber;

    public List<T> getItems() { return items; }
    public boolean hasNext() { return hasNext; }
    public int getPageNumber() { return pageNumber; }
}