package com.madballneek.github.mvpboostrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.madballneek.github.mvpboostrap.module.ConcurrentSimpleMessengerModule;
import com.madballneek.github.mvpboostrap.presenter.MessagePresenterImpl;
import com.madballneek.github.mvpboostrap.presenter.Presenter;
import javafx.stage.Stage;

/**
 * A class to control application state. As the state of the application changes, this class to set
 * the appropriate presenters and view as active.
 * <p/>
 * Currently, this simple application only has one state.
 */
public class AppController implements Presenter {
	private Presenter messagePresenter;

	@Override
	public void go(Stage stage) {
		stage.setTitle("Hello MVP");

		// Set the initial application state.
		// As you add more states to the application, you'll need to add state transition management.
		if (messagePresenter == null) {
			// Let Guice handle building the object graph and return back the instance we need.
			// ConcurrentSimpleMessengerModule defines the interface-to-concrete bindings.
			Injector injector = Guice.createInjector(new ConcurrentSimpleMessengerModule());

			// Alternatively, if we later decide we need execute service tasks asynchronously,
			// we simply ask to inject a AsyncSimpleMessengerModule instead.
//			Injector injector = Guice.createInjector(new AsyncSimpleMessengerModule());

			// Or let's say we're having a bad case of the Mondays,
			// and we want to use a AsyncTaskManager and a RudeMessageService?
//			Injector injector = Guice.createInjector(new AsyncRudeMessengerModule());

			// Regardless of the type of module we use, the MessagePresenter cares little,
			// as long as its dependencies are fulfilled.
			messagePresenter = injector.getInstance(MessagePresenterImpl.class);
		}
		messagePresenter.go(stage);
	}
}