package com.example.plucky.mytree.fragment.profile;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.plucky.mytree.AvatarImageView;
import com.example.plucky.mytree.R;
import com.example.plucky.mytree.chart.PairData;
import com.example.plucky.mytree.chart.SemiAnnualChart;
import com.github.mikephil.charting.charts.LineChart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

public class ProfileFragment extends Fragment {
    private AvatarImageView mImageView;
    private Button takePhoto;
    private Button chooseFromAlbum;
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;
    private UsersManager mUsersManager;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.me_fragment, container, false);
        mUsersManager = new UsersManager(getActivity());

        View v1 = v.findViewById(R.id.chart1_part);
        LineChart LineChart = (LineChart) v1.findViewById(R.id.chart1);
        PairData[] dataObjects = {new PairData(0, 30), new PairData(1, 99), new PairData(2, 22),
                new PairData(3, 44), new PairData(4, 35), new PairData(5, 60), new PairData(6, 80)};

        SemiAnnualChart wkChart = new SemiAnnualChart(LineChart, dataObjects);
        wkChart.setData();
        wkChart.drawChart();


        View vv = v.findViewById(R.id.header_part);
        mImageView = (AvatarImageView) vv.findViewById(R.id.head_icon);

        User UserOnline=SearchUserOnline();
            String isimage = UserOnline.getPhoto();
            if(isimage==null){
                mImageView.setImageResource(R.drawable.me);
            }
            else{
                Bitmap bitmap=BitmapFactory.decodeFile(isimage);
                mImageView.setImageBitmap(bitmap);
            }

        final View contentView = inflater.inflate(R.layout.pop_up_window, container, false);
        final PopupWindow popupWindow = new PopupWindow(contentView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setAnimationStyle(R.style.take_photo_anim);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto = (Button) contentView.findViewById(R.id.takephoto);
                chooseFromAlbum = (Button) contentView.findViewById(R.id.frompicture);

                popupWindow.showAtLocation(contentView, Gravity.CENTER_HORIZONTAL, 10, 10);

                contentView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        int height = contentView.findViewById(R.id.background).getTop();
                        int y = (int) event.getY();
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (y < height) {
                                popupWindow.dismiss();
                            }
                        }
                        return true;
                    }
                });

                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        File outputImage = new File(getActivity().getExternalCacheDir(), "output_image.jpg");
                        try {
                            if (outputImage.exists()) {
                                outputImage.delete();
                            }
                            outputImage.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (Build.VERSION.SDK_INT >= 24) {
                            imageUri = FileProvider.getUriForFile(getActivity(), "com.example.plucky.mytree.fileprovider", outputImage);
                        } else {
                            imageUri = Uri.fromFile(outputImage);
                        }
                        //Code for Start Camera
                        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intent, TAKE_PHOTO);
                        popupWindow.dismiss();
                    }
                });

                chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else {
                            openAlbum();
                        }
                        popupWindow.dismiss();

                    }
                });

            }
        });
        return v;
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(getActivity(), "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        String imagePath="/storage/emulated/0/Android/data/com.example.plucky.mytree/cache/output_image.jpg";
                        //imagePath = imageUri.getPath();
                        //Log.v(TAG,"take photo"+imagePath);
                        User UserOnline=SearchUserOnline();
                        UserOnline.setPhoto(imagePath);
                        mUsersManager.updateUser(UserOnline);
                        Bitmap mBitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        mImageView.setImageBitmap(mBitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data);

                    } else {
                        handleImageBeforeKitKat(data);

                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath=null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(getActivity(), uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        User UserOnline=SearchUserOnline();
        UserOnline.setPhoto(imagePath);
        Log.v(TAG,"from album"+imagePath);
        mUsersManager.updateUser(UserOnline);
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        User UserOnline=SearchUserOnline();
        UserOnline.setPhoto(imagePath);
        Log.v(TAG,"from album"+imagePath);
        mUsersManager.updateUser(UserOnline);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getActivity().getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap mBitmap = BitmapFactory.decodeFile(imagePath);
            mImageView.setImageBitmap(mBitmap);
        } else {
            Toast.makeText(getActivity(), "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private User SearchUserOnline() {
        List<User> users = mUsersManager.getUsers();
        User muser=null;
        int length = users.size();
        for(int i=0;i<length;i++){
            if(users.get(i).getStatus()==1){
                muser=users.get(i);
            }
        }
        return muser;
    }

}


