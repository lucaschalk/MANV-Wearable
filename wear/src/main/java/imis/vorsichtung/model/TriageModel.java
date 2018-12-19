package imis.vorsichtung.model;

import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import imis.vorsichtung.activities.MainActivity;
import imis.vorsichtung.model.algorithm.Algorithm;
import imis.vorsichtung.model.algorithm.MSTaRT;
import imis.vorsichtung.model.algorithm.PRIOR;
import imis.vorsichtung.utilities.Constants;
import imis.vorsichtung.utilities.xml.XMLReader;
import imis.vorsichtung.utilities.xml.XMLWriter;
import imis.vorsichtung.view.TriageView;

public class TriageModel {

    private MainActivity mainActivity;

    private XMLWriter xmlWriter;
    private XMLReader xmlReader;

    private Algorithm msTaRT;
    private Algorithm prior;

    private int currentPatientID;
    private boolean algorithmSwitch = true;

    public TriageModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        this.msTaRT = new MSTaRT(mainActivity);
        this.prior = new PRIOR(mainActivity);

        this.readXML();
    }

    public void exitApp() {
        System.exit(0);
    }

    public void triageNewPatient(TriageView triageView) {
        triageView.drawPatientID(++currentPatientID);
        if (!algorithmSwitch) {
            msTaRT.setState(1, true, triageView);
        } else {
            prior.setState(1, true, triageView);
        }
    }

    public void backToMainMenu(TriageView triageView) {
        if (!algorithmSwitch) {
            msTaRT.clearHistory();
        } else {
            prior.clearHistory();
        }
        currentPatientID--;
        triageView.drawMainMenu();

    }

    public void negativeAnswer(TriageView triageView) {
        if (!algorithmSwitch) {
            msTaRT.setStateNo(triageView);
        } else {
            prior.setStateNo(triageView);
        }
    }

    public void positiveAnswer(TriageView triageView) {
        if (!algorithmSwitch) {
            msTaRT.setStateYes(triageView);
        } else {
            prior.setStateYes(triageView);
        }
    }

    public void back(TriageView triageView) {
        if (!algorithmSwitch) {
            msTaRT.setPreviousState(triageView);
        } else {
            prior.setPreviousState(triageView);
        }
    }

    public void backPressed(TriageView triageView) {
        if (triageView.getSettingsScreen().getVisibility() == View.VISIBLE) {
            triageView.drawMainMenu();
            return;
        }
        if (triageView.getOverviewScreen().getVisibility() == View.VISIBLE) {

            if (!algorithmSwitch) {
                if (msTaRT.getCurrentState() >= 1 && msTaRT.getCurrentState() <= 5 || msTaRT.getCurrentState() == 7 || msTaRT.getCurrentState() == 8 || msTaRT.getCurrentState() == 31) {
                    triageView.drawQuestionScreen();
                } else if (msTaRT.getCurrentState() >= 100 && msTaRT.getCurrentState() <= 800) {
                    triageView.drawCategoryScreen();
                } else if (msTaRT.getCurrentState() == 6 || msTaRT.getCurrentState() == 80) {
                    triageView.drawActionScreen();
                } else {
                    triageView.drawMainMenu();
                }
            } else {
                if (prior.getCurrentState() >= 1 && prior.getCurrentState() <= 6) {
                    triageView.drawQuestionScreen();
                } else if (prior.getCurrentState() >= 100 && prior.getCurrentState() <= 700) {
                    triageView.drawCategoryScreen();
                } else if (prior.getCurrentState() >= 10 && prior.getCurrentState() <= 30) {
                    triageView.drawActionScreen();
                } else {
                    triageView.drawMainMenu();
                }
            }
            return;
        }

        if (!algorithmSwitch) {
            if (triageView.getBackMenuScreen().getVisibility() == View.GONE) {
                if (msTaRT.getCurrentState() == 1) {
                    triageView.getEditLastStateBtn().setVisibility(View.GONE);
                } else {
                    triageView.getEditLastStateBtn().setVisibility(View.VISIBLE);
                }
                triageView.drawBackMenuScreen();
            } else {
                triageView.drawBackMenuScreen();
                switchBackMenu(triageView);
            }
        } else {
            if (triageView.getBackMenuScreen().getVisibility() == View.GONE) {
                if (prior.getCurrentState() == 1) {
                    triageView.getEditLastStateBtn().setVisibility(View.GONE);
                } else {
                    triageView.getEditLastStateBtn().setVisibility(View.VISIBLE);
                }
                triageView.drawBackMenuScreen();
            } else {
                triageView.drawBackMenuScreen();
                switchBackMenu(triageView);
            }
        }
    }

    public void switchBackMenu(TriageView triageView) {
        if (!algorithmSwitch) {
            msTaRT.setState(msTaRT.getCurrentState(), false, triageView);
        } else {
            prior.setState(prior.getCurrentState(), false, triageView);
        }
    }

    public void actionConfirmed(TriageView triageView) {
        if (!algorithmSwitch) {
            msTaRT.setStateYes(triageView);
        } else {
            prior.setStateYes(triageView);
        }
    }

    public void categoryConfirmed(TriageView triageView) {
        triageView.drawMainMenu();
        if (!algorithmSwitch) {
            writeXml(msTaRT);
            mainActivity.sendData(msTaRT.getClassification());
            msTaRT.clearHistory();
        } else {
            writeXml(prior);
            mainActivity.sendData(prior.getClassification());
            prior.clearHistory();
        }

    }

    public void updateOverview(TriageView triageView) {
        readXML();
        triageView.updateOverviewScreen(xmlReader.getManvInfo());
        triageView.drawOverviewScreen();

    }

    public void readXML() {
        this.xmlReader = new XMLReader();
        this.xmlReader.readFromXML();
        this.currentPatientID = xmlReader.getManvInfo()[Constants.XML_LAST_PATIENT_ID];
    }

    /**
     * Method that writes the patient history for documentation into the xml
     * file for the patient using the XMLWriter
     */
    public void writeXml(Algorithm algorithm) {
        this.xmlWriter = new XMLWriter();
        this.xmlWriter.writeToXML(currentPatientID + "", algorithm.getClassification(), algorithm.getHistoryForXML());
    }

}
