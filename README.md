### HolderAdapter背景

Android的AdapterView用的比较多，ListView，GridView，Spinner等，原生的BaseAdapter对ViewHolder没有支持，
每次都要，定义内部类，inflater根布局，对item内部view设置clicklistener并转发到adapter的调用者等。
写的次数多了感觉很繁琐，于是写了个简单的封装，简化Adapter的编写.

### 主要文件介绍
1，CommonAdapter.java   
从BaseAdapter继承，添加了常用的函数，通过泛型约定了List数据源，代替子类实现getCount功能,添加bindClick实现Adapter的OnItemClickListener。

2，BaseViewHolder.java   
和HolderAdapter结合，实现类似Activity的view初始化方方式onCreate->setContentView->findViewById。

3，HolderAdapter.java   
从CommonAdapter继承，主要用来驱动BaseViewHolder工作。


### 使用方法

**1,Adapter定义**

```java
public class SampleHolderAdapter extends HolderAdapter<Object> {
    //定义Item内部的点击事件，如果不需要可以不定义
    public static final int CLICK_ACTION_ADD = 1;
    public static final int CLICK_ACTION_UPDATE = 2;
    public static final int CLICK_ACTION_DELETE = 3;
    //构造函数配置数据源（可选，或者之后通过setData更新）
    public SampleHolderAdapter(List<Object> data) {
        super(data);
    }

    @Override
    public BaseViewHolder<?> createViewHolder(int position) {
        //创建期望的 ViewHolder
        return new ViewHolder();
    }
    
    class ViewHolder extends BaseViewHolder<Object> {
        Button btn1, btn2, btn3;

        @Override
        protected void onCreate(Context context, ViewGroup parent) {
            //ViewHolder创建后onCreate会被执行1次
            setContentView(R.layout.item_test);//setContent支持View或者layout id
            btn1 = (Button) findViewById(R.id.btn1);//findViewById
            btn2 = (Button) findViewById(R.id.btn2);
            btn3 = (Button) findViewById(R.id.btn3);
        }

        @Override
        protected void onDataChanged(int position, Object data) {
            //adapter getView的时候onDataChanged会被执行一次
            btn1.setText("add " + position);
            btn2.setText("update " + position);
            btn3.setText("delete " + position);
            //
            bindClick(btn1, position, CLICK_ACTION_ADD);//绑定ItemClick，每次都需bind
            bindClick(btn2, position, CLICK_ACTION_UPDATE);
            bindClick(btn3, position, CLICK_ACTION_DELETE);
        }
    }

}
```
**2,Adapter使用**
    
   ```java
 //数据源    
    private final ArrayList<Object> mData = new ArrayList<>();
    //创建Adapter并绑定数据源
    private final SampleHolderAdapter mAdapter = new SampleHolderAdapter(mData);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listview);
        //绑定Adapter
        mListView.setAdapter(mAdapter);
        //设置click listener（由于CommonAdapter支持，并通过bindClick声明）
        mAdapter.setOnItemClickListener(onItemClickListener);
    }

    //ClickListener实现
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(CommonAdapter<?> adapter, View v, int position, int action) {
            switch (action) {//action就是我们SampleHolderAdapte中定义并且bindClick的value
                case SampleHolderAdapter.CLICK_ACTION_ADD:
                    toast(getContext(), position + " add click");
                    break;
                case SampleHolderAdapter.CLICK_ACTION_UPDATE:
                    toast(getContext(),position + " update click");
                    break;
                case SampleHolderAdapter.CLICK_ACTION_DELETE:
                    toast(getContext(), position + " delete click");
                    break;
            }
        }
    };
```

到此定义和使用的代码完毕。
