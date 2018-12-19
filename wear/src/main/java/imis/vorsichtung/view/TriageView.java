package imis.vorsichtung.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import imis.vorsichtung.R;
import imis.vorsichtung.activities.MainActivity;
import imis.vorsichtung.utilities.Constants;

public class TriageView {

    private LinearLayout mainMenuScreen;

    private LinearLayout questionScreen;

    private TextView question;

    private ImageButton backButton;

    private MainActivity mainActivity;

    private Button startBtn;

    private Button exitBtn;

    private Button confirmKatBtn;

    private Button confirmActBtn;

    private ImageButton negBtn;

    private ImageButton posBtn;

    private LinearLayout actionScreen;

    private LinearLayout categoryScreen;

    private LinearLayout catText;

    private LinearLayout backMenuScreen;

    private TextView catColor;

    private TextView actionText;

    private ImageButton backBtn;

    private Button editLastStateBtn;

    private Button backToMenuBtn;

    private LinearLayout confirmSwipe1;

    private LinearLayout confirmSwipe2;

    private ImageView swipePositiveNegative;

    private TextView patientID;

    private LinearLayout view;

    private ImageButton settingsBtn;

    private LinearLayout settingsScreen;

    private LinearLayout overviewScreen;

    private TextView appName;

    private Switch swipeInteractionSwitch;

    private ImageButton patientBtn;

    private TextView greenCatAmount;

    private TextView yellowCatAmount;

    private TextView redCatAmount;

    private TextView blackCatAmount;

    private TextView patientAmount;

    public TriageView(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        patientBtn = (ImageButton) mainActivity.findViewById(R.id.patientButton);
        overviewScreen = (LinearLayout) mainActivity.findViewById(R.id.overviewScreen);
        swipeInteractionSwitch = (Switch) mainActivity.findViewById(R.id.swipeInteractionSwitch);
        settingsScreen = (LinearLayout) mainActivity.findViewById(R.id.settingsScreen);
        view = (LinearLayout) mainActivity.findViewById(R.id.display);

        patientID = (TextView) mainActivity.findViewById(R.id.patientID);
        appName = (TextView) mainActivity.findViewById(R.id.appName);

        mainMenuScreen = (LinearLayout) mainActivity.findViewById(R.id.mainMenuScreen);
        questionScreen = (LinearLayout) mainActivity.findViewById(R.id.questionScreen);

        question = (TextView) mainActivity.findViewById(R.id.questionText);

        actionScreen = (LinearLayout) mainActivity.findViewById(R.id.actionScreen);
        categoryScreen = (LinearLayout) mainActivity.findViewById(R.id.categoryScreen);
        backMenuScreen = (LinearLayout) mainActivity.findViewById(R.id.backMenuScreen);
        catText = (LinearLayout) mainActivity.findViewById(R.id.categorizationText);
        catColor = (TextView) mainActivity.findViewById(R.id.catColor);
        actionText = (TextView) mainActivity.findViewById(R.id.actionText);

        startBtn = (Button) mainActivity.findViewById(R.id.newPatientButton);
        exitBtn = (Button) mainActivity.findViewById(R.id.exitAppButton);
        confirmActBtn = (Button) mainActivity.findViewById(R.id.confirmAct);
        confirmKatBtn = (Button) mainActivity.findViewById(R.id.confirmKat);
        backButton = (ImageButton) mainActivity.findViewById(R.id.backButton);
        editLastStateBtn = (Button) mainActivity.findViewById(R.id.editLastQuestionButton);
        backToMenuBtn = (Button) mainActivity.findViewById(R.id.goToMainMenuButton);

        negBtn = (ImageButton) mainActivity.findViewById(R.id.negativeButton);
        posBtn = (ImageButton) mainActivity.findViewById(R.id.positiveButton);
        backBtn = (ImageButton) mainActivity.findViewById(R.id.backButton);
        settingsBtn = (ImageButton) mainActivity.findViewById(R.id.settingsButton);

        //backMenuScreen = (LinearLayout) mainActivity.findViewById(R.id.backMenuScreen);
        swipePositiveNegative = (ImageView) mainActivity.findViewById(R.id.swipePositiveNegative);

        confirmSwipe1 = (LinearLayout) mainActivity.findViewById(R.id.confirmSwipe1);
        confirmSwipe2 = (LinearLayout) mainActivity.findViewById(R.id.confirmSwipe2);

        this.greenCatAmount = (TextView) mainActivity.findViewById(R.id.greenCatAmount);
        this.yellowCatAmount = (TextView) mainActivity.findViewById(R.id.yellowCatAmount);
        this.redCatAmount = (TextView) mainActivity.findViewById(R.id.redCatAmount);
        this.blackCatAmount = (TextView) mainActivity.findViewById(R.id.blackCatAmount);
        this.patientAmount = (TextView) mainActivity.findViewById(R.id.patientAmount);

        drawMainMenu();

    }

