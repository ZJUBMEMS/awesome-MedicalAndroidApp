package com.example.zjubme.teethmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DentistDiagnosisActivity extends AppCompatActivity{
    private int[] editName = {R.id.editText_name, R.id.editText_birthday};
    private String[] tabName = {"name", "birthday"};;

    //相册请求码
    private static final int ALBUM_REQUEST_CODE_1 = 1;
    //相机请求码
    private static final int ALBUM_REQUEST_CODE_2= 2;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE_3 = 3;
    //相机请求码
    private static final int ALBUM_REQUEST_CODE_4 = 4;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE_5 = 5;
    //相机请求码
    private static final int ALBUM_REQUEST_CODE_6 = 6;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE_7 = 7;
    //相机请求码
    private static final int ALBUM_REQUEST_CODE_8 = 8;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_1 = 11;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_2 = 12;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_3 = 13;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_4 = 14;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_5 = 15;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_6 = 16;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_7 = 17;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE_8 = 18;

    private ImageButton imageButton_1;
    private ImageButton imageButton_2;
    private ImageButton imageButton_3;
    private ImageButton imageButton_4;
    private ImageButton imageButton_5;
    private ImageButton imageButton_6;
    private ImageButton imageButton_7;
    private ImageButton imageButton_8;


    //调用照相机返回图片文件
    private File tempFile;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dentist_diagnosis_activity);
        hideActionBar();
        setBack();
        searchDataBaseUsers();
        setSubmit();
        imageButton_1 = (ImageButton)findViewById(R.id.imageButton_1);
        imageButton_1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_1);
            }
        });

        imageButton_2 = (ImageButton)findViewById(R.id.imageButton_2);
        imageButton_2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_2);
            }
        });

        imageButton_3 = (ImageButton)findViewById(R.id.imageButton_3);
        imageButton_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_3);
            }
        });

        imageButton_4 = (ImageButton)findViewById(R.id.imageButton_4);
        imageButton_4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_4);
            }
        });
        imageButton_5 = (ImageButton)findViewById(R.id.imageButton_5);
        imageButton_5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_5);
            }
        });

        imageButton_6 = (ImageButton)findViewById(R.id.imageButton_6);
        imageButton_6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_6);
            }
        });

        imageButton_7 = (ImageButton)findViewById(R.id.imageButton_7);
        imageButton_7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_7);
            }
        });

        imageButton_8 = (ImageButton)findViewById(R.id.imageButton_8);
        imageButton_8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE_8);
            }
        });
//               for(int i = 0;i < imageButtonId.length;i++){
//                   ImageButton imageButton = (ImageButton)findViewById(imageButtonId[i]);
//                   imageButton.setOnClickListener(new View.OnClickListener() {
//                       @Override
//                       public void onClick(View v) {
////                           switch (v.getId()) {
////                               case R.id.mGoCamera_btn:
////                                   getPicFromCamera();
////                                   break;
////                               case R.id.mGoAlbm_btn:
////                                   getPicFromAlbm();
////                                   break;
////                               default:
////                                   break;
////                           }
//                           getPicFromAlbm();
//                       }
//                   });
//               }
    }


            //从相机获取图片

//            private void getPicFromCamera() {
//                //用于保存调用相机拍照后所生成的文件
//                tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                    imageUri = FileProvider.getUriForFile(MainActivity.this, "com.example.cameratest.fileprovider", tempFile);
//                }else {
//                    //否则使用Uri.fromFile(file)方法获取Uri
//                    imageUri = Uri.fromFile(tempFile);
//                }
//
//
//                //跳转到调用系统相机
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                //判断版本
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
//            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            Uri contentUri = FileProvider.getUriForFile(MainActivity.this, "com.example.cameratest.fileprovider", tempFile);
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
//            Log.e("dasd", contentUri.toString());
//        } else {    //否则使用Uri.fromFile(file)方法获取Uri
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
//        }
//                startActivityForResult(intent, CAMERA_REQUEST_CODE);
//            }

