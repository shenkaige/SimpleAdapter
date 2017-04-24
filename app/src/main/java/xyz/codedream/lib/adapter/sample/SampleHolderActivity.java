package xyz.codedream.lib.adapter.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.codedream.lib.adapter.CommonAdapter;
import xyz.codedream.lib.adapter.CommonAdapter.OnItemClickListener;

/**
 * Created by skg.
 */
public class SampleHolderActivity extends Activity {
    private final ArrayList<Object> mData = new ArrayList<>();
    private final SampleHolderAdapter mAdapter = new SampleHolderAdapter(mData);
    private ListView mListView;
    {
        for (int i = 0; i < 20; i++) {
            mData.add("item " + i);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        mListView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(CommonAdapter<?> adapter, View v, int position, int action) {
            switch (action) {
                case SampleHolderAdapter.CLICK_ACTION_ADD:
                    Toast.makeText(SampleHolderActivity.this, position + " add click:", Toast.LENGTH_SHORT).show();
                    break;
                case SampleHolderAdapter.CLICK_ACTION_UPDATE:
                    Toast.makeText(SampleHolderActivity.this, position + " update click:", Toast.LENGTH_SHORT).show();
                    break;
                case SampleHolderAdapter.CLICK_ACTION_DELETE:
                    Toast.makeText(SampleHolderActivity.this, position + " delete click:", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
