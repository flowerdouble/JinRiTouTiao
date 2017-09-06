package zhangchaofei.pindao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张超飞 on 2017/9/5.
 */
public class MyAdapter extends BaseAdapter{
    private List<String> list;
    private Context context;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context,R.layout.item,null);
            holder.te=view.findViewById(R.id.tv);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.te.setText(list.get(i));
        return view;
    }
    class ViewHolder{
        TextView te;
    }
}
