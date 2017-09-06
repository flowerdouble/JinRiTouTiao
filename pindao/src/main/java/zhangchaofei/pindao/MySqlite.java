package zhangchaofei.pindao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;


/**
 * Created by 张超飞 on 2017/9/5.
 */
public class MySqlite extends SQLiteOpenHelper{
    public MySqlite(Context context){
        super(context,"item.db",null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table item(id integer primary key autoincrement,title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
