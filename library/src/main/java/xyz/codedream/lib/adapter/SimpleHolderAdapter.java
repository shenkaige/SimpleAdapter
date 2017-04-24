package xyz.codedream.lib.adapter;

import java.util.List;

import xyz.codedream.lib.adapter.holder.BaseViewHolder;
import xyz.codedream.lib.adapter.holder.SimpleViewHolderCreator;
import xyz.codedream.lib.adapter.holder.ViewHolderCreator;

/**
 * @param <T>
 * @author skg
 */
public class SimpleHolderAdapter<T> extends HolderAdapter<T> {

    private ViewHolderCreator holder;

    public SimpleHolderAdapter(ViewHolderCreator holder) {
        this.holder = holder;
    }

    public SimpleHolderAdapter(ViewHolderCreator holder, List<T> data) {
        this.holder = holder;
        setData(data, false);
    }

    public SimpleHolderAdapter(Class<? extends BaseViewHolder> clz, Object clzParent) {
        this(new SimpleViewHolderCreator(clz, clzParent));
    }

    public SimpleHolderAdapter(Class<? extends BaseViewHolder> clz, Object clzParent, List<T> data) {
        this(new SimpleViewHolderCreator(clz, clzParent), data);
    }

    @Override
    public BaseViewHolder<?> createViewHolder(int position) {
        return holder.createViewHolder(position);
    }

    @Override
    public int getViewHolderType(int position) {
        return holder.getViewHolderType(position);
    }

    @Override
    public int getViewHolderTypeCount() {
        return holder.getViewHolderTypeCount();
    }

    public static <T> SimpleHolderAdapter<T> adapter(ViewHolderCreator holder) {
        return new SimpleHolderAdapter<T>(holder);
    }

    public static <T> SimpleHolderAdapter<T> adapter(ViewHolderCreator holder, List<T> data) {
        return new SimpleHolderAdapter<T>(holder, data);
    }

    public static <T> SimpleHolderAdapter<T> adapter(Class<? extends BaseViewHolder> clz, Object clzParent) {
        return new SimpleHolderAdapter<T>(clz, clzParent);
    }

    public static <T> SimpleHolderAdapter<T> adapter(Class<? extends BaseViewHolder> clz, Object clzParent, List<T> data) {
        return new SimpleHolderAdapter<T>(clz, clzParent, data);
    }
}