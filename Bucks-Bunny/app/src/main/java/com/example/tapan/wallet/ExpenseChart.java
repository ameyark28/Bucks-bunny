package com.example.tapan.wallet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ExpenseChart extends AppCompatActivity {
    final ThreadLocal<ImageButton> lvOne=new ThreadLocal<ImageButton>(){
        @SuppressLint("WrongViewCast")
        @Override
        protected ImageButton initialValue() {
            return (ImageButton) findViewById(R.id.lvOne);
        }
    };
    final ThreadLocal<ImageButton> lvTwo=new ThreadLocal<ImageButton>(){
        @SuppressLint("WrongViewCast")
        @Override
        protected ImageButton initialValue() {
            return (ImageButton) findViewById(R.id.lvTwo);
        }
    };
    final ThreadLocal<ImageButton> lvThree=new ThreadLocal<ImageButton>(){
        @SuppressLint("WrongViewCast")
        @Override
        protected ImageButton initialValue() {
            return (ImageButton) findViewById(R.id.lvThree);
        }
    };
    final ThreadLocal<ImageButton> lvFour=new ThreadLocal<ImageButton>(){
        @SuppressLint("WrongViewCast")
        @Override
        protected ImageButton initialValue() {
            return (ImageButton) findViewById(R.id.lvFour);
        }
    };
    final ThreadLocal<ImageButton> lvFive=new ThreadLocal<ImageButton>(){
        @SuppressLint("WrongViewCast")
        @Override
        protected ImageButton initialValue() {
            return (ImageButton) findViewById(R.id.lvFive);
        }
    };



    private TextView txtOne;
    private TextView txtTwo;
    private TextView txtThree;
    private TextView txtFour;
    private TextView txtFive;



    private TextView lvparent;
    private TextView txtinfo;


    private final AppCompatActivity activity = new AppCompatActivity();
    String[] name = new String[]{"Food", "Health", "Clothing", "Travel","Others"};
    int[] percentage = new int[]{55, 25, 10, 5, 5};

    @SuppressLint("WrongViewCast")
    public void onCreate(Bundle savesInstanceState) {
        super.onCreate(savesInstanceState);
        setContentView(R.layout.expense_chart);
        Intent intent = getIntent();


       // final TextView txtinfo;

        TextView txtOne, txtTwo, txtThree, txtFour, txtFive;

        final Uri[] outputFileUri = new Uri[1];
        final OutputStream[] outStream = {null};

        //Personaldb pdb = new Personaldb(activity);


        txtinfo = (TextView) findViewById(R.id.txtinfo);
        final PieView pieView = (PieView) findViewById(R.id.pie_view);

       /* lvOne = get().findViewById(R.id.lvOne);
        lvTwo = findViewById(R.id.lvTwo);
        lvThree = findViewById(R.id.lvThree);
        lvFour = findViewById(R.id.lvFour);
        lvFive = findViewById(R.id.lvFive);
        lvparent = findViewById(R.id.lvparent);*/


        txtOne = (TextView) findViewById(R.id.txtOne);
        txtTwo = (TextView) findViewById(R.id.txtTwo);
        txtThree = (TextView) findViewById(R.id.txtThree);
        txtFour = (TextView) findViewById(R.id.txtFour);
        txtFive = (TextView) findViewById(R.id.txtFive);


        Button mail = (Button) findViewById(R.id.mail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.mail)
                    process(v);
            }

            public void process(View view) {
                Intent intent = null, chooser = null;

                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to = {"prakrutichandak@gmail.com", "mali.akshada@sitpune.edu.in"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "HEY I HAVE SENT THIS THROUGH THE APP");
                intent.setType("message/rfc822");
                chooser = Intent.createChooser(intent, "Send Email");
                startActivity(chooser);
            }
        });
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveImage();
            }

            public void SaveImage() {
                lvparent.buildDrawingCache();
                Bitmap bm = lvparent.getDrawingCache();

                try {
                    File root = new File(Environment.getExternalStorageDirectory() + File.separator + "ExpeneSheet" + File.separator);
                    root.mkdirs();
                    File sdImageMainDirectory = new File(root, "expenseChart.jpg");
                    outputFileUri[0] = Uri.fromFile(sdImageMainDirectory);
                    outStream[0] = new FileOutputStream(sdImageMainDirectory);
                } catch (Exception e) {
                    Toast.makeText(activity, "Error occured. Please try again later.", Toast.LENGTH_SHORT).show();
                }

                try {
                    bm.compress(Bitmap.CompressFormat.PNG, 100, outStream[0]);
                    outStream[0].flush();
                    outStream[0].close();
                    Toast.makeText(activity, "Created successfully!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }
            }
        });
       // set(pieView);
    }

    private void set(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();

        int color0 = Color.rgb(0, 128, 255);
        int color1 = Color.rgb(252, 3, 71);
        int color2 = Color.rgb(117, 91, 4);
        int color3 = Color.rgb(3, 7, 173);
        int color4 = Color.rgb(20, 156, 82);

        pieHelperArrayList.add(new PieHelper(55, color0));
        pieHelperArrayList.add(new PieHelper(25, color1));

        pieHelperArrayList.add(new PieHelper(10, color2));

        pieHelperArrayList.add(new PieHelper(5, color3));

        pieHelperArrayList.add(new PieHelper(5, color4));

        lvOne.get().setBackgroundColor(color0);
        txtOne.setText("Food");
        lvTwo.get().setBackgroundColor(color1);
        txtTwo.setText("Health");
        lvThree.get().setBackgroundColor(color2);
        txtThree.setText("Clothing");
        lvFour.get().setBackgroundColor(color3);
        txtFour.setText("Travel");
        lvFive.get().setBackgroundColor(color4);
        txtFive.setText("Others");

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override
            public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    txtinfo.setText(percentage[index] + "% owns "+ name[index] + ".");
                } else {
                    txtinfo.setText("No selected pie");
                }
            }
        });
    }
}

