package xyz.codedream.lib.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * @param <Data>
 * @author skg
 */
public abstract class RecyclerHolderAdapter<Data, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<Data> mData;

    public void setData(List<Data> data, boolean notifyDataSetChanged) {
        mData = data;
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public Data getData(int position) {
        if (mData != null && position >= 0 && position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    protected void performItemClick(View itemView, int position) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(itemView, position);
        }
    }

}
