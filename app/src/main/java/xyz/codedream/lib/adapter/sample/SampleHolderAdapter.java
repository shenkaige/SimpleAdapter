package xyz.codedream.lib.adapter.sample;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import xyz.codedream.lib.adapter.HolderAdapter;
import xyz.codedream.lib.adapter.holder.BaseViewHolder;

/**
 * Created by skg.
 */

public class SampleHolderAdapter extends HolderAdapter<Object> {

    public static final int CLICK_ACTION_ADD = 1;
    public static final int CLICK_ACTION_UPDATE = 2;
    public static final int CLICK_ACTION_DELETE = 3;

    public SampleHolderAdapter(List<Object> data) {
        super(data);
    }

    @Override
    public BaseViewHolder<?> createViewHolder(int position) {
        return new ViewHolder();
    }

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

}
