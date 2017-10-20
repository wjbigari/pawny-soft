package com.example.ak.mealplanner;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabFragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabFragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayAdapter<Fooditem> listAdapter;

    private OnFragmentInteractionListener mListener;

    public TabFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment1 newInstance(String param1, String param2) {
        TabFragment1 fragment = new TabFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab_fragment1, container, false);

        final ListView mainListView =  rootView.findViewById(R.id.list_main);

        // Create and populate a List of food names
        ArrayList<Fooditem> foodList = new ArrayList<Fooditem>();

        // Create ArrayAdapter
        listAdapter  = new ArrayAdapter<Fooditem>(getActivity(), R.layout.listrow, foodList);


        // Add more foods
        listAdapter.add(new Fooditem("Bread", "10"));
        listAdapter.add(new Fooditem("Milk", "10"));
        listAdapter.add(new Fooditem("Eggs", "10"));
        listAdapter.add(new Fooditem("Orange", "10"));
        listAdapter.add(new Fooditem("Yogurt", "10"));
        listAdapter.add(new Fooditem("Bacon", "10"));
        listAdapter.add(new Fooditem("Ham", "10"));
        listAdapter.add(new Fooditem("Lettuce", "10"));
        listAdapter.add(new Fooditem("Apple", "10"));
        listAdapter.add(new Fooditem("Rice", "10"));
        listAdapter.add(new Fooditem("Steak", "10"));
        listAdapter.add(new Fooditem("Avocado", "10"));
        listAdapter.add(new Fooditem("Beans", "10"));
        listAdapter.add(new Fooditem("Salsa", "10"));

        listAdapter.sort(new Comparator<Fooditem>() {
            @Override
            public int compare(Fooditem o1, Fooditem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        listAdapter.notifyDataSetChanged();

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter(listAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3)
            {
                Fooditem entry= (Fooditem) parent.getAdapter().getItem(position);

            }
        });

        EditText inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                listAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fooditem x = (Fooditem) parent.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), FoodActivity.class);
                intent.putExtra("name", x.getName());
                intent.putExtra("info", x.getInfo());
                startActivity(intent);
            }
        });

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