//            /**
//             * 从相册获取图片
//             */
//            private void getPicFromAlbm() {
//                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
//                photoPickerIntent.setType("image/*");
//                startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
//            }
    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri,int ALBUM_REQUEST_CODE) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        switch(ALBUM_REQUEST_CODE){
            case 1:
                startActivityForResult(intent, CROP_REQUEST_CODE_1);
                break;
            case 2:
                startActivityForResult(intent, CROP_REQUEST_CODE_2);
                break;
            case 3:
                startActivityForResult(intent, CROP_REQUEST_CODE_3);
                break;
            case 4:
                startActivityForResult(intent, CROP_REQUEST_CODE_4);
                break;
            case 5:
                startActivityForResult(intent, CROP_REQUEST_CODE_5);
                break;
            case 6:
                startActivityForResult(intent, CROP_REQUEST_CODE_6);
                break;
            case 7:
                startActivityForResult(intent, CROP_REQUEST_CODE_7);
                break;
            case 8:
                startActivityForResult(intent, CROP_REQUEST_CODE_8);
                break;
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
//            case CAMERA_REQUEST_CODE://调用相机后返回
//                if (resultCode == RESULT_OK) {
//                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        Uri contentUri = FileProvider.getUriForFile(MainActivity.this, "com.example.cameratest.fileprovider", tempFile);
//                        cropPhoto(contentUri);
//                    } else {
//                        cropPhoto(Uri.fromFile(tempFile));
//                    }
//                }
//                break;

            case ALBUM_REQUEST_CODE_1:    //image1调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,1);
                }
                break;
            case ALBUM_REQUEST_CODE_2:    //image2调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,2);
                }
                break;
            case ALBUM_REQUEST_CODE_3:    //image3调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,3);
                }
                break;
            case ALBUM_REQUEST_CODE_4:    //image4调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,4);
                }
                break;
            case ALBUM_REQUEST_CODE_5:    //image5调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,5);
                }
                break;
            case ALBUM_REQUEST_CODE_6:    //image6调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,6);
                }
                break;
            case ALBUM_REQUEST_CODE_7:    //image7调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,7);
                }
                break;
            case ALBUM_REQUEST_CODE_8:    //image8调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri,8);
                }
                break;
            case CROP_REQUEST_CODE_1:     //调用剪裁后返回
                Bundle bundle_1 = intent.getExtras();
                if (bundle_1 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_1.getParcelable("data");
                    //设置到ImageView上
                    imageButton_1.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_2:     //调用剪裁后返回
                Bundle bundle_2 = intent.getExtras();
                if (bundle_2 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_2.getParcelable("data");
                    //设置到ImageView上
                    imageButton_2.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_3:     //调用剪裁后返回
                Bundle bundle_3 = intent.getExtras();
                if (bundle_3 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_3.getParcelable("data");
                    //设置到ImageView上
                    imageButton_3.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_4:     //调用剪裁后返回
                Bundle bundle_4 = intent.getExtras();
                if (bundle_4 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_4.getParcelable("data");
                    //设置到ImageView上
                    imageButton_4.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_5:     //调用剪裁后返回
                Bundle bundle_5 = intent.getExtras();
                if (bundle_5 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_5.getParcelable("data");
                    //设置到ImageView上
                    imageButton_5.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_6:     //调用剪裁后返回
                Bundle bundle_6 = intent.getExtras();
                if (bundle_6 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_6.getParcelable("data");
                    //设置到ImageView上
                    imageButton_6.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_7:     //调用剪裁后返回
                Bundle bundle_7 = intent.getExtras();
                if (bundle_7 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_7.getParcelable("data");
                    //设置到ImageView上
                    imageButton_7.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                }
                break;
            case CROP_REQUEST_CODE_8:     //调用剪裁后返回
                Bundle bundle_8 = intent.getExtras();
                if (bundle_8 != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle_8.getParcelable("data");
                    //设置到ImageView上
                    imageButton_8.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("crop", image);
                    try {
                        upLoadImage(path);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    public String saveImage(String name, Bitmap bmp) {
        //File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        File appDir = new File(Environment.getExternalStorageDirectory(),"bbb");
        Log.i("DiagnosisActivity","directory_pictures="+appDir);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        //String fileName = name + ".jpg";
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void upLoadImage(String path) throws FileNotFoundException {
        AVFile photos = AVFile.withAbsoluteLocalPath("picture1.png", path);
        photos.saveInBackground();
    }



    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    private void setBack(){
        ImageButton back = (ImageButton)findViewById(R.id.self_help_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DentistDiagnosisActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void setSubmit(){
        Button button = (Button)findViewById(R.id.submit_self_help);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "提交成功，请耐心等待医生回复", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DentistDiagnosisActivity.this, Mine.class);
                startActivity(intent);
            }
        });
    }

    private void searchDataBaseUsers(){
        AVQuery<AVObject> query = new AVQuery<>("Users");
        SharedPreferences phone = getSharedPreferences("data", MODE_PRIVATE);
        query.whereEqualTo("phone", phone.getString("phone", ""));
        query.getFirstInBackground(new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if(avObject == null){
                    return;
                }

                if(e == null){
                   for(int i = 0;i < editName.length;i++){
                       EditText editText = (EditText)findViewById(editName[i]);
                       editText.setText(avObject.getString(tabName[i]));
                   }
                }
            }
        });
    }
}
