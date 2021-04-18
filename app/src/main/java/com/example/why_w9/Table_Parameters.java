package com.example.why_w9;

public class Table_Parameters {

    private String table_number, table_status, table_user, order_details;
    public Table_Parameters(){

    }


    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public void setTable_status(String table_status) {
        this.table_status = table_status;
    }

    public void setTable_user(String table_user) {
        this.table_user = table_user;
    }

    public String getTable_user() {
        return table_user;
    }

    public String getOrder_details() {
        return order_details;
    }

    public void setOrder_details(String order_details) {
        this.order_details = order_details;
    }

    public String getTable_status() {
        return table_status;
    }
}
