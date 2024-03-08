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

public class datastruct extends AppCompatActivity {
    String[] question_list = {
            "What is the time complexity of searching an element in a binary search tree?",
            "Which data structure is based on the LIFO (Last In, First Out) principle?",
            "What is the worst-case time complexity of inserting an element into a heap?",
            "Which data structure allows you to access elements based on their position?",
            "What is the time complexity of inserting an element into a sorted array with n elements?",
            "Which of the following is not a type of tree data structure?",
            "What is the primary goal of hash tables?",
            "Which sorting algorithm has the best time complexity in the worst case?",
            "What is the time complexity of deleting an element from a linked list?",
            "Which data structure is used in Depth-First Search (DFS) algorithm?",
            "What is the time complexity of searching for an element in a hash table on average?",
            "Which data structure is suitable for implementing a priority queue?",
            "What is the time complexity of searching for an element in a binary search tree on average?",
            "Which of the following is not an application of a stack?",
            "What is the time complexity of merging two sorted arrays of sizes m and n?",
            "Which data structure uses the principle of First In, First Out (FIFO)?",
            "What is the time complexity of finding the minimum element in a binary heap?",
            "Which data structure is used for representing hierarchical data?",
            "What is the time complexity of searching an element in a linked list?",
            "Which of the following is not a balanced tree data structure?"
    };

    String[] choose_list = {
            "O(log n)", "O(n)", "O(n log n)", "O(n^2)",
            "Queue", "Stack", "Heap", "Array",
            "O(log n)", "O(n)", "O(n log n)", "O(1)",
            "Array", "Linked List", "Queue", "Stack",
            "O(n)", "O(log n)", "O(n log n)", "O(1)",
            "Binary Tree", "B-Tree", "Trie", "Linked List",
            "To provide constant-time access to elements", "To minimize collisions", "To minimize memory usage", "To provide constant-time insertion and deletion",
            "Quick Sort", "Merge Sort", "Heap Sort", "Bubble Sort",
            "O(1)", "O(log n)", "O(n)", "O(n^2)",
            "Stack", "Queue", "Heap", "Linked List",
            "O(log n)", "O(n)", "O(n log n)", "O(1)",
            "Binary Search Tree", "Trie", "Graph", "Array",
            "O(n)", "O(log n)", "O(n^2)", "O(1)",
            "Undo operations in text editors", "Expression evaluation", "Backtracking algorithms", "Tower of Hanoi",
            "O(m + n)", "O(m log n)", "O(n log m)", "O(mn)",
            "Stack", "Queue", "Heap", "Linked List",
            "O(1)", "O(log n)", "O(n)", "O(n log n)",
            "Tree", "Graph", "Linked List", "Array",
            "O(1)", "O(log n)", "O(n)", "O(n^2)",
            "AVL Tree", "Red-Black Tree", "Heap", "Trie"
    };

    String[] correct_list = {
            "O(log n)", "Stack", "O(log n)", "Array", "O(n)",
            "Trie", "To provide constant-time access to elements", "Heap Sort", "O(1)", "Stack",
            "O(1)", "Priority Queue", "O(log n)", "Expression evaluation", "O(m + n)",
            "Queue", "O(1)", "Tree", "O(n)", "Trie"
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
        setContentView(R.layout.activity_datastruct);
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
                            Toast.makeText(datastruct.this , "incorrect",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                        }else {
                            Toast.makeText(datastruct.this , "correct",Toast.LENGTH_LONG).show();
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
                                Intent intent  = new Intent(datastruct.this , ResulteActivity.class);
                                intent.putExtra("Resute" , scorePlayer);
                                startActivity(intent);
                                finish();
                            }

                        },500);

                    }else {
                        Toast.makeText(datastruct.this ,  "You must choose one",Toast.LENGTH_LONG).show();
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