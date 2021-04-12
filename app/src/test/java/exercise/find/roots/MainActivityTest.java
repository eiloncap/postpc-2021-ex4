package exercise.find.roots;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 28)
public class MainActivityTest extends TestCase {

  @Test
  public void when_activityIsLaunching_then_theButtonShouldStartDisabled(){
    // create a MainActivity and let it think it's currently displayed on the screen+
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // test: make sure that the "calculate" button is disabled
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);
    assertFalse(button.isEnabled());
  }

  @Test
  public void when_activityIsLaunching_then_theEditTextShouldStartEmpty(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // test: make sure that the "input" edit-text has no text
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    String input = inputEditText.getText().toString();
    assertTrue(input == null || input.isEmpty());
  }

  @Test
  public void when_activityIsLaunching_then_theProgressBarShouldBeInvisible(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // test: make sure that the progress bar is invisible
    ProgressBar progressBar = mainActivity.findViewById(R.id.progressBar);
    assertEquals(View.GONE, progressBar.getVisibility());
  }

  @Test
  public void when_userIsEnteringNumberInput_and_noCalculationAlreadyHappened_then_theButtonShouldBeEnabled(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // find the edit-text and the button
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

    // test: insert input to the edit text and verify that the button is enabled
    inputEditText.setText("1");
    assertTrue(button.isEnabled());
  }

  @Test
  public void when_userIsEnteringZeroInput_and_noCalculationAlreadyHappened_then_theButtonShouldBeDisabled(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // find the edit-text and the button
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

    // test: insert input to the edit text and verify that the button is disabled
    inputEditText.setText("0");
    assertFalse(button.isEnabled());
  }

  @Test
  public void when_userIsEnteringNegativeInput_and_noCalculationAlreadyHappened_then_theButtonShouldBeDisabled(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // find the edit-text and the button
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

    // test: insert input to the edit text and verify that the button is disabled
    inputEditText.setText("-2");
    assertFalse(button.isEnabled());
  }

  @Test
  public void when_userIsEnteringFloatInput_and_noCalculationAlreadyHappened_then_theButtonShouldBeDisabled(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // find the edit-text and the button
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

    // test: insert input to the edit text and verify that the button is disabled
    inputEditText.setText("17.3");
    assertFalse(button.isEnabled());
  }

  @Test
  public void when_userIsEnteringValidInput_and_thenDeletingIt_then_theButtonShouldBeEnabledAndThenDisabled(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // find the edit-text and the button
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

    // test: insert input to the edit text and verify that the button is enabled
    inputEditText.setText("22");
    assertTrue(button.isEnabled());
    inputEditText.setText("");
    assertFalse(button.isEnabled());
  }

  @Test
  public void when_userIsEnteringValidInput_and_thenClickingTheButton_then_progressBarShouldBeVisible(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();

    // find the progressBar, edit-text and the button
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);
    ProgressBar progressBar = mainActivity.findViewById(R.id.progressBar);

    // test: insert input to the edit text and verify that the progressBar is visible
    inputEditText.setText("11111111");
    button.performClick();
    assertEquals(View.VISIBLE, progressBar.getVisibility());
  }

  @Test
  public void when_userIsEnteringInput_and_thenRotatesScreen_then_inputIsTheSame(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);

    mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    inputEditText.setText("1234567");
    mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    assertEquals("1234567", inputEditText.getText().toString());
  }

  @Test
  public void when_startingCalculation_then_theButtonShouldStartDisabled(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    EditText inputEditText = mainActivity.findViewById(R.id.editTextInputNumber);
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);

    inputEditText.setText("1234567");
    button.performClick();
    assertFalse(button.isEnabled());
  }

  @Test
  public void when_startingCalculation_and_thenReceivingFailureBroadcast_then_theButtonShouldStartEnabledAndProgressBarShouldBeInvisible(){
    // create a MainActivity and let it think it's currently displayed on the screen
    MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class).create().visible().get();
    Button button = mainActivity.findViewById(R.id.buttonCalculateRoots);
    ProgressBar progressBar = mainActivity.findViewById(R.id.progressBar);

    Intent broadcast = new Intent().setAction("stopped_calculations");
    RuntimeEnvironment.application.sendBroadcast(broadcast);
    Shadows.shadowOf(Looper.getMainLooper()).idle();

    assertTrue(button.isEnabled());
    assertEquals(View.GONE, progressBar.getVisibility());
  }
}