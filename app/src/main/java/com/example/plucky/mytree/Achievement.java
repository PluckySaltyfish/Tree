package com.example.plucky.mytree;

public class Achievement {
    private int id[]=new int[30];
    private int image[]=new int[30];

    public Achievement(){
        for(int i=0;i<30;i++) id[i]=i+1;

        for(int i=0;i<30;i=i+5)
                image[i]=R.mipmap.tree_one;
        for(int i=1;i<30;i=i+5)
            image[i]=R.mipmap.tree_two;
        for(int i=2;i<30;i=i+5)
            image[i]=R.mipmap.tree_three;
        for(int i=3;i<30;i=i+5)
            image[i]=R.mipmap.tree_four;
        for(int i=4;i<30;i=i+5)
            image[i]=R.mipmap.tree_five;
    }

    public int getId(int i){
        return id[i];
    }
    public int getImage(int i){
        return image[i];
    }

}


