package com.example.machinegunrange;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

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
                                String senderEmail = MainActivity.senderEmail;
                                String senderCred = MainActivity.senderCred;
                                MailSender sender = new MailSender(senderEmail,
                                        senderCred);
                                //TODO ADD CURRENT DATA TO EMAIL
                                sender.sendMail("Hello from JavaMail",
                                        "<html><body><tr><td>data 1</td></tr>" +
                                                "<tr><td>data 2</td></tr></body></html>",
                                        senderEmail, email);
                                activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context,
                                                "List emailed to " + email, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                                activity.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context,
                                                "Oops! Email did not send, try again ",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }

}
