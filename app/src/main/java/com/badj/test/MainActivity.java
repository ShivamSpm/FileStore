package com.badj.test;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private static String FILE_NAME = "record001";

    EditText mEditText;
    final Context context = this;
    private Button button;
    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = findViewById(R.id.edit_text);
    }

    public void save(View v) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //File root = new File(Environment.getExternalStorageDirectory(), "Notes");
                                FILE_NAME = userInput.getText().toString();
                                String text = mEditText.getText().toString();

                                FileOutputStream fos = null;

                                try {
                                    fos = openFileOutput(FILE_NAME+".txt", MODE_PRIVATE);
                                    fos.write(text.getBytes());

                                    mEditText.getText().clear();
                                    //Toast.makeText(this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (fos != null) {
                                        try {
                                            fos.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();


    }

    public void load(View v) {

        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.prompts, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.editTextDialogUserInput);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                //File root = new File(Environment.getExternalStorageDirectory(), "Notes");
                                FILE_NAME = userInput.getText().toString();
                                Log.d("mihir",FILE_NAME);
                                String text;


                                FileInputStream fis = null;

                                try {
                                    fis = openFileInput(FILE_NAME+".txt");
                                    InputStreamReader isr = new InputStreamReader(fis);
                                    BufferedReader br = new BufferedReader(isr);
                                    StringBuilder sb = new StringBuilder();

                                    while ((text = br.readLine()) != null) {
                                        sb.append(text).append("\n");
                                    }

                                    mEditText.setText(sb.toString());

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (fis != null) {
                                        try {
                                            fis.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
