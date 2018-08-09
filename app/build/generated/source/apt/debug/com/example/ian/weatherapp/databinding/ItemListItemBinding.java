package com.example.ian.weatherapp.databinding;
import com.example.ian.weatherapp.R;
import com.example.ian.weatherapp.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemListItemBinding extends android.databinding.ViewDataBinding implements android.databinding.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.iconWeather, 4);
    }
    // views
    @NonNull
    public final android.widget.TextView actWeather;
    @NonNull
    public final android.widget.ImageView iconWeather;
    @NonNull
    public final android.widget.TextView location;
    @NonNull
    private final android.support.constraint.ConstraintLayout mboundView0;
    @NonNull
    public final android.widget.TextView temp;
    // variables
    @Nullable
    private com.example.ian.weatherapp.entity.Item mItem;
    @Nullable
    private com.example.ian.weatherapp.Home.ItemClickCallback mCallback;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemListItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.actWeather = (android.widget.TextView) bindings[2];
        this.actWeather.setTag(null);
        this.iconWeather = (android.widget.ImageView) bindings[4];
        this.location = (android.widget.TextView) bindings[1];
        this.location.setTag(null);
        this.mboundView0 = (android.support.constraint.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.temp = (android.widget.TextView) bindings[3];
        this.temp.setTag(null);
        setRootTag(root);
        // listeners
        mCallback2 = new android.databinding.generated.callback.OnClickListener(this, 1);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.item == variableId) {
            setItem((com.example.ian.weatherapp.entity.Item) variable);
        }
        else if (BR.callback == variableId) {
            setCallback((com.example.ian.weatherapp.Home.ItemClickCallback) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setItem(@Nullable com.example.ian.weatherapp.entity.Item Item) {
        this.mItem = Item;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }
    @Nullable
    public com.example.ian.weatherapp.entity.Item getItem() {
        return mItem;
    }
    public void setCallback(@Nullable com.example.ian.weatherapp.Home.ItemClickCallback Callback) {
        this.mCallback = Callback;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.callback);
        super.requestRebind();
    }
    @Nullable
    public com.example.ian.weatherapp.Home.ItemClickCallback getCallback() {
        return mCallback;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        com.example.ian.weatherapp.entity.Item item = mItem;
        com.example.ian.weatherapp.Home.ItemClickCallback callback = mCallback;
        java.lang.String itemWeatherGetInt0Main = null;
        java.lang.String itemMainTemp = null;
        java.util.List<com.example.ian.weatherapp.entity.Weather> itemWeather = null;
        com.example.ian.weatherapp.entity.Weather itemWeatherGetInt0 = null;
        java.lang.String itemName = null;
        com.example.ian.weatherapp.entity.Main itemMain = null;

        if ((dirtyFlags & 0x5L) != 0) {



                if (item != null) {
                    // read item.weather
                    itemWeather = item.getWeather();
                    // read item.name
                    itemName = item.getName();
                    // read item.main
                    itemMain = item.getMain();
                }


                if (itemWeather != null) {
                    // read item.weather.get(0)
                    itemWeatherGetInt0 = itemWeather.get(0);
                }
                if (itemMain != null) {
                    // read item.main.temp
                    itemMainTemp = itemMain.temp;
                }


                if (itemWeatherGetInt0 != null) {
                    // read item.weather.get(0).main
                    itemWeatherGetInt0Main = itemWeatherGetInt0.main;
                }
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.actWeather, itemWeatherGetInt0Main);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.location, itemName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.temp, itemMainTemp);
        }
        if ((dirtyFlags & 0x4L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(mCallback2);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        // localize variables for thread safety
        // item
        com.example.ian.weatherapp.entity.Item item = mItem;
        // callback
        com.example.ian.weatherapp.Home.ItemClickCallback callback = mCallback;
        // callback != null
        boolean callbackJavaLangObjectNull = false;



        callbackJavaLangObjectNull = (callback) != (null);
        if (callbackJavaLangObjectNull) {



            callback.onClick(item);
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ItemListItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemListItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ItemListItemBinding>inflate(inflater, com.example.ian.weatherapp.R.layout.item_list_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ItemListItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemListItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.ian.weatherapp.R.layout.item_list_item, null, false), bindingComponent);
    }
    @NonNull
    public static ItemListItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemListItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/item_list_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ItemListItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): item
        flag 1 (0x2L): callback
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}