package imis.vorsichtung.controller;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import imis.vorsichtung.R;
import imis.vorsichtung.activities.MainActivity;
import imis.vorsichtung.model.TriageModel;
import imis.vorsichtung.utilities.Constants;
import imis.vorsichtung.utilities.xml.XMLReader;
import imis.vorsichtung.view.TriageView;

import android.view.GestureDetector.SimpleOnGestureListener;

public class TriageController implements View.OnClickListener, View.OnTouchListener {

    private MainActivity mainActivity;
    private TriageView triageView;
    private TriageModel triageModel;
    private GestureDetector gestureDetector;

    public TriageController(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.triageView = new TriageView(mainActivity);
        this.triageModel = new TriageModel(mainActivity);
        this.gestureDetector = new GestureDetector(mainActivity, new GestureListener());

        triageView.getView().setOnTouchListener(this);
        triageView.getStartBtn().setOnClickListener(this);
        triageView.getNegativeBtn().setOnClickListener(this);
        triageView.getPositiveBtn().setOnClickListener(this);
        triageView.getConfirmKatBtn().setOnClickListener(this);
        triageView.getConfirmActBtn().setOnClickListener(this);
        triageView.getExitBtn().setOnClickListener(this);
        triageView.getBackBtn().setOnClickListener(this);
        triageView.getPatientID().setOnClickListener(this);
        triageView.getBackToMenuBtn().setOnClickListener(this);
        triageView.getEditLastStateBtn().setOnClickListener(this);
        triageView.getAppName().setOnClickListener(this);
        triageView.getSwipeInteractionSwitch().setOnClickListener(this);
        triageView.getPatientID().setOnClickListener(this);

    }

    /**
     * This method starts a new Patient, and resets all screens,
     * onclicklisteners, patient ID and counter to the initial state,
     * when the "New Patient" Button is pressed in the Main Menu.
     */

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.swipeInteractionSwitch:
                triageView.drawInteraction();
                break;
            case R.id.appName:
                triageView.drawSettingsScreen();
                break;
            case R.id.exitAppButton:
                triageModel.exitApp();
                break;
            case R.id.patientID:
                triageModel.updateOverview(triageView);
                break;
            case R.id.newPatientButton:
                triageModel.triageNewPatient(triageView);
                break;
            case R.id.negativeButton:
                triageModel.negativeAnswer(triageView);
                break;
            case R.id.positiveButton:
                triageModel.positiveAnswer(triageView);
                break;
            case R.id.backButton:
                triageModel.backPressed(triageView);
                break;
            case R.id.confirmAct:
                triageModel.actionConfirmed(triageView);
                break;
            case R.id.editLastQuestionButton:
                triageModel.back(triageView);
                break;
            case R.id.goToMainMenuButton:
                triageModel.backToMainMenu(triageView);
                break;
            case R.id.confirmKat:
                triageModel.categoryConfirmed(triageView);
                break;
        }
    }

    /*
    @author Mirak Rusin modified by Mher Kondratyan and Aliim Wanali
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            boolean result = false;
            boolean swipeActive = (mainActivity.findViewById(R.id.swipePositiveNegative).getVisibility() == View.VISIBLE);

            try {
                if (swipeActive) {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > Constants.SWIPE_THRESHOLD && Math.abs(velocityX) > Constants.SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    } else if (Math.abs(diffY) > Constants.SWIPE_THRESHOLD && Math.abs(velocityY) > Constants.SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public void onSwipeRight() {
        if (triageView.getQuestionScreen().getVisibility() == View.VISIBLE) {
            triageModel.negativeAnswer(triageView);
        }
    }

    public void onSwipeLeft() {
        if (triageView.getQuestionScreen().getVisibility() == View.VISIBLE) {
            triageModel.positiveAnswer(triageView);
        } else if (triageView.getActionScreen().getVisibility() == View.VISIBLE) {
            triageModel.positiveAnswer(triageView);
        } else if (triageView.getCategoryScreen().getVisibility() == View.VISIBLE) {
            triageModel.categoryConfirmed(triageView);
        }
    }

    public void onSwipeTop() {
        triageView.drawOverviewScreen();
    }

    public void onSwipeBottom() {
        triageModel.backPressed(triageView);
    }

}


