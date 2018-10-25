package com.example.jarvis.dbtest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button CreateDB_btn;
    private Button UpgradeDB_btn;
    private Button DeleteDB_btn;
    private Button InsertDT_btn;
    private Button DeleteDT_btn;
    private Button ModifyDT_btn;
    private Button QueryDT_btn;
    private ListView QueryDT_List;
    private ListView Operation_List;
    private ArrayAdapter<String> Data_Adapter;
    private ArrayAdapter<String> Operation_Adapter;
    List<String> DBLIST=new ArrayList<String >();
    List<String> OperationList=new ArrayList<String >();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateDB_btn=(Button)findViewById(R.id.CREATE_DB);
        UpgradeDB_btn=(Button)findViewById(R.id.UPGRADE_DB);
        DeleteDB_btn=(Button)findViewById(R.id.DELETE_DB);
        InsertDT_btn=(Button)findViewById(R.id.INSERT_DATA);
        DeleteDT_btn=(Button)findViewById(R.id.DELETE_DATA);
        ModifyDT_btn=(Button)findViewById(R.id.MODIFY_DATA);
        QueryDT_btn=(Button)findViewById(R.id.QUERY_DATA);
        QueryDT_List=(ListView)findViewById(R.id.QUERY_DATA_List);
        Operation_List=(ListView)findViewById(R.id.Operation_Recode);

        CreateDB_btn.setOnClickListener(this);
        UpgradeDB_btn.setOnClickListener(this);
        DeleteDB_btn.setOnClickListener(this);
        InsertDT_btn.setOnClickListener(this);
        DeleteDT_btn.setOnClickListener(this);
        ModifyDT_btn.setOnClickListener(this);
        QueryDT_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.CREATE_DB:
                MySQLiteOpenHelper DBHELPER1= new MySQLiteOpenHelper(this);
                SQLiteDatabase DB1=DBHELPER1.getWritableDatabase();
                OperationList.add("创建数据库及表格");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB1.close();
                break;
            case R.id.UPGRADE_DB:
                MySQLiteOpenHelper DBHELPER2= new MySQLiteOpenHelper(this,2);
                SQLiteDatabase DB2=DBHELPER2.getWritableDatabase();
                OperationList.add("更新数据库版本，当前版本为：2");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB2.close();
                break;

            case R.id.DELETE_DB:
                MySQLiteOpenHelper DBHELPER3=new MySQLiteOpenHelper(this);
                SQLiteDatabase DB3=DBHELPER3.getWritableDatabase();
                deleteDatabase("TEST.db");
                OperationList.add("删除数据库：TEST.db");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB3.close();
                break;

            case R.id.INSERT_DATA:
                MySQLiteOpenHelper DBHELPER4=new MySQLiteOpenHelper(this);
                SQLiteDatabase DB4=DBHELPER4.getWritableDatabase();
                ContentValues VALUE1=new ContentValues();
                VALUE1.put("id",1);
                VALUE1.put("name","David");
                DB4.insert("user",null,VALUE1);
                VALUE1.put("id",2);
                VALUE1.put("name","Steve");
                DB4.insert("user",null,VALUE1);
                VALUE1.put("id",3);
                VALUE1.put("name","Jarvis");
                DB4.insert("user",null,VALUE1);
                OperationList.add("插入数据");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB4.close();
                break;

            case R.id.DELETE_DATA:
                MySQLiteOpenHelper DBHELPER5=new MySQLiteOpenHelper(this);
                SQLiteDatabase DB5=DBHELPER5.getWritableDatabase();
                DB5.delete("user","id=?",new String []{"1"});//删除数据中id=1的数据
                OperationList.add("删除数据：id=1");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB5.close();
                break;

            case R.id.MODIFY_DATA:
                MySQLiteOpenHelper DBHELPER6=new MySQLiteOpenHelper(this);
                SQLiteDatabase DB6=DBHELPER6.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("name","CC");
                DB6.update("user",values,"id=?",new String []{"2"});
                OperationList.add("修改数据：id=2的行，name变为CC");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB6.close();
                break;

            case R.id.QUERY_DATA:
                MySQLiteOpenHelper DBHELPER7=new MySQLiteOpenHelper(this);
                SQLiteDatabase DB7=DBHELPER7.getReadableDatabase();
                Cursor cursor = DB7.query("user", new String[] { "id","name" },
                        null, null, null, null, null);
                DBLIST.removeAll(DBLIST);
                while (cursor.moveToNext()) {
                    DBLIST.add("id:"+cursor.getString(0)+"  name:"+cursor.getString(1));
                }
                if(cursor!=null&&!cursor.isClosed()){
                    cursor.close();
                }
                Data_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,DBLIST);
                QueryDT_List.setAdapter(Data_Adapter);
                OperationList.add("查询数据，结果在右侧");
                Operation_Adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,OperationList);
                Operation_List.setAdapter(Operation_Adapter);
                DB7.close();
                break;
        }
    }
}
