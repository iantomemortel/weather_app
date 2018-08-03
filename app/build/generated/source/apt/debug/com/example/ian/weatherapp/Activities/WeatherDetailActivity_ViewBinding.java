// Generated code from Butter Knife. Do not modify!
package com.example.ian.weatherapp.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.ian.weatherapp.R;
import com.example.ian.weatherapp.Util.CustomExpandableListView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WeatherDetailActivity_ViewBinding implements Unbinder {
  private WeatherDetailActivity target;

  @UiThread
  public WeatherDetailActivity_ViewBinding(WeatherDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WeatherDetailActivity_ViewBinding(WeatherDetailActivity target, View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.expandableListView = Utils.findOptionalViewAsType(source, R.id.expandableDetailList, "field 'expandableListView'", CustomExpandableListView.class);
    target.progreessBarParentLayout = Utils.findRequiredViewAsType(source, R.id.progreessBarParentLayout, "field 'progreessBarParentLayout'", LinearLayout.class);
    target.btnRefresh = Utils.findRequiredViewAsType(source, R.id.btnRefresh, "field 'btnRefresh'", Button.class);
    target.iconWeather = Utils.findRequiredViewAsType(source, R.id.iconWeather, "field 'iconWeather'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WeatherDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.expandableListView = null;
    target.progreessBarParentLayout = null;
    target.btnRefresh = null;
    target.iconWeather = null;
  }
}
