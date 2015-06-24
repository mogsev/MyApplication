package com.mogsev.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mogsev.util.MathOperation;
import com.mogsev.util.RandomValue;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssignmentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssignmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssignmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

   // private OnFragmentInteractionListener mListener;

    private static final String RANDOM_VALUE = "RANDOM_VALUE";
    private Integer answer;
    private ArrayList<Integer> list;
    Button buttonClick;
    Button answer1;
    Button answer2;
    Button answer3;
    Button buttonProceed;
    TextView textViewAnswer;
    TextView textViewExpression;
    RandomValue randomValue;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssignmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    /**
    public static AssignmentFragment newInstance(String param1, String param2) {
        AssignmentFragment fragment = new AssignmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    public AssignmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_assignment, container, false);
        //The application was lunched?
        if (savedInstanceState == null) {
            randomValue = new RandomValue(20, MathOperation.SUM);
            randomValue.generate();
            list = randomValue.getList();
        } else {
            randomValue = (RandomValue) savedInstanceState.get(RANDOM_VALUE);
            list = randomValue.getList();
        }

        //Initialize links for objects
        textViewAnswer = (TextView) view.findViewById(R.id.textViewAnswer);
        answer1 = (Button) view.findViewById(R.id.answer1);
        answer2 = (Button) view.findViewById(R.id.answer2);
        answer3 = (Button) view.findViewById(R.id.answer3);
        buttonProceed = (Button) view.findViewById(R.id.buttonProceed);
        textViewExpression = (TextView) view.findViewById(R.id.textViewExpression);

        //filling Activity
        onSum();

        // Inflate the layout for this fragment
        return view;
    }
/**
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
 */
/**
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

/**
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /**
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(RANDOM_VALUE, randomValue);
    }

    /**
     *
     * @param view
     */
    public void onClickAnswer(View view) {
        buttonClick = (Button) view;
        answer = new Integer(buttonClick.getText().toString());
        textViewAnswer.setVisibility(View.VISIBLE);
        if (answer.compareTo(randomValue.getResult()) == 0) {
            textViewAnswer.setText(R.string.correct_answer);
            textViewAnswer.setTextColor(Color.GREEN);
        } else {
            textViewAnswer.setText(R.string.wrong_answer);
            textViewAnswer.append(" ");
            textViewAnswer.append(randomValue.getResult().toString());
            textViewAnswer.setTextColor(Color.RED);
        }
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        buttonProceed.setVisibility(View.VISIBLE);

        // generate new data
        randomValue.generate();
        list = randomValue.getList();
    }

    /**
     *
     * @param view
     */
    public void onClickProceed(View view){
        onSum();
    }

    /**
     *
     */
    public void onSum() {
        buttonProceed.setVisibility(View.INVISIBLE);
        textViewAnswer.setText(R.string.title_answer);
        textViewAnswer.setTextColor(Color.BLACK);

        textViewExpression.setText(randomValue.getExpression());
        answer1.setEnabled(true);
        answer2.setEnabled(true);
        answer3.setEnabled(true);
        answer1.setText(String.valueOf(list.get(0)));
        answer2.setText(String.valueOf(list.get(1)));
        answer3.setText(String.valueOf(list.get(2)));
    }

}
