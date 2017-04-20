package com.example.gengchunjiang.mzorder_soft.activity.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;


import android.support.v4.app.Fragment;

import android.util.Base64;

import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gengchunjiang.mzorder_soft.R;
import com.example.gengchunjiang.mzorder_soft.activity.activity.AboutUsActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.FeedBackActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.LoginActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.MyBalanceActivity;
import com.example.gengchunjiang.mzorder_soft.activity.activity.ResetPasswordActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * Created by gengchunjiang on 2017/3/15.
 */

public class GerenzhongxinFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button but_zhuxiao, bt_gallery, bt_camera, bt_cancel;
    private RelativeLayout rl_about_us, rl_yijian, rl_xiugai, rl_yu_e;
    private RelativeLayout rl_phone_number;
    private ImageButton ib_avatar;

    private Dialog dialog;

    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gerenzhongxin, container, false);

        initView();

        return view;
    }

    private void initView() {
        but_zhuxiao = (Button) view.findViewById(R.id.but_zhuxiao);
        but_zhuxiao.setOnClickListener(this);
//        but_guanyuwomen = (Button) view.findViewById(R.id.but_guanyuwomen);
//        but_guanyuwomen.setOnClickListener(this);
        rl_about_us = (RelativeLayout) view.findViewById(R.id.rl_about_us);
        rl_about_us.setOnClickListener(this);
        rl_yijian = (RelativeLayout) view.findViewById(R.id.rl_yijian);
        rl_yijian.setOnClickListener(this);
        rl_xiugai = (RelativeLayout) view.findViewById(R.id.rl_xiugai);
        rl_xiugai.setOnClickListener(this);
        rl_yu_e = (RelativeLayout) view.findViewById(R.id.rl_yu_e);
        rl_yu_e.setOnClickListener(this);
        rl_phone_number = (RelativeLayout) view.findViewById(R.id.rl_phone_number);
        rl_phone_number.setOnClickListener(this);
        ib_avatar = (ImageButton) view.findViewById(R.id.ib_avatar);
        ib_avatar.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_zhuxiao:
                getActivity().finish();
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_about_us:
                //跳转到关于我们的activity
                Intent intent2 = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent2);
                break;
            case R.id.rl_yijian:
                //跳转到意见反馈activity
                Intent intent3 = new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent3);
                break;
            case R.id.rl_xiugai:
                Intent intent4 = new Intent(getActivity(), ResetPasswordActivity.class);
                startActivity(intent4);
                break;
            case R.id.rl_yu_e:
                Intent intent5 = new Intent(getActivity(), MyBalanceActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_phone_number:
                break;
            case R.id.ib_avatar:
//                View view= getLayoutInflater().inflate(R.layout.choss,null);
//                Dialog dialog=new Dialog(this,R.style.transparentFrameWindowStyle);
//                dialog.setContentView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//                Window window=dialog.getWindow();
//                //设置显示动画
//                window.setWindowAnimations(R.style.main_menu_animstyle);
//                WindowManager.LayoutParams wl=window.getAttributes();
//                wl.x = 0;
//                wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
//                // 以下这两句是为了保证按钮可以水平满屏
//                wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
//                wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
//                dialog.onWindowAttributesChanged(wl);
//                dialog.setCanceledOnTouchOutside(true);
//                dialog.show();
                View view2 = getActivity().getLayoutInflater().inflate(R.layout.dialog_photo_choose, null);
                dialog = new Dialog(getActivity(), R.style.transparentFrameWindowStyle);
                dialog.setContentView(view2, new LayoutParams(LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT));
                bt_gallery = (Button) view2.findViewById(R.id.bt_gallery);
                bt_gallery.setOnClickListener(this);
                bt_camera = (Button) view2.findViewById(R.id.bt_camera);
                bt_camera.setOnClickListener(this);
                bt_cancel = (Button) view2.findViewById(R.id.bt_cancel);
                bt_cancel.setOnClickListener(this);
                Window window = dialog.getWindow();
                // 设置显示动画
                window.setWindowAnimations(R.style.main_menu_animstyle);
                WindowManager.LayoutParams wl = window.getAttributes();
                wl.x = 0;
                wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                // 以下这两句是为了保证按钮可以水平满屏
                wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
                wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                // 设置显示位置
                dialog.onWindowAttributesChanged(wl);
                // 设置点击外围解散
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
                bt_gallery.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent6 = new Intent();
                        intent6.setType("image/*");
                        intent6.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent6, "Select Pic"), PICK_IMAGE_REQUEST);
                    }
                });
                bt_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent7 = new Intent(); //调用照相机
                        intent7.setAction("android.media.action.STILL_IMAGE_CAMERA");
                        startActivity(intent7);
                    }
                });
                bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //从相册里得到bitmap
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //把bitmap给ImageView
                ib_avatar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


    /**
     * 把Bitmap转换成String
     *
     * @param bitmap
     * @return
     */
    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}
