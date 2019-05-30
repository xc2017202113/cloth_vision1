package com.example.xuchen.vision1;


import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.threed.jpct.Object3D;

import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public class model_show extends AppCompatActivity {

    public final static int MSG_LOAD_MODEL_SUC = 0;
    private GLSurfaceView myGLView;
    private RenderView myRenderer;
    private Button btnLoad;
    private Button btnLeft;
    private Button btnRight;
    private Button btnTop;
    private Button btnDown;
    private static final String TAG = "model_show";
    private Thread threadLoadModel;
    public static Handler handler;
    private Button btnNew;
    private Button btnChange;
    private Button btnSave;
    private List<Cloth>clothList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_show);
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                Log.d(TAG, "model_show:handleMessage: " + msg.what);
                switch (msg.what) {
                    case MSG_LOAD_MODEL_SUC:
                        Toast.makeText(model_show.this, "模型加载成功", Toast.LENGTH_SHORT).show();
                        Object3D object3D = (Object3D) msg.obj;
                        myRenderer.myWorld.addObject(object3D);
                        break;
                }
            }

        };
        btnLoad = findViewById(R.id.btnLoadModel);
//        btnLeft = findViewById(R.id.btnLeft);
//        btnRight = findViewById(R.id.btnRight);
//        btnTop = findViewById(R.id.btnTop);
//        btnDown = findViewById(R.id.btnDown);
        btnNew = findViewById(R.id.btnNew);
        btnChange = findViewById(R.id.btnChange);
        btnSave = findViewById(R.id.btnSave);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(model_show.this, "开始加载模型", Toast.LENGTH_SHORT).show();
                threadLoadModel.start();
            }
        });
//        btnLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRenderer.applyTranslation(-10, 0, 0);
//            }
//        });
//        btnRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRenderer.applyTranslation(10, 0, 0);
//            }
//        });
//        btnTop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRenderer.applyTranslation(0, -10, 0);
//            }
//        });
//        btnDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRenderer.applyTranslation(0, 10, 0);
//            }
//        });
        myGLView = (GLSurfaceView) this.findViewById(R.id.surfaceView);
        myGLView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        myGLView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
        myGLView.setZOrderOnTop(true);
        myRenderer = new RenderView(this);
        myGLView.setRenderer(myRenderer);
        threadLoadModel = new Thread(new Runnable() {
            @Override
            public void run() {
                myRenderer.addObject(model_show.this);
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        btnChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });

        initCloth();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter=new MyAdapter(clothList);
        recyclerView.setAdapter(adapter);
    }

    private void initCloth(){
            Cloth c1=new Cloth("cloth1",R.drawable.c1);
            clothList.add(c1);
            Cloth c2=new Cloth("cloth2",R.drawable.c2);
            clothList.add(c2);
            Cloth c3=new Cloth("cloth3",R.drawable.c3);
            clothList.add(c3);
            Cloth c4=new Cloth("cloth1",R.drawable.c1);
            clothList.add(c4);
            Cloth c5=new Cloth("cloth2",R.drawable.c2);
            clothList.add(c5);
            Cloth c6=new Cloth("cloth3",R.drawable.c3);
            clothList.add(c6);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myGLView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myGLView.onResume();
    }

}

