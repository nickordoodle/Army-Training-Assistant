package com.example.machinegunrange;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.net.HttpCookie;

public class Utilities {

    public static Uri getRawUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator
                + "/raw/" + filename);
    }
    public Uri getDrawableUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MainActivity.PACKAGE_NAME + "/drawable/" + filename);
    }
    public Uri getMipmapUri(String filename) {
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + File.pathSeparator + File.separator + MainActivity.PACKAGE_NAME + "/mipmap/" + filename);
    }

    public static void sendEmailWithDialog(final Activity activity, final Context context){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        final EditText emailET = new EditText(context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(emailET);

        // set dialog message
        alertDialogBuilder.setCancelable(true).setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                final String email = emailET.getText().toString();

                //ensure a value is entered
                if (email.matches("")) {
                    Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show();
                } else {
                    //send email based on users email
                    new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            //TODO HIGHEST PRIORITY ENCAPSULATE EMAIL CLIENT LOGIN INFO
                            MailSender sender = new MailSender("MY_EMAIL",
                                    "PASSWORD");
                            sender.sendMail("Hello from JavaMail", "Body from JavaMail",
                                    "MY_EMAIL", email);
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();

            }
        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

}
