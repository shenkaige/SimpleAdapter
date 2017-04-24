package xyz.codedream.lib.adapter.holder;

/**
 * @author skg
 */
public class SimpleViewHolderCreator implements ViewHolderCreator {
    private Class<? extends BaseViewHolder> clz;
    private Object clzParent;

    public SimpleViewHolderCreator(Class<? extends BaseViewHolder> clz, Object clzParent) {
        this.clz = clz;
        this.clzParent = clzParent;
    }

    @Override
    public BaseViewHolder<?> createViewHolder(int position) {
        return BaseViewHolder.createHolderByClass(clz, clzParent);
    }

    @Override
    public int getViewHolderType(int position) {
        return 0;
    }

    @Override
    public int getViewHolderTypeCount() {
        return 1;
    }
}