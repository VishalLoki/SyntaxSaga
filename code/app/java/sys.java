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

public class sys extends AppCompatActivity {
    String[] question_list = {
            "What is the purpose of an assembler?",
            "Which of the following is not a system programming language?",
            "What is the primary function of a linker?",
            "What does the 'make' utility do in system programming?",
            "Which of the following is not an operating system?",
            "What is the purpose of a loader in system programming?",
            "Which type of memory allocation is typically used in system programming?",
            "What is the purpose of an interpreter?",
            "What is the role of system calls in system programming?",
            "Which of the following is not a system programming tool?",
            "What is the function of the preprocessor in C programming?",
            "Which programming language is commonly used for writing device drivers?",
            "What is the purpose of a shell in system programming?",
            "Which of the following is not a standard file descriptor?",
            "What is the role of an operating system kernel?",
            "What is the purpose of a debugger?",
            "What is the primary function of a system compiler?",
            "Which type of programming typically involves low-level hardware interactions?",
            "What is the role of an operating system scheduler?",
            "Which of the following is not a system programming paradigm?"
    };

    String[] choose_list = {
            "To convert assembly language code into machine code", "To manage system resources", "To translate high-level programming languages into machine code", "To manage file systems",
            "Java", "C", "C++", "Assembly",
            "To combine object files into an executable program", "To allocate memory dynamically", "To translate assembly language code into machine code", "To manage system I/O",
            "Automate the build process", "Manage system resources", "Create system backups", "Debug programs",
            "Linux", "Windows", "MacOS", "UNIX",
            "To allocate memory for executing programs", "To interpret high-level language code", "To combine object files into an executable program", "To manage system I/O",
            "Static memory allocation", "Dynamic memory allocation", "Heap memory allocation", "Stack memory allocation",
            "To execute high-level language code", "To manage system resources", "To allocate memory dynamically", "To translate assembly language code into machine code",
            "To provide an interface between user programs and the operating system", "To manage system resources", "To automate system tasks", "To translate high-level language code into machine code",
            "gcc", "gdb", "make", "vi",
            "To expand macros and include header files", "To manage system resources", "To translate high-level language code into machine code", "To manage file systems",
            "Python", "C", "Java", "Assembly",
            "To provide an interface between users and the operating system", "To manage system resources", "To automate system tasks", "To interpret high-level language code",
            "STDIN", "STDOUT", "STDERR", "STDFILE",
            "To manage system resources", "To execute user programs", "To provide a user-friendly interface", "To translate high-level language code into machine code",
            "To find and fix errors in programs", "To manage system resources", "To automate system tasks", "To allocate memory dynamically",
            "To translate high-level language code into machine code", "To allocate memory for executing programs", "To manage system I/O", "To interpret assembly language code",
            "Embedded programming", "Application programming", "Web programming", "Script programming",
            "To manage system resources", "To automate system tasks", "To schedule processes for execution", "To interpret high-level language code",
            "Object-oriented programming", "Imperative programming", "Procedural programming", "Functional programming"
    };

    String[] correct_list = {
            "To convert assembly language code into machine code", "Java", "To combine object files into an executable program", "Automate the build process", "Linux",
            "To allocate memory for executing programs", "Static memory allocation", "To interpret high-level language code", "To provide an interface between user programs and the operating system", "vi",
            "To expand macros and include header files", "C", "To provide an interface between users and the operating system", "STDFILE", "To manage system resources", "To find and fix errors in programs",
            "To translate high-level language code into machine code", "Embedded programming", "To schedule processes for execution", "Web programming", "Object-oriented programming"
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
        setContentView(R.layout.activity_sys);
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
                            Toast.makeText(sys.this , "incorrect",Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.background_btn_erreur);

                        }else {
                            Toast.makeText(sys.this , "correct",Toast.LENGTH_LONG).show();
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
                                Intent intent  = new Intent(sys.this , ResulteActivity.class);
                                intent.putExtra("Resute" , scorePlayer);
                                startActivity(intent);
                                finish();
                            }

                        },500);

                    }else {
                        Toast.makeText(sys.this ,  "You must choose one",Toast.LENGTH_LONG).show();
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