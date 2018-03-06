package com.example.plucky.mytree.fragment.profile;

import com.example.plucky.mytree.R;

public class Users {
    private String User[]=new String[10];
    private String Password[]=new String[10];
    private int Image[]=new int[10];
    private int number;
    public Users(){
        User[0]="CXY";
        Password[0]="123456";
        Image[0]= R.drawable.tree_one;

        User[1]="cxy";
        Password[1]="654321";
        Image[1]=R.drawable.tree_two;

        number=2;
    }
    public void addUser(String user, String password, int image){
        User[number]=user;
        Password[number]=password;
        Image[number]=image;
        number=number+1;
    }
    public void deleteUser(String user){
        for(int i=0;i<number;i++){
            if(User[i]==user){
                for(int j=i;j<number-1;j++){
                    User[j]=User[j+1];
                    Password[j]=Password[j+1];
                    Image[j]=Image[j+1];
                    number=number-1;
                }
            }
        }
    }

    public String getPassword(String user) {
        int flag=-1;
        int i=0;
        while(i<number) {
            if (User[i] == user) {
                flag = i;
                break;
            }
            i=i+1;
        }
        if(flag==-1) return null;
        else return Password[flag];
    }

    public int getImage(String user) {
        int flag=-1;
        int i=0;
        while(i<number) {
            if (user.equals(User[i])) {
                flag = i;
                break;
            }
            i=i+1;
        }
        if(flag==-1) return R.drawable.me;
        else return Image[flag];

    }
}
