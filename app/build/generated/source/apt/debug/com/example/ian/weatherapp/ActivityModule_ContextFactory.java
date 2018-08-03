// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.example.ian.weatherapp;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ActivityModule_ContextFactory implements Factory<Context> {
  private final ActivityModule module;

  public ActivityModule_ContextFactory(ActivityModule module) {
    assert module != null;
    this.module = module;
  }

  @Override
  public Context get() {
    return Preconditions.checkNotNull(
        module.context(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static Factory<Context> create(ActivityModule module) {
    return new ActivityModule_ContextFactory(module);
  }
}