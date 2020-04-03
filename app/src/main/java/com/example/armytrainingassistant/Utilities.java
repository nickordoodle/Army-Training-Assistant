package com.example.armytrainingassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import static com.google.common.base.Predicates.equalTo;

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
                                //TODO MOST LIKELY NEED TO CREATE HTML GENERATOR CLASS
                                InputStream inputStream = context.getAssets().open("htmlTemplate.html");

                                String htmlString = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
                                String title = context.getString(R.string.app_name);
                                String body = "This is Body";
                                //htmlString = htmlString.replace("$title", title);
                                htmlString = htmlString.replace("$body", body);

                                sender.sendMail(title,
                                        htmlString,
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
