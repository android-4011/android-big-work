package com.example.androidbigwork;


import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.MonthView;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    Button button1,button2,button3;
    ArrayList<View> list;
    MyPagerAdapter adapter;
    TextView textView1,textView2;
    CalendarView calendarView;
    CalendarView calendarView1;
    CalendarView calendarView2;
    FloatingActionButton floatbutton1;
    FloatingActionButton floatbutton2;
    Calendar calendar1;
    EditText editText1;
    //EditText editText2;
    EditText editText_yeat,editText_month,editText_day;
    ConstraintLayout add_agenda;
    TextView textView_agenda;
    SharedPreferences settings;
    Button btn_startspeech;
    private static final String TAG = MainActivity.class .getSimpleName();
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSpeech();
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);


        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button1.setOnClickListener((View.OnClickListener) new button1_Click());//月视图
        button2.setOnClickListener((View.OnClickListener) new button2_Click());//周视图
        button3.setOnClickListener((View.OnClickListener) new button3_Click());//日视图
        floatbutton1=findViewById(R.id.floatingActionButton1);
        floatbutton2=findViewById(R.id.floatingActionButton2);
        editText_yeat=findViewById(R.id.editText_year);
        editText_month=findViewById(R.id.editText_month);
        editText_day=findViewById(R.id.editText_day);


        textView_agenda=findViewById(R.id.textView_agenda);
        textView_agenda.setText("测试textview是否有问题");
        viewPager=findViewById(R.id.vp_main);
        list=new ArrayList<View>();
        LayoutInflater li=getLayoutInflater();
        list.add(li.inflate(R.layout.monthly_view,null,false));
        list.add(li.inflate(R.layout.weekly_view,null,false));
        list.add(li.inflate(R.layout.daily_view,null,false));
        adapter=new MyPagerAdapter(list);
        viewPager.setAdapter(adapter);

        calendarView=list.get(0).findViewById(R.id.calendarView);
        textView2.setText(String.valueOf(calendarView.getCurMonth()));
        textView1.setText(String.valueOf(calendarView.getCurYear()));
        calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        calendarView.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });
        calendarView1=list.get(1).findViewById(R.id.calendarView1);
        calendarView2=list.get(2).findViewById(R.id.calendarView2);
        calendarView1.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        calendarView1.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });
        calendarView2.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
            @Override
            public void onMonthChange(int year, int month) {
                textView2.setText(String.valueOf(month));
            }
        });
        calendarView2.setOnYearChangeListener(new CalendarView.OnYearChangeListener() {
            @Override
            public void onYearChange(int year) {
                textView1.setText(String.valueOf(year));
            }
        });
        floatbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.scrollToCurrent();
                calendarView1.scrollToCurrent();
                calendarView2.scrollToCurrent();
                textView2.setText(String.valueOf(calendarView.getCurMonth()));
                textView1.setText(String.valueOf(calendarView.getCurYear()));
            }
        });
        floatbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                add_agenda=(ConstraintLayout)getLayoutInflater().inflate(R.layout.add_agenda,null);
                btn_startspeech = add_agenda.findViewById(R.id.btn_startspeech );
                editText1=add_agenda.findViewById(R.id.editText1);
                btn_startspeech.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startSpeechDialog();
                    }
                });
                dialog.setTitle("新建日程");
                dialog.setMessage("请输入备注和日期");
                dialog.setView(add_agenda);
                dialog.setPositiveButton("确定",new addClick());
                dialog.setNegativeButton("取消",new cancleClick());
                dialog.create();
                dialog.show();
            }
        });
        calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(Calendar calendar, boolean isClick) {
                calendarView1.scrollToCalendar(calendar.getYear(),calendar.getMonth(),calendar.getDay());
                calendarView2.scrollToCalendar(calendar.getYear(),calendar.getMonth(),calendar.getDay());
                SharedPreferences userSettings= getSharedPreferences("database", 0);
                if(userSettings.contains(String.valueOf(calendar.getYear())+"年"+String.valueOf(calendar.getMonth())+"月"+String.valueOf(calendar.getDay())+"日"))
                {
                    textView_agenda.setText(userSettings.getString(String.valueOf(calendar.getYear())+ "年"+String.valueOf(calendar.getMonth())+"月"+String.valueOf(calendar.getDay())+"日","默认"));
                }
                else
                {
                    textView_agenda.setText("");
                }
            }
        });



    }

    private class button1_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            button1.setTextColor(Color.parseColor("#00BCD4"));
            button2.setTextColor(Color.parseColor("#88000000"));
            button3.setTextColor(Color.parseColor("#88000000"));
            viewPager.setCurrentItem(0,false);
            Toast.makeText(MainActivity.this,"月视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class button2_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            button1.setTextColor(Color.parseColor("#88000000"));
            button2.setTextColor(Color.parseColor("#00BCD4"));
            button3.setTextColor(Color.parseColor("#88000000"));
            viewPager.setCurrentItem(1,false);
            Toast.makeText(MainActivity.this,"周视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class button3_Click implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            button1.setTextColor(Color.parseColor("#88000000"));
            button2.setTextColor(Color.parseColor("#88000000"));
            button3.setTextColor(Color.parseColor("#00BCD4"));
            viewPager.setCurrentItem(2,false);
            Toast.makeText(MainActivity.this,"日视图",Toast.LENGTH_SHORT).show();
        }
    }

    private class addClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {



           //String regex="\\d\\d\\d\\d年\\d\\d月\\d\\d日";
            //if(editText2.getText().toString().matches(regex))
            //{
                editText1=add_agenda.findViewById(R.id.editText1);
                //editText2=add_agenda.findViewById(R.id.editText2);
                settings=getSharedPreferences("database", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=settings.edit();
               // editor.putString(editText2.getText().toString(),editText1.getText().toString());
                editor.commit();
                dialog.dismiss();
            //}
            /*else
            {
                editText2.setText("");
                Toast.makeText(MainActivity.this,"请按照年月日格式输出",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }*/



        }
    }

    private class cancleClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
           dialog.dismiss();
        }
    }
    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility. createUtility( this, SpeechConstant. APPID + "=5e0217ca" );
    }
    private void startSpeechDialog() {
        //1. 创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener()) ;
        //2. 设置accent、 language等参数
        mDialog.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mDialog.setParameter(SpeechConstant. ACCENT, "mandarin" );
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        // 结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener( new MyRecognizerDialogListener()) ;
        //4. 显示dialog，接收语音输入
        mDialog.show() ;
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        /**
         * @param results
         * @param isLast  是否说完了
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString(); //为解析的
            showTip(result) ;
            System. out.println(" 没有解析的 :" + result);

            String text = JsonParser.parseIatResult(result) ;//解析过后的
            System. out.println(" 解析后的 :" + text);

            String sn = null;
            // 读取json结果中的 sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString()) ;
                sn = resultJson.optString("sn" );
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults .put(sn, text) ;//没有得到一句，添加到

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults .get(key));
            }

            editText1.setText(resultBuffer.toString());// 设置输入框的文本
            editText1.setSelection(editText1.length()) ;//把光标定位末尾
        }

        @Override
        public void onError(SpeechError speechError) {

        }
    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败 ");
            }

        }
    }

    /**
     * 语音识别
     */
    private void startSpeech() {
        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer( this, null); //语音识别器
        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
        mIat.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
        mIat.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mIat.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
        //3. 开始听写
        mIat.startListening( mRecoListener);
    }


    // 听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
//一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
//关于解析Json的代码可参见 Demo中JsonParser 类；
//isLast等于true 时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.e (TAG, results.getResultString());
            System.out.println(results.getResultString()) ;
            showTip(results.getResultString()) ;
        }

        // 会话发生错误回调接口
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true)) ;
            // 获取错误码描述
            Log. e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
        }

        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
        }

        // 扩展用接口
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
        }
    };

    private void showTip (String data) {
        Toast.makeText( this, data, Toast.LENGTH_SHORT).show() ;
    }
}



