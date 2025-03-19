package com.example.androidpythonopencv;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //初始化python环境
        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));
        }


        Log.d("Python", "Python initialized");


        //使用numpy计算两个矩阵的乘积
        Python py = Python.getInstance();

        //调用hello_python.py里面的matrix_multiply函式
        PyObject pyObj = py.getModule("hello_python").get("matrix_multiply");


        //将matrix_multiply计算完的数值，换成java中的float类型

        float[][] result = pyObj.call().toJava(float[][].class);
        String resultStr = "";
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                resultStr += result[i][j] + " ";
            }
            resultStr += "\n";

        }
        Log.d("Result", resultStr);
        Log.d("Python", "Result logged: " + resultStr);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}