    public void drawMainMenu() {
        questionScreen.setVisibility(View.GONE);
        actionScreen.setVisibility(View.GONE);
        categoryScreen.setVisibility(View.GONE);
        backMenuScreen.setVisibility(View.GONE);
        settingsScreen.setVisibility(View.GONE);
        overviewScreen.setVisibility(View.GONE);
        mainMenuScreen.setVisibility(View.VISIBLE);
        appName.setVisibility(View.VISIBLE);
        patientID.setVisibility(View.GONE);
        backButton.setVisibility(View.GONE);

        //TODO: BUTTONS ODER TEXT?
        settingsBtn.setVisibility(View.GONE);
        patientBtn.setVisibility(View.GONE);

        drawInteraction();

    }

    public void drawInteraction() {
        if (swipeInteractionSwitch.isChecked()) {
            posBtn.setVisibility(View.GONE);
            negBtn.setVisibility(View.GONE);
            confirmActBtn.setVisibility(View.GONE);
            confirmKatBtn.setVisibility(View.GONE);
            swipePositiveNegative.setVisibility(View.VISIBLE);
            confirmSwipe1.setVisibility(View.VISIBLE);
            confirmSwipe2.setVisibility(View.VISIBLE);
        } else {
            posBtn.setVisibility(View.VISIBLE);
            negBtn.setVisibility(View.VISIBLE);
            confirmActBtn.setVisibility(View.VISIBLE);
            confirmKatBtn.setVisibility(View.VISIBLE);
            swipePositiveNegative.setVisibility(View.GONE);
            confirmSwipe1.setVisibility(View.GONE);
            confirmSwipe2.setVisibility(View.GONE);

        }
    }


    /**
     * hides the old screen and shows the backmenu, also hides the backmenu, if the backmenu was visible
     */
    public void drawBackMenuScreen() {
        if (backMenuScreen.getVisibility() == View.GONE) {
            questionScreen.setVisibility(View.GONE);
            actionScreen.setVisibility(View.GONE);
            categoryScreen.setVisibility(View.GONE);
            settingsScreen.setVisibility(View.GONE);
            overviewScreen.setVisibility(View.GONE);
            backMenuScreen.setVisibility(View.VISIBLE);

        } else {
            backMenuScreen.setVisibility(View.GONE);
        }
        appName.setVisibility(View.GONE);
        patientID.setVisibility(View.VISIBLE);
    }

