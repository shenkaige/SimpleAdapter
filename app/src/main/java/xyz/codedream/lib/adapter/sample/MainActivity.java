package xyz.codedream.lib.adapter.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.codedream.lib.adapter.CommonAdapter;
import xyz.codedream.lib.adapter.CommonAdapter.OnItemClickListener;
import xyz.codedream.lib.adapter.HolderAdapter;
import xyz.codedream.lib.adapter.SimpleHolderAdapter;
import xyz.codedream.lib.adapter.holder.BaseViewHolder;

/**
 * 各种Adapter写法对比，单独HolderAdapter使用，请看{@link SampleHolderActivity}
 */
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
                vh.btn1 = (Button) convertView.findViewById(R.id.btn1);
                vh.btn2 = (Button) convertView.findViewById(R.id.btn2);
                vh.btn3 = (Button) convertView.findViewById(R.id.btn3);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.btn1.setText(getData(position).toString());
            vh.btn2.setText(getData(position).toString());
            vh.btn3.setText(getData(position).toString());
            return null;
        }

        class ViewHolder {
            Button btn1, btn2, btn3;
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
        Button btn1, btn2, btn3;

        @Override
        protected void onCreate(Context context, ViewGroup parent) {
            setContentView(R.layout.item_test);
            btn1 = (Button) findViewById(R.id.btn1);
            btn2 = (Button) findViewById(R.id.btn2);
            btn3 = (Button) findViewById(R.id.btn3);
        }

        @Override
        protected void onDataChanged(int position, Object data) {
            btn1.setText("add " + position);
            btn2.setText("update " + position);
            btn3.setText("delete " + position);
            //
            bindClick(btn1, position, CLICK_ACTION_ADD);
            bindClick(btn2, position, CLICK_ACTION_UPDATE);
            bindClick(btn3, position, CLICK_ACTION_DELETE);
        }
    }

    //--end

    //简单Holder写法
    private SimpleHolderAdapter<Object> simpleHolderAdapter = SimpleHolderAdapter.adapter(ViewHolder.class, this, mData);
    //--end

    //设置ItemClick
    static final int CLICK_ACTION_ADD = 1;
    static final int CLICK_ACTION_UPDATE = 2;
    static final int CLICK_ACTION_DELETE = 3;

    {
        simpleHolderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(CommonAdapter<?> adapter, View v, int position, int action) {
                switch (action) {
                    case CLICK_ACTION_ADD:
                        Toast.makeText(MainActivity.this, position + " add click:", Toast.LENGTH_SHORT).show();
                        break;
                    case CLICK_ACTION_UPDATE:
                        Toast.makeText(MainActivity.this, position + " update click:", Toast.LENGTH_SHORT).show();
                        break;
                    case CLICK_ACTION_DELETE:
                        Toast.makeText(MainActivity.this, position + " delete click:", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
