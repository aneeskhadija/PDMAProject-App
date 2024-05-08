package com.anees.pdma.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.anees.pdma.Adapters.UserDatabase_Adapter;
import com.anees.pdma.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import spencerstudios.com.fab_toast.FabToast;

public class Login extends AppCompatActivity {

    UserDatabase_Adapter userDatabaseAdapter = new UserDatabase_Adapter(this);

    EditText edt_username, edt_password;
    CheckBox password_show, password_remember;

    private static final String PREFS_NAME = "preferences";
    private static final String PREF_USERNAME = "Username";
    private static final String PREF_PASSWORD = "Password";

    private final String DefaultUsernameValue = "";
    private String UsernameValue;

    private final String DefaultPasswordValue = "";
    private String PasswordValue;

    String password, encrypted_password;
    public static String username, user_id, name, designation;

    Button btn_login, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_username = (EditText) findViewById(R.id.id_edt_user_name);
        edt_password = (EditText) findViewById(R.id.id_edt_password);
        btn_login = (Button) findViewById(R.id.id_btn_sign_in);
        password_remember = (CheckBox) findViewById(R.id.remember_password_check_box);
        password_show = (CheckBox) findViewById(R.id.show_password_check_box);

        password_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    edt_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edt_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        password_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    savePreferences();
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = edt_username.getText().toString();
                password = edt_password.getText().toString();

                encrypted_password = md5(password);
                if (username.isEmpty()) {
                    FabToast.makeText(getApplicationContext(), "Please enter username", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
                } else if (password.isEmpty()) {
                    FabToast.makeText(getApplicationContext(), "Please enter password", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
                } else {
                    if (username.equals(userDatabaseAdapter.validateUserUsername()) && encrypted_password.equals(userDatabaseAdapter.validateUserPassword())) {
                        if (designation.startsWith("Irrigation")) {
                          //  Intent irrigation_user_login = new Intent(Login.this, IrrigationUserOption.class);
                            FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.success_message), FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();
                        //    startActivity(irrigation_user_login);
                            finish();
                            edt_username.setText("");
                            edt_password.setText("");
                        } else {
                          //  Intent intent_user_login = new Intent(Login.this, UserOptions.class);
                            FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.success_message), FabToast.LENGTH_SHORT, FabToast.SUCCESS,  FabToast.POSITION_DEFAULT).show();
                          //  startActivity(intent_user_login);
                            finish();
                            edt_username.setText("");
                            edt_username.setText("");
                        }
                    } else {
                        FabToast.makeText(getApplicationContext(), getApplicationContext().getString(R.string.error_invalid), FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
                        edt_username.setText("");
                        edt_username.setText("");
                    }
                }

            }
        });
    }

    private static final String md5(final String password) {
        try {

            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void savePreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Edit and commit
        UsernameValue = edt_username.getText().toString();
        PasswordValue = edt_password.getText().toString();
        if (UsernameValue.isEmpty() && PasswordValue.isEmpty())
        {
        }
        else {
            editor.putString(PREF_USERNAME, UsernameValue);
            editor.putString(PREF_PASSWORD, PasswordValue);
            editor.commit();
        }
    }

    private void loadPreferences() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Get value
        UsernameValue = settings.getString(PREF_USERNAME, DefaultUsernameValue);
        PasswordValue = settings.getString(PREF_PASSWORD, DefaultPasswordValue);
        edt_username.setText(UsernameValue);
        edt_password.setText(PasswordValue);
    }
}