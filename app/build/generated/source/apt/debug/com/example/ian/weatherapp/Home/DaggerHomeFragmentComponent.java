// Generated by Dagger (https://google.github.io/dagger).
package com.example.ian.weatherapp.Home;

import com.example.ian.weatherapp.Fragments.MainScreenFragment;
import com.example.ian.weatherapp.Fragments.MainScreenFragment_MembersInjector;
import com.example.ian.weatherapp.WeatherApplicationComponent;
import dagger.internal.Preconditions;

public final class DaggerHomeFragmentComponent implements HomeFragmentComponent {
  private WeatherApplicationComponent weatherApplicationComponent;

  private DaggerHomeFragmentComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.weatherApplicationComponent = builder.weatherApplicationComponent;
  }

  @Override
  public void inject(MainScreenFragment mainScreenFragment) {
    injectMainScreenFragment(mainScreenFragment);
  }

  private MainScreenFragment injectMainScreenFragment(MainScreenFragment instance) {
    MainScreenFragment_MembersInjector.injectWeatherService(
        instance,
        Preconditions.checkNotNull(
            weatherApplicationComponent.getWeatherService(),
            "Cannot return null from a non-@Nullable component method"));
    MainScreenFragment_MembersInjector.injectGson(
        instance,
        Preconditions.checkNotNull(
            weatherApplicationComponent.getGson(),
            "Cannot return null from a non-@Nullable component method"));
    return instance;
  }

  public static final class Builder {
    private WeatherApplicationComponent weatherApplicationComponent;

    private Builder() {}

    public HomeFragmentComponent build() {
      if (weatherApplicationComponent == null) {
        throw new IllegalStateException(
            WeatherApplicationComponent.class.getCanonicalName() + " must be set");
      }
      return new DaggerHomeFragmentComponent(this);
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This
     *     method is a no-op. For more, see https://google.github.io/dagger/unused-modules.
     */
    @Deprecated
    public Builder homeFragmentModule(HomeFragmentModule homeFragmentModule) {
      Preconditions.checkNotNull(homeFragmentModule);
      return this;
    }

    public Builder weatherApplicationComponent(
        WeatherApplicationComponent weatherApplicationComponent) {
      this.weatherApplicationComponent = Preconditions.checkNotNull(weatherApplicationComponent);
      return this;
    }
  }
}
