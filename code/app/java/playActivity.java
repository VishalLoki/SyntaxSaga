package com.example.syntaxsaga;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class playActivity extends AppCompatActivity {
    String[] question_list = {
            "What is the primary key in a database table?",
            "Which of the following is not a type of database model?",
            "What does SQL stand for?",
            "Which of the following is not a SQL data manipulation command?",
            "Which normal form deals with the problem of transitive dependencies?",
            "What is the purpose of an index in a database?",
            "Which SQL command is used to retrieve data from a database?",
            "Which type of join returns all rows when there is a match in either table?",
            "What does ACID stand for in the context of database transactions?",
            "Which of the following is a NoSQL database?",
            "What is the purpose of the GROUP BY clause in SQL?",
            "Which of the following is a valid data type in SQL?",
            "Which SQL command is used to make permanent changes to the database?",
            "What does the term 'Normalization' refer to in database design?",
            "Which of the following is not a property of a transaction in a database?",
            "What is a foreign key in a relational database?",
            "Which SQL command is used to remove a table from the database?",
            "What is the purpose of a trigger in a database?",
            "Which of the following is an example of a DDL (Data Definition Language) command?",
            "In a relational database, what is the purpose of the primary key?"
    };

    String[] choose_list = {
            "Unique identifier", "Indexed attribute", "Non-null attribute", "Foreign key",
            "Relational", "Hierarchical", "Network", "Sequential",
            "Standard Query Language", "Structured Query Language", "Systematic Query Language", "Secure Query Language",
            "SELECT", "INSERT", "DELETE", "DEFINE",
            "First Normal Form (1NF)", "Second Normal Form (2NF)", "Third Normal Form (3NF)", "Boyce-Codd Normal Form (BCNF)",
            "To ensure data integrity", "To optimize query performance", "To enforce referential integrity", "To define relationships between tables",
            "UPDATE", "DELETE", "SELECT", "INSERT",
            "INNER JOIN", "LEFT JOIN", "RIGHT JOIN", "FULL OUTER JOIN",
            "Atomicity, Consistency, Isolation, Durability", "Atomicity, Consensus, Isolation, Durability", "Accuracy, Consistency, Isolation, Durability", "Atomicity, Consistency, Isolation, Dependency",
            "MySQL", "PostgreSQL", "MongoDB", "Oracle",
            "To group rows that have the same values into summary rows", "To sort the results", "To filter the results", "To join tables",
            "ARRAY", "DATE", "RECORD", "SET",
            "COMMIT", "ROLLBACK", "SAVEPOINT", "ALTER",
            "Reducing redundancy and dependency by organizing fields and table of a database", "Increasing redundancy and dependency by organizing fields and tables of a database", "Adding additional fields and tables to a database", "Deleting unnecessary fields and tables from a database",
            "Atomicity", "Concurrency", "Isolation", "Durability",
            "A key that uniquely identifies each record in a table", "A key that refers to another table's primary key", "A key that ensures each record has a unique value", "A key that is not a primary key",
            "REMOVE TABLE", "DELETE TABLE", "DROP TABLE", "ERASE TABLE",
            "To enforce data integrity", "To perform an action automatically when a specified event occurs", "To retrieve data from multiple tables", "To define relationships between tables",
            "SELECT", "UPDATE", "CREATE", "DELETE",
            "To ensure uniqueness of each record", "To enforce referential integrity", "To define relationships between tables", "To optimize query performance"
    };

    String[] correct_list = {
            "Unique identifier", "Sequential", "Structured Query Language", "DEFINE", "Third Normal Form (3NF)",
            "To optimize query performance", "SELECT", "FULL OUTER JOIN", "Atomicity, Consistency, Isolation, Durability", "MongoDB",
            "To group rows that have the same values into summary rows", "DATE", "COMMIT", "Reducing redundancy and dependency by organizing fields and tables of a database", "Concurrency", "A key that refers to another table's primary key", "DROP TABLE", "To perform an action automatically when a specified event occurs", "CREATE", "To ensure uniqueness of each record"
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
        setContentView(R.layout.activity_play);
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
                            Toast.makeText(playActivity.this , "incorrect",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                        }else {
                            Toast.makeText(playActivity.this , "correct",Toast.LENGTH_LONG).show();
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
                                Intent intent  = new Intent(playActivity.this , ResulteActivity.class);
                                intent.putExtra("Resute" , scorePlayer);
                                startActivity(intent);
                                finish();
                            }

                        },500);

                    }else {
                        Toast.makeText(playActivity.this ,  "You must choose one",Toast.LENGTH_LONG).show();
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