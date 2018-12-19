package imis.vorsichtung.model.algorithm;

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


public class PRIOR extends Algorithm {


    public PRIOR(MainActivity activity) {
        super(activity);
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
    @Override
    public void setState(int newState, boolean createHistory, TriageView triageView) {

        // If the newState should be saved in the history, this will be done here
        showPreviousQuestionAndAnswer(super.getCurrentState(), newState, createHistory);
        //set new state
        super.setCurrentState(newState);

        // State 1 to 7: Normal questions of the algorithm
        if (newState == 1) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question21);
            setNextStatesForAnswers(10, 2);
        } else if (newState == 2) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question22);
            setNextStatesForAnswers(20, 3);
        } else if (newState == 3) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question23);
            setNextStatesForAnswers(30, 4);
        } else if (newState == 4) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question24);
            setNextStatesForAnswers(40, 5);
        } else if (newState == 5) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question25);
            setNextStatesForAnswers(500, 6);
        } else if (newState == 6) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question26);
            setNextStatesForAnswers(600, 7);
        } else if (newState == 7) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question27);
            setNextStatesForAnswers(700, 800);

            // State 100 to 800: Classification states
        } else if (newState == 100 || newState == 200 || newState == 300 || newState == 400 || newState == 500 || newState == 600) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.holo_red_dark));
            triageView.getCatColor().setText(R.string.catRed);
        } else if (newState == 700) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.holo_orange_light));
            triageView.getCatColor().setText(R.string.catYellow);

        } else if (newState == 800) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.holo_green_light));
            triageView.getCatColor().setText(R.string.catGreen);
        }

        // States 10-30 are questions and instructions between the normal
        // questions of the algorithm and the classification
        else if (newState == 10) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.stopbleeding);
            setNextStatesForAnswers(11, 1);
        } else if (newState == 11) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.transport);
            setNextStatesForAnswers(100, 1);
        } else if (newState == 20) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.spontaneousBreathingQuestion);
            setNextStatesForAnswers(21, 1);
        } else if (newState == 21) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.lateralposition);
            setNextStatesForAnswers(22, 1);
        } else if (newState == 22) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.stopbleeding);
            setNextStatesForAnswers(200, 1);
        } else if (newState == 30) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.spontaneousBreathingQuestion);
            setNextStatesForAnswers(31, 1);
        } else if (newState == 31) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.stopbleeding);
            setNextStatesForAnswers(300, 1);
        } else if (newState == 40) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.stopbleeding);
            setNextStatesForAnswers(400, 1);
        }


    }

    @Override
    public void showPreviousQuestionAndAnswer(int previousState, int newState, boolean createHistory) {
        // input was yes or no
        // Questions 1 to 7
        if (createHistory) {
            super.getHistory().add(newState);

            if (previousState == 1) {
                if (newState == 2) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question21));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 10) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question21));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 2) {
                if (newState == 3) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question22));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 20) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question22));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 3) {
                if (newState == 4) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question23));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 30) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question23));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 4) {
                if (newState == 5) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question24));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 40) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question24));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 5) {
                if (newState == 6) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question25));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 500) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question25));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 6) {
                if (newState == 7) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question26));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 600) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question26));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 7) {
                if (newState == 800) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question27));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 700) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question27));
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            }

            // States 10-30
            else if (previousState == 10) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.stopbleeding));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 11) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.transport));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 20) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.spontaneousBreathingQuestion));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 21) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.lateralposition));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 22) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.stopbleeding));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 30) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.spontaneousBreathingQuestion));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 31) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.stopbleeding));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 40) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.stopbleeding));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            }
        }
    }


    @Override
    public int getClassification() {
        // category 1
        if (super.getCurrentState() == 100) {
            return Constants.CATEGORY_RED;
        } else if (super.getCurrentState() == 200) {
            return Constants.CATEGORY_RED;
        } else if (super.getCurrentState() == 300) {
            return Constants.CATEGORY_RED;
        } else if (super.getCurrentState() == 400) {
            return Constants.CATEGORY_RED;
        } else if (super.getCurrentState() == 500) {
            return Constants.CATEGORY_RED;
        } else if (super.getCurrentState() == 600) {
            return Constants.CATEGORY_RED;
            // category 2
        } else if (super.getCurrentState() == 700) {
            return Constants.CATEGORY_YELLOW;
            // category 3
        } else if (super.getCurrentState() == 800) {
            return Constants.CATEGORY_GREEN;
        } else
            return -1;
    }


}
