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
public class MSTaRT extends Algorithm{


    public MSTaRT(MainActivity activity) {
        super(activity);
    }

    /**
     * This method defines for each newState which elements and which texts should
     * be shown. It also contains the definition of the next states for the
     * answers "Yes" and "No"
     * <p>
     * Each newState number represents a question, instruction or classification of
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

        // State 1 to 5 and 7 to 8: Normal questions of the algorithm
        if (newState == 1) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question1);
            super.setNextStatesForAnswers(100, 2);
        } else if (newState == 2) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question2);
            super.setNextStatesForAnswers(80, 3);
        } else if (newState == 3) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question3);
            super.setNextStatesForAnswers(31, 4);
        } else if (newState == 4) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question4);
            super.setNextStatesForAnswers(300, 5);
        } else if (newState == 5) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question5);
            super.setNextStatesForAnswers(6, 7);
        } else if (newState == 7) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question6);
            super.setNextStatesForAnswers(300, 8);
        } else if (newState == 8) {
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.question7);
            super.setNextStatesForAnswers(300, 700);

            // State 100 to 800: Classification states
        } else if (newState == 800) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.black));
            triageView.getCatColor().setText(R.string.catBlack);
        } else if (newState == 300) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.holo_red_dark));
            triageView.getCatColor().setText(R.string.catRed);
        } else if (newState == 700) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.holo_orange_light));
            triageView.getCatColor().setText(R.string.catYellow);
        }else if (newState == 100) {
            triageView.drawCategoryScreen();
            triageView.getCatText().setBackgroundColor(super.getMainActivity().getResources().getColor(android.R.color.holo_green_light));
            triageView.getCatColor().setText(R.string.catGreen);
        }

        // States 6,31,80 are questions and instructions between the normal
        // questions of the algorithm and the classification
        else if (newState == 80) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.action1);
            super.setNextStatesForAnswers(800, 1);
        } else if (newState == 31) {
            //special case of instruction and question in one screen
            triageView.drawQuestionScreen();
            triageView.getQuestion().setText(R.string.action3);
            super.setNextStatesForAnswers(300, 80);
        } else if (newState == 6) {
            triageView.drawActionScreen();
            triageView.getActionText().setText(R.string.action2);
            super.setNextStatesForAnswers(7, 1);
        }
    }

    @Override
    public void showPreviousQuestionAndAnswer(int previousState, int newState, boolean createHistory){
        // input was yes or no
        // Questions 1 to 7
        if(createHistory) {
            super.getHistory().add(newState);

            if (previousState == 1) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question1));
                if (newState == 2) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 100) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 2) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question2));
                if (newState == 3) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 80) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 3) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question3));
                if (newState == 4) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 31) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 4) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question4));
                if (newState == 5) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 300) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 5) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question5));
                if (newState == 7) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 6) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 7) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question6));
                if (newState == 8) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 300) {

                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 8) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.question7));
                if (newState == 700) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 300) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            }
            // States 6, 31 and 80
            else if (previousState == 80) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.action1));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            } else if (previousState == 31) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.action3));
                if (newState == 80) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.no));
                } else if (newState == 300) {
                    super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
                }
            } else if (previousState == 6) {
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.action2));
                super.getHistoryForXML().add(super.getMainActivity().getString(R.string.yes));
            }
        }
    }


    @Override
    public int getClassification() {
        // category 3
        if (super.getCurrentState() == 100) {
            return Constants.CATEGORY_GREEN;
            // category 1
        } else if (super.getCurrentState() == 300) {
            return Constants.CATEGORY_RED;
            // category 2
        } else if (super.getCurrentState() == 700) {
            return Constants.CATEGORY_YELLOW;
            // category black is 0
        } else if (super.getCurrentState() == 800) {
            return Constants.CATEGORY_BLACK;
        } else
            return -1;
    }

}
