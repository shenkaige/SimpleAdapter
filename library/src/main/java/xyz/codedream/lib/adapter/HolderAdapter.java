package xyz.codedream.lib.adapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xyz.codedream.lib.adapter.holder.BaseViewHolder;
import xyz.codedream.lib.adapter.holder.ViewHolderCreator;

/**
 * @author skg
 */
public abstract class HolderAdapter<T> extends CommonAdapter<T> implements ViewHolderCreator {

    public HolderAdapter() {
    }

    public HolderAdapter(List<T> data) {
        setData(data, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder<T> hv = BaseViewHolder.fetch(convertView, parent, position, HolderAdapter.this);
        return hv.fillData(position, getData(position));
    }

    @Override
    public int getViewHolderType(int position) {
        return 0;
    }

    @Override
    public int getViewHolderTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return getViewHolderType(position);
    }

    @Override
    public int getViewTypeCount() {
        return getViewHolderTypeCount();
    }
}
