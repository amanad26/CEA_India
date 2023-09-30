package com.example.cea.Apis;

import android.content.Context;

import com.example.cea.Models.CartListModel;

import java.util.List;

public interface RefreshCart {
    List<CartListModel.Datum> onRemove(Context context , String userId);
    void onRefresh();
}
