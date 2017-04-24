package xyz.codedream.lib.adapter.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;

import xyz.codedream.lib.adapter.HolderAdapter;

/**
 * View Holder
 *
 * @author skg
 */
public abstract class BaseViewHolder<D> {
    protected View rootView;
    protected Context context;
    protected ViewGroup parent;
    protected D data;
    protected int position;
    protected HolderAdapter<D> adapter;

    public BaseViewHolder() {
    }

    protected final void create(Context context, ViewGroup parent) {
        this.context = context;
        this.parent = parent;
        onCreate(context, parent);
    }

    protected abstract void onCreate(Context context, ViewGroup parent);

    protected void setContentView(View v) {
        rootView = v;
    }

    protected void setContentView(int layoutId) {
        setContentView(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    public View findViewById(int id) {
        return rootView.findViewById(id);
    }

    public View findViewByIdAndSetClick(int id, OnClickListener l) {
        View v = findViewById(id);
        v.setOnClickListener(l);
        return v;
    }

    public View getView() {
        return rootView;
    }

    public final View fillData(HolderAdapter<D> adapter, int position, D data) {
        this.adapter = adapter;
        this.position = position;
        this.data = data;
        onDataChanged(position, data);
        return getView();
    }

    protected abstract void onDataChanged(int position, D data);

    public int getPosition() {
        return position;
    }

    public D getData() {
        return data;
    }

    private static void onViewHolderCreate(BaseViewHolder<?> holder, ViewGroup parent) {
        holder.create(parent.getContext(), parent);
        holder.getView().setTag(holder);
    }

    protected HolderAdapter<D> getAdapter() {
        return adapter;
    }

    public void bindClick(View clickView, int position) {
        bindClick(clickView, position, null);
    }

    public void bindClick(View clickView, int position, Integer action) {
        if (adapter == null) {
            throw new RuntimeException("can't bind click before ViewHolder attached to adater");
        }
        adapter.bindClick(clickView, position, action);
    }


    public static BaseViewHolder<?> createHolderByClass(Class<? extends BaseViewHolder> clz, Object clzParent) {
        try {
            Constructor[] cs = clz.getDeclaredConstructors();
            Constructor c = null;
            boolean useClzParent = false;
            for (Constructor csc : cs) {
                Class<?>[] ps = csc.getParameterTypes();
                if (ps == null || ps.length <= 0) {
                    c = csc;
                    useClzParent = false;
                    break;
                } else if (ps.length == 1 && clzParent != null && clzParent.getClass().equals(ps[0])) {
                    c = csc;
                    useClzParent = true;
                    break;
                }
            }
            c.setAccessible(true);
            if (useClzParent) {
                return (BaseViewHolder<?>) c.newInstance(clzParent);
            } else {
                return (BaseViewHolder<?>) c.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T extends BaseViewHolder<?>> T fetch(View convertView, ViewGroup parent, int position, Class<? extends BaseViewHolder> clz, Object clzParent) {
        BaseViewHolder<?> result = null;
        if (convertView == null) {
            try {
                result = createHolderByClass(clz, clzParent);
                onViewHolderCreate(result, parent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            result = (BaseViewHolder<?>) convertView.getTag();
        }
        return (T) result;
    }

    public static <T extends BaseViewHolder<?>> T fetch(View convertView, ViewGroup parent, int position, ViewHolderCreator creator) {
        BaseViewHolder<?> result = null;
        if (convertView == null) {
            try {
                result = creator.createViewHolder(position);
                onViewHolderCreate(result, parent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            result = (BaseViewHolder<?>) convertView.getTag();
        }
        return (T) result;
    }

    public static <T> T fetch(View v) {
        if (v == null) {
            return null;
        }
        Object obj = v.getTag();
        try {
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}