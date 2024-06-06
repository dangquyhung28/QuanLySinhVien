package com.example.quanlysinhvien;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ListView lstViewSV;
    FloatingActionButton btnAdd;
    EditText edtSearch;
    Toolbar toolbar;
    //
    ThiSinhAdapter adapter;
    ArrayList<ThiSinh> thiSinhs =new ArrayList<>();
    MyDBHelper myDBHelper= new MyDBHelper(this);
    @Override
    protected void onStart() {
        super.onStart();
        myDBHelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myDBHelper.closeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.td_tang){
            Collections.sort(thiSinhs, new Comparator<ThiSinh>() {
                @Override
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return Float.compare(o1.tongDiem(), o2.tongDiem());
                }
            });
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Sap xep theo tổng điểm tăng", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId()==R.id.td_giam) {
            Collections.sort(thiSinhs, new Comparator<ThiSinh>() {
                @Override
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return Float.compare(o2.tongDiem(), o1.tongDiem());
                }
            });
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Sap xep theo tổng điểm giảm", Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId()==R.id.sbd_tang) {
            Collections.sort(thiSinhs, new Comparator<ThiSinh>() {
                @Override
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return o1.Sbd.compareTo(o2.Sbd);
                }
            });
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Sap xep theo số báo danh tăng", Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId()==R.id.sbd_giam) {
            Collections.sort(thiSinhs, new Comparator<ThiSinh>() {
                @Override
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return o2.Sbd.compareTo(o1.Sbd);
                }
            });
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Sap xep theo số báo danh giam", Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId()==R.id.dtb_tang) {

            Collections.sort(thiSinhs, new Comparator<ThiSinh>() {
                @Override
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return Float.compare(o1.diemTB(), o2.diemTB());
                }
            });
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Sap xep theo diem tb tăng", Toast.LENGTH_SHORT).show();
            return true;
        }else if (item.getItemId()==R.id.dtb_giam){
            Collections.sort(thiSinhs, new Comparator<ThiSinh>() {
                @Override
                public int compare(ThiSinh o1, ThiSinh o2) {
                    return Float.compare(o2.diemTB(), o1.diemTB());
                }
            });
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Sap xep theo diem tb giam", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return (super.onOptionsItemSelected(item));
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initwiget();
        setSupportActionBar(toolbar);


        displayThiSinh();

        adapter = new ThiSinhAdapter(MainActivity.this, R.layout.items, thiSinhs);
        lstViewSV.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
                startActivity(intent);
            }
        });

        lstViewSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThiSinh selectedThiSinh = thiSinhs.get(position);
                Toast.makeText(MainActivity.this,   selectedThiSinh.HoTen, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
                intent.putExtra("SBD", selectedThiSinh.getSbd());
                intent.putExtra("HoTen", selectedThiSinh.getHoTen());
                intent.putExtra("Toan", selectedThiSinh.getToan());
                intent.putExtra("Ly", selectedThiSinh.getLy());
                intent.putExtra("Hoa", selectedThiSinh.getHoa());
                startActivity(intent);
            }
        });
        lstViewSV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Tạo PopupMenu
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);

                // Inflate (nạp) menu resource vào PopupMenu
                popupMenu.getMenuInflater().inflate(R.menu.dialog_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.menu_item_sua){
                            ThiSinh selectedThiSinh = thiSinhs.get(position);
                            Toast.makeText(MainActivity.this,   selectedThiSinh.HoTen, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ActivityAdd.class);
                            intent.putExtra("SBD", selectedThiSinh.Sbd);
                            intent.putExtra("HoTen", selectedThiSinh.HoTen);
                            intent.putExtra("Toan", selectedThiSinh.Toan);
                            intent.putExtra("Ly", selectedThiSinh.Ly);
                            intent.putExtra("Hoa", selectedThiSinh.Hoa);
                            startActivity(intent);
                            return true;
                        }
                        if(item.getItemId()==R.id.menu_item_xoa){

                            xacnhanxoa(position);
                            adapter.notifyDataSetChanged();
                            lstViewSV.setAdapter(adapter);
                            
                            return true;
                        }
                        return false;
                    }

                });
                return true;
            }
        });

    }

    private void displayThiSinh() {
        myDBHelper.openDB();
        Cursor cursor = myDBHelper.DisplayAll();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            thiSinhs.add(new ThiSinh(cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getSbd())),
                    cursor.getString(cursor.getColumnIndexOrThrow(MyDBHelper.getNAME())),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(MyDBHelper.getToan())),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(MyDBHelper.getLy())),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(MyDBHelper.getHoa()))));
        }
    }

    private void xacnhanxoa(int val) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Thong bao");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có chắc muốn xóa,?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDBHelper.Delete(thiSinhs.get(val).getSbd());
                Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }

    private void initwiget() {
        lstViewSV = (ListView) findViewById(R.id.listView);
        btnAdd = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        edtSearch = (EditText) findViewById(R.id.editTextText);
        toolbar = findViewById(R.id.toolbar2);
    }
}