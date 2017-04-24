package xyz.codedream.lib.adapter.holder;

/**
 * @author skg
 */
public abstract class BaseViewHolderCreator implements ViewHolderCreator {

    @Override
    public int getViewHolderType(int position) {
        return 0;
    }

    @Override
    public int getViewHolderTypeCount() {
        return 0;
    }
}
