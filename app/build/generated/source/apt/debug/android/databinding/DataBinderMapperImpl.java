
package android.databinding;
import com.example.ian.weatherapp.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.example.ian.weatherapp.R.layout.fragment_main_screen:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/fragment_main_screen_0".equals(tag)) {
                            return new com.example.ian.weatherapp.databinding.FragmentMainScreenBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for fragment_main_screen is invalid. Received: " + tag);
                }
                case com.example.ian.weatherapp.R.layout.item_list_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/item_list_item_0".equals(tag)) {
                            return new com.example.ian.weatherapp.databinding.ItemListItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for item_list_item is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case -310932039: {
                if(tag.equals("layout/fragment_main_screen_0")) {
                    return com.example.ian.weatherapp.R.layout.fragment_main_screen;
                }
                break;
            }
            case 1430266334: {
                if(tag.equals("layout/item_list_item_0")) {
                    return com.example.ian.weatherapp.R.layout.item_list_item;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"callback"
            ,"isLoading"
            ,"item"};
    }
}