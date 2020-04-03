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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.crypto.Mac;

import static com.google.common.base.Predicates.equalTo;

public class Utilities {

    private static final String htmlTableRowOpen = "<tr>";
    private static final String htmlTableRowClose = "</tr>";
    private static final String htmlTableDataOpen = "<td>";
    private static final String htmlTableDataClose = "</tr>";
    private static final String htmlTableOpen = "<table>";
    private static final String htmlTableClose = "</table>";

    public static void sendEmailWithDialog(final Activity activity, final Context context, final ArrayList<MachineGunner> data){

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
                                String title = "See your training report by Army Training Assistant";
                                String body = htmlGenerator(data, context);
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

    public static String htmlGenerator(ArrayList<MachineGunner> data, Context context){

        ArrayList<MachineGunner> sortedData = new ArrayList<MachineGunner>();
        sortedData.addAll(data);
        //Last name sorter
        Comparator<MachineGunner> compareByLastName = (MachineGunner o1, MachineGunner o2) ->
                o1.getLastName().compareTo( o2.getLastName() );
        Collections.sort(sortedData, compareByLastName);

        String result = "";
        //generate html table to hold our data
        result += htmlTableOpen;

        //build first row of header values
        result += htmlTableRowOpen +
                    htmlTableDataOpen + context.getString(R.string.html_rank) + htmlTableDataClose +
                    htmlTableDataOpen + context.getString(R.string.html_last_name) + htmlTableDataClose +
                    htmlTableDataOpen + context.getString(R.string.html_first_name) + htmlTableDataClose +
                    htmlTableDataOpen + context.getString(R.string.html_weapon_system) + htmlTableDataClose +
                    htmlTableDataOpen + context.getString(R.string.html_score) + htmlTableDataClose +
                    htmlTableDataOpen + context.getString(R.string.html_battalion) + htmlTableDataClose +
                    htmlTableDataOpen + context.getString(R.string.html_company) + htmlTableDataClose +
                htmlTableClose;

        //concatenate data for each trainee by row
        for (MachineGunner currentTrainee : sortedData) {
            result += htmlTableRowOpen +
                    htmlTableDataOpen + currentTrainee.getRank() + htmlTableDataClose +
                    htmlTableDataOpen + currentTrainee.getLastName() + htmlTableDataClose +
                    htmlTableDataOpen + currentTrainee.getFirstName() + htmlTableDataClose +
                    htmlTableDataOpen + currentTrainee.getWeaponSystem() + htmlTableDataClose +
                    htmlTableDataOpen + currentTrainee.getScore() + htmlTableDataClose +
                    htmlTableDataOpen + currentTrainee.getBattalion() + htmlTableDataClose +
                    htmlTableDataOpen + currentTrainee.getCompany() + htmlTableDataClose +
                    htmlTableClose;
        }

        //close our table
        result += htmlTableClose;

        return result;
    }

}
