package com.example.why_w9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> namesList;
    private HashMap<String,List<String>> infoList;

    public ExpandableListViewAdapter(Context context, List<String> namesList, HashMap<String, List<String>> infoList) {
        this.context = context;
        this.namesList = namesList;
        this.infoList = infoList;
    }

    @Override
    public int getGroupCount() {
        return this.namesList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.infoList.get(this.namesList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.namesList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.infoList.get(this.namesList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String employeeName = (String) getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.name_list,null);
        }
        TextView employeeTV = convertView.findViewById(R.id.Name_tv);
        employeeTV.setText(employeeName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String subinfo = (String) getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.subinfo_list,null);
        }
        TextView subinfoTV = convertView.findViewById(R.id.subinfo_tv);
        subinfoTV.setText(subinfo);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