    public void drawSettingsScreen() {
        questionScreen.setVisibility(View.GONE);
        actionScreen.setVisibility(View.GONE);
        categoryScreen.setVisibility(View.GONE);
        backMenuScreen.setVisibility(View.GONE);
        mainMenuScreen.setVisibility(View.GONE);
        overviewScreen.setVisibility(View.GONE);
        settingsScreen.setVisibility(View.VISIBLE);
        appName.setVisibility(View.VISIBLE);
        patientID.setVisibility(View.GONE);
        settingsBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void drawOverviewScreen() {
        questionScreen.setVisibility(View.GONE);
        actionScreen.setVisibility(View.GONE);
        categoryScreen.setVisibility(View.GONE);
        backMenuScreen.setVisibility(View.GONE);
        mainMenuScreen.setVisibility(View.GONE);
        settingsScreen.setVisibility(View.GONE);
        overviewScreen.setVisibility(View.VISIBLE);
        appName.setVisibility(View.GONE);
        patientID.setVisibility(View.VISIBLE);
        settingsBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void drawQuestionScreen() {
        questionScreen.setVisibility(View.VISIBLE);
        actionScreen.setVisibility(View.GONE);
        categoryScreen.setVisibility(View.GONE);
        backMenuScreen.setVisibility(View.GONE);
        mainMenuScreen.setVisibility(View.GONE);
        settingsScreen.setVisibility(View.GONE);
        overviewScreen.setVisibility(View.GONE);
        appName.setVisibility(View.GONE);
        patientID.setVisibility(View.VISIBLE);
        settingsBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void drawCategoryScreen() {
        questionScreen.setVisibility(View.GONE);
        actionScreen.setVisibility(View.GONE);
        categoryScreen.setVisibility(View.VISIBLE);
        backMenuScreen.setVisibility(View.GONE);
        mainMenuScreen.setVisibility(View.GONE);
        settingsScreen.setVisibility(View.GONE);
        overviewScreen.setVisibility(View.GONE);
        appName.setVisibility(View.GONE);
        patientID.setVisibility(View.VISIBLE);
        settingsBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void drawActionScreen() {
        questionScreen.setVisibility(View.GONE);
        actionScreen.setVisibility(View.VISIBLE);
        categoryScreen.setVisibility(View.GONE);
        backMenuScreen.setVisibility(View.GONE);
        mainMenuScreen.setVisibility(View.GONE);
        settingsScreen.setVisibility(View.GONE);
        overviewScreen.setVisibility(View.GONE);
        appName.setVisibility(View.GONE);
        patientID.setVisibility(View.VISIBLE);
        settingsBtn.setVisibility(View.GONE);
        backBtn.setVisibility(View.VISIBLE);
    }

    public void updateOverviewScreen(int[] manvInfo){
        this.greenCatAmount.setText("GREEN: "+ manvInfo[Constants.CATEGORY_GREEN]);
        this.yellowCatAmount.setText("YELLOW: "+manvInfo[Constants.CATEGORY_YELLOW]);
        this.redCatAmount.setText("RED: "+manvInfo[Constants.CATEGORY_RED]);
        this.blackCatAmount.setText("BLACK: "+manvInfo[Constants.CATEGORY_BLACK]);
    }

    public void drawPatientID(int currentPatientID){
        patientID.setText("Patienten ID: " + currentPatientID);
    }

    public LinearLayout getView() {
        return view;
    }

    public Button getStartBtn() {
        return startBtn;
    }

    public Button getExitBtn() {
        return exitBtn;
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    public ImageButton getNegativeBtn() {
        return negBtn;
    }

    public ImageButton getPositiveBtn() {
        return posBtn;
    }

    public Button getConfirmKatBtn() {
        return confirmKatBtn;
    }

    public Button getConfirmActBtn() {
        return confirmActBtn;
    }

    public TextView getPatientID() {
        return patientID;
    }

    public LinearLayout getQuestionScreen() {
        return questionScreen;
    }

    public LinearLayout getActionScreen() {
        return actionScreen;
    }

    public LinearLayout getCategoryScreen() {
        return categoryScreen;
    }

    public Button getEditLastStateBtn() {
        return editLastStateBtn;
    }

    public Button getBackToMenuBtn() {
        return backToMenuBtn;
    }

    public LinearLayout getBackMenuScreen() {
        return backMenuScreen;
    }

    public ImageButton getSettingsBtn() {
        return settingsBtn;
    }

    public LinearLayout getSettingsScreen() {
        return settingsScreen;
    }

    public TextView getAppName() {
        return appName;
    }

    public Switch getSwipeInteractionSwitch() {
        return swipeInteractionSwitch;
    }

    public LinearLayout getMainMenuScreen() {
        return mainMenuScreen;
    }

    public TextView getQuestion() {
        return question;
    }

    public ImageButton getBackButton() {
        return backButton;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public ImageButton getNegBtn() {
        return negBtn;
    }

    public ImageButton getPosBtn() {
        return posBtn;
    }

    public LinearLayout getCatText() {
        return catText;
    }

    public TextView getCatColor() {
        return catColor;
    }

    public TextView getActionText() {
        return actionText;
    }

    public LinearLayout getConfirmSwipe1() {
        return confirmSwipe1;
    }

    public LinearLayout getConfirmSwipe2() {
        return confirmSwipe2;
    }

    public ImageView getSwipePositiveNegative() {
        return swipePositiveNegative;
    }


    public LinearLayout getOverviewScreen() {
        return overviewScreen;
    }

    public ImageButton getPatientBtn() {
        return patientBtn;
    }

    public TextView getGreenCatAmount() {
        return greenCatAmount;
    }

    public TextView getYellowCatAmount() {
        return yellowCatAmount;
    }

    public TextView getRedCatAmount() {
        return redCatAmount;
    }

    public TextView getBlackCatAmount() {
        return blackCatAmount;
    }

    public TextView getPatientAmount() {
        return patientAmount;
    }
}
