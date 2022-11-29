package com.maclaurin.nvh.project_BookFilm;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {
    EditText userName_edt, password_edt;
    Button bt_login, bt_signIn,bt_exit;
    private String userName, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        anhXa();
        controlButton();
    }

    private void controlButton() {
        // setDialog cho button exit
        bt_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeActivity.this, com.google.android.material.R.style.Base_Theme_AppCompat_Dialog_Alert);
                builder.setTitle("Bạn có chắc muốn thoát khỏi app ?");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setIcon(R.drawable.ic_launcher_foreground);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();  // show dialog
            }
        });


        // setDialog cho button đăng kí
        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Dialog dialog = new Dialog(WelcomeActivity.this);
                dialog.setTitle("Hộp thoại xử lý");
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.custom_dialog);
                int rootView = R.layout.custom_dialog;
                EditText userName_signIn = (EditText)dialog.findViewById(R.id.cusDia_name_signIn);
                EditText password_signIn = (EditText)dialog.findViewById(R.id.cusDia_password_signIn);
                Button bt_accept_signIn = (Button)  dialog.findViewById(R.id.cusDia_accept_bt);
                Button bt_cancel_signIn = (Button) dialog.findViewById(R.id.cusDia_cancel_bt);
                bt_accept_signIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userName = userName_signIn.getText().toString().trim();
                        password = password_signIn.getText().toString().trim();
                        userName_edt.setText(userName);
                        password_edt.setText(password);
                        dialog.cancel();
                    }
                });
                bt_cancel_signIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
                Log.i("test_app", "ok");
            }
        });
        // xử lí đăng nhập
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userName_edt.getText().length() != 0 && password_edt.getText().length() != 0){
                    if(userName_edt.getText().toString().equals(userName) && password_edt.getText().toString().equals(password)){
                        Toast.makeText(WelcomeActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent_login = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent_login);
                    }
                    // admin account
                    else if (userName_edt.getText().toString().equals("admin") && password_edt.getText().toString().equals("admin")){
                        Toast.makeText(WelcomeActivity.this,"Đăng nhập thành công!",Toast.LENGTH_SHORT).show();
                        Intent intent_login = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(intent_login);
                    }
                    else {
                        Toast.makeText(WelcomeActivity.this, "Sai mật khẩu hoặc tên đăng nhập!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(WelcomeActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void anhXa() {
        userName_edt = (EditText) findViewById(R.id.user_name);
        password_edt = (EditText) findViewById(R.id.password);
        bt_login = (Button) findViewById(R.id.button_login);
        bt_signIn = (Button) findViewById(R.id.button_signIn);
        bt_exit = (Button) findViewById(R.id.button_exit);
    }
}