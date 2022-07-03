package com.example.kim00451androidlab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.kim00451androidlab3.DBHelper.*;



import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> datas = new ArrayList<String>();
    EditText et;
    DBHelper dbHelper;
    SQLiteDatabase db;
    Cursor mCursor;
    Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent()
        setContentView(R.layout.activity_chat);

        datas.add(new String("enter"));
        datas.add(new String("enter"));
        datas.add(new String("enter"));


        listView = findViewById(R.id.listView);
        et = findViewById(R.id.chatText);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, datas);

        listView.setAdapter(adapter);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                    selectedPos = position;
                    CharSequence[] items = {"delete", "cancel"};

                    AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                    alertDlg.setTitle("delete?");
                    alertDlg.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch(which) {
                                case 0 :
                                    deletePosition();
                                    break;

                                case 1 :
                                    Toast.makeText(getApplicationContext(), "cnecel", Toast.LENGTH_SHORT).show();
                                    break;
                                default :
                                    break;
                            }
                        }
                    });

                    alertDlg.show();
                    return false;
                }

            };

            // 삭제 메소드
            private void deletePosition() {

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
                alertDlg.setTitle("check deleted");
                alertDlg.setMessage("delete?");

                alertDlg.setPositiveButton("진짜삭제", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        IDNA.Info info = mInfoArray.get(selectedPos);
                        int id = info.id;
                        boolean result = dbHelper.deleteColumn(id);

                        if(result){
                            mInfoArray.remove(selectedPos);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "successed!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDlg.setNegativeButton("cancel", new View.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDlg.show();
            }




        });




    public void buttonSend(View view){
        switch (v.getId()) {
            case R.id.buttonSend:
                // DB에 title, content 삽입
                dbHelper.onUpgrade();
                        et[0].getText().toString().trim();
                );

                et[0].setText("");
                adapter.notifyDataSetChanged();
                mCursor.close();
                break;

            default:
                break;
        }
    }


    public void buttonReceive(View view){
        String str = et.getText().toString();
        datas.add(str);
        adapter.notifyDataSetChanged();
        listView.setSelection(datas.size()-1);

        et.setText("");

    }




}

