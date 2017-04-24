package xyz.codedream.lib.adapter.holder;

public interface ViewHolderCreator {

    public BaseViewHolder<?> createViewHolder(int position);

    public int getViewHolderType(int position);

    public int getViewHolderTypeCount();
}