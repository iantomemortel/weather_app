// Generated code from Butter Knife. Do not modify!
package com.example.ian.weatherapp.Activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.ian.weatherapp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashScreenActivity_ViewBinding implements Unbinder {
  private SplashScreenActivity target;

  @UiThread
  public SplashScreenActivity_ViewBinding(SplashScreenActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashScreenActivity_ViewBinding(SplashScreenActivity target, View source) {
    this.target = target;

    target.splashScreen = Utils.findRequiredViewAsType(source, R.id.splashScreen, "field 'splashScreen'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashScreenActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.splashScreen = null;
  }
}
