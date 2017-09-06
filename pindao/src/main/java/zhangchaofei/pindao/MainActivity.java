package zhangchaofei.pindao;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mButton;
    private MySqlite sqlite;
    private SQLiteDatabase db;
    private List<String> list_my;
    private List<String> list_other;
    private View inflate;
    private GridView my_pindao;
    private GridView other_pindao;
    private MyAdapter adapter_my;
    private  MyAdapter adapter_other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        sqlite=new MySqlite(this);
        db=sqlite.getWritableDatabase();
        list_my=new ArrayList<>();
        list_my.add("热点");
        list_my.add("财经");
        list_my.add("科技");
        list_my.add("段子");
        list_my.add("汽车");
        list_my.add("时尚");
        list_my.add("房产");
        list_my.add("彩票");
        list_my.add("独家");
        //其他频道集合
        list_other = new ArrayList<>();
        list_other.add("头条");
        list_other.add("首页");
        list_other.add("娱乐");
        list_other.add("图片");
        list_other.add("游戏");
        list_other.add("体育");
        list_other.add("北京");
        list_other.add("军事");
        list_other.add("历史");
        list_other.add("教育");
    }
    private void initview() {
        mButton= (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        int a=0;
        switch(view.getId()){
            case R.id.button:
                inflate= LayoutInflater.from(MainActivity.this).inflate(R.layout.pindao,null);
                my_pindao=inflate.findViewById(R.id.my_pindao);
                other_pindao=inflate.findViewById(R.id.other_pindao);
                adapter_my=new MyAdapter(this,list_my);
                my_pindao.setAdapter(adapter_my);
                adapter_other = new MyAdapter(this,list_other);
                other_pindao.setAdapter(adapter_other);

                PopupWindow popupWindow=new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                if (a==0){
                    popupWindow.showAsDropDown(mButton);
                    a=1;
                }else if (a==1){
                    popupWindow.dismiss();
                    a=0;
                }
                my_pindao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("PY", "点击了");
                        //获取点击的条目
                        String item2 = (String) adapter_my.getItem(i);
                        Log.d("PY", item2 + i);
                        //添加到数据库
                        ContentValues values = new ContentValues();
                        values.put("title", item2);
                        db.insert("item", null, values);
                        //把点击的这个条目从集合里删除掉并刷新适配器
                        list_my.remove(i);
                        Log.d("PY", "删除" + i);
                        adapter_my.notifyDataSetChanged();
                        Cursor cursor=db.query("item",null,null,null,null,null,null);
                        String string=null;
                        while(cursor.moveToNext()){
                            string=cursor.getString(cursor.getColumnIndex("title"));
                            list_other.add(string);
                            adapter_other.notifyDataSetChanged();

                        }
                        db.delete("item",null,null);

                    }
                });
                other_pindao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //获取点击的条目
                        String item1 = (String) adapter_other.getItem(i);
                        //添加到数据库
                        ContentValues values1 = new ContentValues();
                        values1.put("title", item1);
                        db.insert("item", null, values1);
                        //把点击的这个条目从集合里删除掉并刷新适配器
                        list_other.remove(i);
                        adapter_other.notifyDataSetChanged();
                        //从数据库中查询到刚删除的这个条目
                        Cursor cursor1 = db.query("item", null, null, null, null, null, null);
                        //遍历查询结果
                        String string1 = null;
                        while (cursor1.moveToNext()) {
                            string1 = cursor1.getString(cursor1.getColumnIndex("title"));
                            //把刚才我的频道删除的数据添加到我的频道里
                            list_my.add(string1);
                            adapter_my.notifyDataSetChanged();
                        }
                        db.delete("item",null,null);
                    }

                });
                break;
        }

    }
}
