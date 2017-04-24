package xyz.codedream.lib.adapter.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import xyz.codedream.lib.adapter.CommonAdapter;
import xyz.codedream.lib.adapter.HolderAdapter;
import xyz.codedream.lib.adapter.SimpleHolderAdapter;
import xyz.codedream.lib.adapter.holder.BaseViewHolder;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(simpleHolderAdapter);
    }

    private ArrayList<Object> mData = new ArrayList<>();

    {
        for (int i = 0; i < 20; i++) {
            mData.add("item " + i);
        }
    }

    //原始写法
    private CommonAdapter commonAdapter = new CommonAdapter() {

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
                vh.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.tv.setText(getData(position).toString());
            return null;
        }

        class ViewHolder {
            TextView tv;
        }
    };

    //----end


    //修改Holder写法
    private HolderAdapter<Object> holderAdapter = new HolderAdapter<Object>() {

        @Override
        public BaseViewHolder<?> createViewHolder(int position) {
            return new ViewHolder();
        }

    };

    class ViewHolder extends BaseViewHolder<Object> {
        TextView tv;

        @Override
        protected void onCreate(Context context, ViewGroup parent) {
            setContentView(R.layout.item_test);
            tv = (TextView) findViewById(R.id.tv);
        }

        @Override
        protected void onDataChanged(int position, Object data) {
            tv.setText(getData().toString());
        }
    }

    //--end

    //简单Holder写法
    private SimpleHolderAdapter<Object> simpleHolderAdapter = SimpleHolderAdapter.adapter(ViewHolder.class, this, mData);
    //--end
}
