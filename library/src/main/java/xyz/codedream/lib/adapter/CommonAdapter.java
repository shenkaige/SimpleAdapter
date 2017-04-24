package xyz.codedream.lib.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 简单Adapter
 *
 * @param <Data>
 * @author skg
 */
public abstract class CommonAdapter<Data> extends BaseAdapter {
    protected List<Data> mData;

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (observer != null) {
            super.unregisterDataSetObserver(observer);
        }
    }

    public void setData(List<Data> data, boolean notifyDataSetChanged) {
        mData = data;
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return getData(position);
    }

    public Data getData(int position) {
        if (mData != null && position >= 0 && position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public interface OnItemClickListener {
        public void onItemClick(CommonAdapter<?> adapter, View v, int position, int action);
    }

    public static final int TAG_KEY_POSITION = Integer.MIN_VALUE / 2;
    public static final int TAG_KEY_ACTION = TAG_KEY_POSITION - 1;

    public void bindClick(View clickView, int position) {
        bindClick(clickView, position, null);
    }

    public void bindClick(View clickView, int position, Integer action) {
        clickView.setOnClickListener(onClickListener);
        clickView.setTag(TAG_KEY_POSITION, position);
        clickView.setTag(TAG_KEY_ACTION, action);
    }

    private final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                int positon = (int) v.getTag(TAG_KEY_POSITION);
                Object actionObj = v.getTag(TAG_KEY_ACTION);
                int action = 0;
                if (actionObj != null) {
                    action = (int) actionObj;
                }
                mOnItemClickListener.onItemClick(CommonAdapter.this, v, positon, action);
            }
        }
    };
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }
}
