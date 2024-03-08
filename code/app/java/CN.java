package com.example.syntaxsaga;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CN extends AppCompatActivity {
    String[] question_list = {
            "What is the purpose of an IP address?",
            "Which of the following is not a type of network topology?",
            "What protocol is used to retrieve email from a remote server?",
            "What is the maximum data rate of Ethernet?",
            "What layer of the OSI model is responsible for routing?",
            "What is the purpose of a subnet mask?",
            "What is the maximum length of a MAC address?",
            "What does TCP stand for?",
            "What is the function of a router?",
            "What is the difference between UDP and TCP?",
            "Which device is used to connect multiple computers in a network?",
            "What does HTTP stand for?",
            "What is the primary function of ARP (Address Resolution Protocol)?",
            "What is the purpose of DNS (Domain Name System)?",
            "Which of the following is not a type of network cable?",
            "What is the purpose of NAT (Network Address Translation)?",
            "Which of the following is not a valid IP address format?",
            "What is the purpose of a firewall?",
            "What is the maximum range of a typical Wi-Fi router?",
            "What is the role of DHCP in a network?"
    };

    String[] choose_list = {
            "To uniquely identify devices on a network", "To provide physical connectivity", "To regulate network traffic", "To manage network security",
            "Star", "Bus", "Ring", "Mesh",
            "SMTP", "POP3", "HTTP", "FTP",
            "10 Mbps", "100 Mbps", "1 Gbps", "10 Gbps",
            "Network layer", "Transport layer", "Data link layer", "Physical layer",
            "To determine the network portion of an IP address", "To determine the host portion of an IP address", "To encrypt network traffic", "To authenticate network devices",
            "48 bits", "64 bits", "128 bits", "256 bits",
            "Transmission Control Protocol", "Time Critical Protocol", "Transport Control Protocol", "Terminal Configuration Protocol",
            "To connect different network segments", "To store and manage network data", "To filter and forward network traffic", "To authenticate network users",
            "UDP is connectionless, while TCP is connection-oriented", "UDP is faster than TCP", "TCP is used for real-time applications, while UDP is used for file transfer", "TCP provides error-checking and guarantees delivery, while UDP does not",
            "Switch", "Hub", "Router", "Modem",
            "HyperText Transfer Protocol", "High Throughput Transfer Protocol", "Hypertext Terminal Protocol", "Host Transfer Protocol",
            "To map IP addresses to MAC addresses", "To encrypt network traffic", "To resolve domain names to IP addresses", "To assign IP addresses dynamically",
            "To convert domain names to IP addresses", "To manage network security", "To translate public IP addresses to private IP addresses", "To filter network traffic",
            "Coaxial cable", "Fiber-optic cable", "Twisted pair cable", "HDMI cable",
            "To translate private IP addresses to public IP addresses", "To authenticate network users", "To manage network bandwidth", "To filter network traffic",
            "192.168.0.1.1", "172.16.256.1", "10.0.0.0", "255.255.255.0",
            "To manage network security", "To regulate network traffic", "To translate domain names to IP addresses", "To authenticate network users",
            "Around 100 meters", "Around 500 meters", "Around 1 kilometer", "Around 10 kilometers",
            "To assign IP addresses dynamically", "To manage network security", "To authenticate network users", "To regulate network traffic"
    };

    String[] correct_list = {
            "To uniquely identify devices on a network", "Mesh", "POP3", "10 Gbps", "Network layer",
            "To determine the network portion of an IP address", "48 bits", "Transport Control Protocol", "To filter and forward network traffic", "UDP is connectionless, while TCP is connection-oriented",
            "Switch", "HyperText Transfer Protocol", "To map IP addresses to MAC addresses", "To resolve domain names to IP addresses", "HDMI cable",
            "To translate private IP addresses to public IP addresses", "172.16.256.1", "To manage network security", "Around 100 meters",
            "To assign IP addresses dynamically"
    };


    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;


    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setNavigationBarColor(getColor(R.color.green));
        getWindow().setStatusBarColor(getColor(R.color.green));
        setContentView(R.layout.activity_cn);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);

        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);

        findViewById(R.id.image_back).setOnClickListener(
                a-> finish()
        );
        remplirData();
        btn_next.setOnClickListener(
                view -> {
                    if (isclickBtn){
                        isclickBtn = false;

                        if(!valueChoose.equals(correct_list[currentQuestion])){
                            Toast.makeText(CN.this , "incorrect",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                        }else {
                            Toast.makeText(CN.this , "correct",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_correct);

                            scorePlayer++;
                        }
                        new Handler().postDelayed(() -> {
                            if(currentQuestion!=question_list.length-1){
                                currentQuestion = currentQuestion + 1;
                                remplirData();
                                valueChoose = "";
                                btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
                                btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);

                            }else {
                                Intent intent  = new Intent(CN.this , ResulteActivity.class);
                                intent.putExtra("Resute" , scorePlayer);
                                startActivity(intent);
                                finish();
                            }

                        },500);

                    }else {
                        Toast.makeText(CN.this ,  "You must choose one",Toast.LENGTH_LONG).show();
                    }
                }
        );


    }

    void remplirData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);

        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);

    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;

        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose2.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose3.setBackgroundResource(R.drawable.background_btn_choose);
            btn_choose4.setBackgroundResource(R.drawable.background_btn_choose);
        }
        chooseBtn();


    }
    void chooseBtn(){

        btn_click.setBackgroundResource(R.drawable.background_btn_choose_color);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}