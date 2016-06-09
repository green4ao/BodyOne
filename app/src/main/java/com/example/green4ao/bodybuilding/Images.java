package com.example.green4ao.bodybuilding;

import java.util.ArrayList;

/**
 * Created by green4ao on 6/5/16.
 */
public class Images {
    private ArrayList<Integer> imageIds;
    public Images(){
        imageIds = new ArrayList<>();
        imageIds.add(R.drawable.front_body);
        imageIds.add(R.drawable.back_body);

    }



    public ArrayList<Integer> getImageItem() {
        return imageIds;
    }
}
