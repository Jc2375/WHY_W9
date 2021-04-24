Data Collection is done through our app: We use a user's email and password for authentication, and input our own values for menu items, etc in the database.

Example of authentication:

fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), customer_home.class));
                                }
                                else {
                                    Toast.makeText(login.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


Example of reading from the database:

private void GetDataFromFirebase()
    {
        Query query= myRef.child("Menu");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    food_item items = new food_item();
                    items.setImageUrl(snapshot.child("Image").getValue().toString());
                    items.setName(snapshot.child("Name").getValue().toString());
                    food_items.add(items);
                }
                recyclerAdapter =new RecyclerAdapter(getApplicationContext(),food_items);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

Example of writing to the database:

public void onButtonClick(View v) {
        if (v.getId() == R.id.BDeliveryAddress) {
            String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

            Customers customers;
            EditText Name, Address, City, State, Zipcode;
            DatabaseReference reff;
            Name = (EditText) findViewById(R.id.editTextTextPersonName);
            Address = (EditText) findViewById(R.id.editTextTextPersonName2);
            City = (EditText) findViewById(R.id.editTextTextPersonName3);
            State = (EditText) findViewById(R.id.editTextTextPersonName4);
            Zipcode = (EditText) findViewById(R.id.editTextTextPersonName5);
            reff = FirebaseDatabase.getInstance().getReference().child("Customers");

            customers = new Customers();
            customers.setName(Name.getText().toString().trim());
            customers.setAddress(Address.getText().toString().trim());
            customers.setCity(City.getText().toString().trim());
            customers.setState(State.getText().toString().trim());
            customers.setZipcode(Zipcode.getText().toString().trim());
            reff.child(uid).setValue(customers);

            Toast.makeText(delivery_address.this, "Select Payment Type", Toast.LENGTH_SHORT).show();
            Bundle bundle=new Bundle();
            bundle.putBoolean("Delivery",true);
            Intent i = new Intent(delivery_address.this, payment_type.class);
            i.putExtras(bundle);
            startActivity(i);
        }

    }
