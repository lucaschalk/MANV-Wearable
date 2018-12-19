package imis.vorsichtung.model.algorithm;

import android.util.Log;
import android.view.View;

import java.util.LinkedList;
import java.util.Locale;

import imis.vorsichtung.R;
import imis.vorsichtung.activities.MainActivity;
import imis.vorsichtung.utilities.Constants;
import imis.vorsichtung.view.TriageView;


/**
 * This application uses states in order to know the actual question and the
 * following questions for the answers. The states are also used to create a
 * history. This can be used in order to document the user commands for a
 * patient and for the getting back function. This class saves and sets the
 * states. It also contains the information, which menu elements are needed for
 * a state and which texts and symbols should be displayed
 *
 * @author Henrik Berndt
 */
public abstract class Algorithm {
    // Fields for current state and a history of states
    private int currentState;

    private LinkedList<Integer> history;
    private LinkedList<String> historyForXml;
    private MainActivity mainActivity;

    // next states for the algorithm for the answers yes and no
    private int nextStateYes = 100;
    private int nextStateNo = 2;

    public Algorithm(MainActivity activity) {
        this.mainActivity = activity;
        this.currentState = 0;
        this.history = new LinkedList<Integer>();
        this.historyForXml = new LinkedList<String>();
    }

    /**
     * This method defines for each state which elements and which texts should
     * be shown. It also contains the definition of the next states for the
     * answers "Yes" and "No"
     * <p>
     * Each state number represents a question, instruction or classification of
     * the algorithm. The states are sorted in a way, so that states with a
     * higher number always represent states which follow states with a smaller
     * number in the algorithm. This is used in order to recognize whether the
     * user wanted to get back or to one of the next states
     *
     * @param newState
     */
    public abstract void setState(int newState, boolean createHistory, TriageView triageView);


    /**
     * Method that shows the previous question and answer based on the previous
     * and the new state
     *
     * @param previousState
     * @param newState
     */
    public abstract void showPreviousQuestionAndAnswer(int previousState, int newState, boolean createHistory);

    /**
     * Returns the classification of the patient. 0 for black (dead), -1 for
     * unknown
     *
     * @return
     */
    public abstract int getClassification();
    /**
     * This method is used to set the next states of the algorithm which are
     * shown if the user choses "Yes" or "No". If there is only one option for
     * the user, the other state won't be reachable and can be set to the
     * initial state 1
     *
     * @param stateYes
     * @param stateNo
     */
    public void setNextStatesForAnswers(int stateYes, int stateNo) {
        nextStateYes = stateYes;
        nextStateNo = stateNo;
    }

    /**
     * Indicates that the answer to a question was yes and calls the next state
     */
    public void setStateYes(TriageView triageView) {
        setState(nextStateYes,true,triageView);
    }

    /**
     * Indicates that the answer to a question was no and calls the next state
     */
    public void setStateNo(TriageView triageView) {
        setState(nextStateNo, true,triageView);
    }

    /**
     * Indicates that the user wanted to get back and calls the previous state
     */
    public void setPreviousState(TriageView triageView) {
        int previousState = getPreviousState();
        if (previousState != 0) {
            setState(previousState, false, triageView);
        }
    }

    /**
     * Clear the history by deleting all states. Has to be done every time when
     * a new patient is triaged
     */
    public void clearHistory() {
        this.currentState = 0;
        history.clear();
        historyForXml.clear();
    }

    /**
     * Get and return the previous state from the history
     *
     * @return
     */
    public int getPreviousState() {
        if (history.size() > 1) {
            // delete last element (current state) from history
            history.remove(history.size() - 1);

            // delete the last two elements (strings for question and for
            // answer) from history
            historyForXml.remove(historyForXml.size() - 1);
            historyForXml.remove(historyForXml.size() - 1);
            // set current state to the last element from
            // history (last state)

            return history.get(history.size() - 1);
        } else {
            return 0;
        }
    }



    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public  LinkedList<Integer> getHistory() {
        return history;
    }

    public LinkedList<String> getHistoryForXML() {
        return historyForXml;
    }

    public int getCurrentState() {
        return currentState;
    }

    public LinkedList<String> getHistoryForXml() {
        return historyForXml;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public int getNextStateYes() {
        return nextStateYes;
    }

    public int getNextStateNo() {
        return nextStateNo;
    }


}
