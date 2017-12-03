package com.example.ak.mealplanner.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.ak.mealplanner.R;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import com.example.ak.mealplanner.Controllers.GetMealHistoryController;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private CompactCalendarView calendarView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MyApplication app;

    private ArrayAdapter<MealPlannerRec> listAdapter;


    private OnFragmentInteractionListener mListener;

    public TabFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment3 newInstance(String param1, String param2) {
        TabFragment3 fragment = new TabFragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onResume(){
        super.onResume();
        //TODO Will - use the controller to add meal history items to the list
        //GetMealHistoryController ghmc = new GetMealHistoryController(app.getUser().getUsername(), calendarView, this);
        //ghmc.execute();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_fragment3, container, false);

        calendarView =  rootView.findViewById(R.id.Calendar);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = calendarView.getEvents(dateClicked);
                Log.i("adarsh", "Day was clicked: " + dateClicked + " with events " + events);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.i("adarsh", "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });
        ArrayList<MealPlannerRec> mealList = new ArrayList<>();
        app = (MyApplication) getActivity().getApplication();
        // Create ArrayAdapter
        //TODO Will - use the controller to add meal history items to the list
        //GetMealHistoryController ghmc = new GetMealHistoryController(app.getUser().getUsername(), calendarView, this);
        //ghmc.execute();

        // Set the ArrayAdapter as the ListView's adapter.
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void addEvent(Event event){
        Log.i("adarsh", event.getData().toString());
        calendarView.addEvent(event, false);
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
