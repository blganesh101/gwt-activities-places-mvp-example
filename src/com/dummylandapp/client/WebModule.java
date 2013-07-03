package com.dummylandapp.client;

import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebModule implements EntryPoint {

	/*
	 * So the user enters a new URL in the address bar and the PlaceHistoryHandler uses
	 * its injected PlaceHistoryMapper to map the URL (token) to a Place.
	 * 
	 * Once it has a Place, the PlaceHistoryHandler sends the Place to the PlaceController.
	 * The PlaceController fires a PlaceChangeEvent onto the EventBus. Every ActivityManager
	 * is listening on that same EventBus for PlaceChangeEvents. If an ActivityManager has
	 * an ActivityMapper that supports the mapping of the Place to an Activity, then the
	 * Activity is returned.
	 * 
	 * An ActivityMapper
	 * 
	 * 
	 * 
	 */
	
	private EventBus eventBus = new SimpleEventBus();
	private PlaceController placeController = new PlaceController(eventBus);
	private PlaceHistoryMapper placeHistoryMapper;
	private PlaceHistoryHandler placeHistoryHandler;
	private Place defaultPlace;
	private ActivityMapper activityMapper;
	private ActivityManager activityManager;
	private LoginDisplay loginDisplay = new LoginDisplay();
	
	@Override
	public void onModuleLoad() {
//		bootstrap();
		
		RootPanel.get().add(new Label("Hello, GWT!"));
//		RootPanel.get().add(loginDisplay);
//		activityManager.setDisplay(loginDisplay);
//		
//		// Connect the PlaceController to the EventBus, and set the
//		// defaultPlace as our first place in history.
//		placeHistoryHandler.register(placeController, eventBus, defaultPlace);
//		placeHistoryHandler.handleCurrentHistory();
	}
	
	private void bootstrap() {
		// My job is to convert a URL (dummylandapp.com/#some-place?var1=key1&var2=key2)
		// to a Place.
		placeHistoryMapper = new PlaceHistoryMapper() {
			@Override
			public String getToken(Place arg0) {
				return "homey";
			}
			
			@Override
			public Place getPlace(String arg0) {
				return defaultPlace;
			}
		};
		
		placeHistoryHandler = new PlaceHistoryHandler(placeHistoryMapper);
		
		defaultPlace = new Place() {
			private String name;
			private Map<String,String> parameterMap;
			
			@SuppressWarnings("unused")
			public String getName() {
				return name;
			}
			@SuppressWarnings("unused")
			public void setName(String name) {
				this.name = name;
			}
			@SuppressWarnings("unused")
			public Map<String,String> getParameterMap() {
				return parameterMap;
			}
			@SuppressWarnings("unused")
			public void setParameterMap(Map<String,String> parameterMap) {
				this.parameterMap = parameterMap;
			}
		};
		
		activityMapper = new ActivityMapper() {
			@Override
			public Activity getActivity(Place arg0) {
				return new LoginActivity();
			}
		};
		
		activityManager = new ActivityManager(activityMapper, eventBus);
	}
	
	public class LoginDisplay extends SimplePanel {
		private LoginDisplayUiBinder uiBinder = GWT
				.create(LoginDisplayUiBinder.class);

		public LoginDisplay() {
			super();
			
			this.add(uiBinder.createAndBindUi(this));
		}

	}
	
	public interface LoginDisplayUiBinder extends UiBinder<Widget, LoginDisplay> {
		// ???
	}

	public class LoginActivity extends AbstractActivity {
		@Override
		public void start(AcceptsOneWidget panel,
				com.google.gwt.event.shared.EventBus eventBus) {

			// TODO: Inject the view...
			
			panel.setWidget(loginDisplay);
		}
	}
	
	
	
	// EVERYTHING AFTER THIS POINT WAS THE ORIGINAL WebModule.java AND WAS WORKING AS OF 6/25/2013.
	
//  private static final String SERVER_ERROR = "An error occurred while "
//      + "attempting to contact the server. Please check your network "
//      + "connection and try again.";
//
//  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
//
//  public void onModuleLoad() {
//	  Logger logger = Logger.getLogger("");
//	  
//	  logger.addHandler(new NetworkLoggerHandler());
//	  
//	  
//	  logger.info("I want to see this show up in the console!");
//	  logger.severe("I want to see this show up in the app log!");
//	  
//    final Button sendButton = new Button("Slap the bass!");
//    final TextBox nameField = new TextBox();
//    nameField.setText("GWT User");
//    final Label errorLabel = new Label();
//
//    // We can add style names to widgets
//    sendButton.addStyleName("sendButton");
//
//    // Add the nameField and sendButton to the RootPanel
//    // Use RootPanel.get() to get the entire body element
//    RootPanel.get("nameFieldContainer").add(nameField);
//    RootPanel.get("sendButtonContainer").add(sendButton);
//    RootPanel.get("errorLabelContainer").add(errorLabel);
//
//    // Focus the cursor on the name field when the app loads
//    nameField.setFocus(true);
//    nameField.selectAll();
//
//    // Create the popup dialog box
//    final DialogBox dialogBox = new DialogBox();
//    dialogBox.setText("Remote Procedure Call");
//    dialogBox.setAnimationEnabled(true);
//    final Button closeButton = new Button("Close");
//    // We can set the id of a widget by accessing its Element
//    closeButton.getElement().setId("closeButton");
//    final Label textToServerLabel = new Label();
//    final HTML serverResponseLabel = new HTML();
//    VerticalPanel dialogVPanel = new VerticalPanel();
//    dialogVPanel.addStyleName("dialogVPanel");
//    dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//    dialogVPanel.add(textToServerLabel);
//    dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//    dialogVPanel.add(serverResponseLabel);
//    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//    dialogVPanel.add(closeButton);
//    dialogBox.setWidget(dialogVPanel);
//
//    // Add a handler to close the DialogBox
//    closeButton.addClickHandler(new ClickHandler() {
//      public void onClick(ClickEvent event) {
//        dialogBox.hide();
//        sendButton.setEnabled(true);
//        sendButton.setFocus(true);
//      }
//    });
//
//    // Create a handler for the sendButton and nameField
//    class MyHandler implements ClickHandler, KeyUpHandler {
//      /**
//       * Fired when the user clicks on the sendButton.
//       */
//      public void onClick(ClickEvent event) {
//        sendNameToServer();
//      }
//
//      /**
//       * Fired when the user types in the nameField.
//       */
//      public void onKeyUp(KeyUpEvent event) {
//        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//          sendNameToServer();
//        }
//      }
//
//      /**
//       * Send the name from the nameField to the server and wait for a response.
//       */
//      private void sendNameToServer() {
//        // First, we validate the input.
//        errorLabel.setText("");
//        String textToServer = nameField.getText();
//        if (!FieldVerifier.isValidName(textToServer)) {
//          errorLabel.setText("Please enter at least four characters");
//          return;
//        }
//        
//        // Then, we send the input to the server.
//        sendButton.setEnabled(false);
//        textToServerLabel.setText(textToServer);
//        serverResponseLabel.setText("");
//        greetingService.greetServer(textToServer, new AsyncCallback<String>() {
//          public void onFailure(Throwable caught) {
//            // Show the RPC error message to the user
//            dialogBox.setText("Remote Procedure Call - Failure");
//            serverResponseLabel.addStyleName("serverResponseLabelError");
//            serverResponseLabel.setHTML(SERVER_ERROR);
//            dialogBox.center();
//            closeButton.setFocus(true);
//          }
//
//          public void onSuccess(String result) {
//            dialogBox.setText("Remote Procedure Call");
//            serverResponseLabel.removeStyleName("serverResponseLabelError");
//            serverResponseLabel.setHTML(result);
//            dialogBox.center();
//            closeButton.setFocus(true);
//          }
//        });
//      }
//    }
//
//    // Add a handler to send the name to the server
//    MyHandler handler = new MyHandler();
//    sendButton.addClickHandler(handler);
//    nameField.addKeyUpHandler(handler);
//  }
}
